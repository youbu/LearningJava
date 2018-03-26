package com.yangwu.framework.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yangwu.framework.annotation.YWAutowired;
import com.yangwu.framework.annotation.YWController;
import com.yangwu.framework.annotation.YWRequestMapping;
import com.yangwu.framework.annotation.YWService;

public class YWDispatchServlet extends HttpServlet {

	private Properties properties = new Properties();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<String> classNames = new ArrayList<String>();

	private Map<String, Object> ioc = new HashMap<String, Object>();

	private Map<String, Object> handlemapping = new HashMap<String, Object>();

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		// 1 ���������ļ�
		doLoadConfig();

		// 2 ���������ļ�ɨ��������ص���
		doScanner(properties.getProperty("scanPackage"));

		// 3 ��ʼ������������ʵ�������ҽ������IOC������
		doInstance();

		// 4 ʵ���Զ�����ע��
		doAutowried();

		// 5 ��ʼ��Hand Mapping
		initHandlerMapping();

	}

	private void initHandlerMapping() {
		
		if (ioc.isEmpty()) {
			return;
		}
		Class<?>clazz = null;		
		YWRequestMapping mapping = null;
		for (Entry<String, Object> entry : ioc.entrySet()) {
			clazz = entry.getValue().getClass();
			if (!clazz.isAnnotationPresent(YWController.class)) {
				return;			
			}
			
			if (clazz.isAnnotationPresent(YWRequestMapping.class)) {
				mapping = clazz.getAnnotation(YWRequestMapping.class);
				String baseUrl = mapping.value();
				
				Method[] methods = clazz.getDeclaredMethods();
				for (Method method : methods) {
					if (method.isAnnotationPresent(YWRequestMapping.class)) {
						mapping = method.getAnnotation(YWRequestMapping.class);
						String url = (baseUrl + "/" +mapping.value()).replaceAll("/+", "/");
						handlemapping.put(url, method);
						System.out.println( " Url : " + url + "  Method : " + method );
					}
				}
				
				
				
			}
			
		}
		
	}

	private void doAutowried() {
		if (ioc.isEmpty()) {
			return;
		}

		for (Entry<String, Object> entry : ioc.entrySet()) {
			// ��ȡ�����ֶ�
			Field fields[] = entry.getValue().getClass().getDeclaredFields();
			for (Field field : fields) {
				if (field.isAnnotationPresent(YWAutowired.class)) {
					YWAutowired autowired = field.getAnnotation(YWAutowired.class);
					String beanName = autowired.value().trim();
					if ("".equals(beanName)) {
						beanName = field.getType().getName();
					}

					field.setAccessible(true);
					try {
						field.set(entry.getValue(), ioc.get(beanName));
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				} else {
					continue;
				}
			}
		}
	}

	private void doInstance() {
		if (classNames.isEmpty()) {
			return;
		}
		try {
			for (String string : classNames) {
				Class<?> clazz = Class.forName(string);
				String beanName = lowerFirstCase(clazz.getSimpleName());
				// ��ʼ��IOC����
				if (clazz.isAnnotationPresent(YWController.class)) {
					// 1��KeyĬ��ʹ����������ĸСд
					ioc.put(beanName, clazz.newInstance());
				} else if (clazz.isAnnotationPresent(YWService.class)) {
					YWService service = clazz.getAnnotation(YWService.class);
					if (!"".equals(service.value())) {
						// 2����������֣�����ʹ���Զ�������
						beanName = lowerFirstCase(service.value());
					}

					Object instance = clazz.newInstance();
					ioc.put(beanName, instance);
					// 3������ǽӿڵĻ���ʹ�ýӿ�����ΪKey
					Class<?>[] interfaces = clazz.getInterfaces();
					for (Class<?> i : interfaces) {
						ioc.put(i.getName(), instance);
					}

				} else {
					continue;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void doScanner(String packageName) {
		URL url = this.getClass().getClassLoader().getResource("/" + packageName.replaceAll("\\.", "/"));

		File classDir = new File(url.getFile());

		for (File file : classDir.listFiles()) {
			if (file.isDirectory()) {
				doScanner(packageName + "." + file.getName());
			} else {
				String className = packageName + "." + file.getName().replace(".class", "");
				classNames.add(className);
			}
		}
	}

	private void doLoadConfig() {
		// ���dSpring�����ļ� applicationContext.xml
		System.out.println("======== contextConfigLocation : "
				+ getServletConfig().getInitParameter("contextConfigLocation") + "===============");
		InputStream is = this.getClass().getResourceAsStream(getServletContext().getContextPath() + "/"
				+ getServletConfig().getInitParameter("contextConfigLocation"));
		try {
			properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String url = req.getRequestURI();
		url.replaceAll(req.getContextPath(), "").replaceAll("/+", "/");
		
		if (!handlemapping.containsKey(url)) {
			resp.getWriter().write("404 Not Found !");
			return;
		}
		
		Method method = (Method) handlemapping.get(url);
		
//		method.invoke(obj, args)
//		resp.getWriter().write("Hello MVC");
	}

	private String lowerFirstCase(String className) {
		char[] chars = className.toCharArray();
		chars[0] += 32;
		return String.valueOf(chars);
	}
}
