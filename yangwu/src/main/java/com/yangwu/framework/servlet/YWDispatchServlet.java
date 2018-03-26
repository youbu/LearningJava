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

		// 1 加载配置文件
		doLoadConfig();

		// 2 根据配置文件扫描所有相关的类
		doScanner(properties.getProperty("scanPackage"));

		// 3 初始化所有相关类的实例，并且将其放入IOC容器中
		doInstance();

		// 4 实现自动依赖注入
		doAutowried();

		// 5 初始化Hand Mapping
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
			// 获取所有字段
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
				// 初始化IOC容器
				if (clazz.isAnnotationPresent(YWController.class)) {
					// 1、Key默认使用类名首字母小写
					ioc.put(beanName, clazz.newInstance());
				} else if (clazz.isAnnotationPresent(YWService.class)) {
					YWService service = clazz.getAnnotation(YWService.class);
					if (!"".equals(service.value())) {
						// 2、如果有名字，优先使用自定义名字
						beanName = lowerFirstCase(service.value());
					}

					Object instance = clazz.newInstance();
					ioc.put(beanName, instance);
					// 3、如果是接口的话，使用接口名作为Key
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
		// 加載Spring配置文件 applicationContext.xml
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
