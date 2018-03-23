package com.yangwu.framework.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.Request;

public class YWDispatchServlet extends HttpServlet {

	Properties properties = new Properties();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.println("======== contextConfigLocation : "+config.getInitParameter("contextConfigLocation") + "===============");
		InputStream is = this.getClass().getResourceAsStream(getServletContext().getContextPath() +"/" + config.getInitParameter("contextConfigLocation"));
		try {
			
			properties.load(is);
			System.out.println("======== scanPackage : "+ properties.getProperty("scanPackage") + "===============");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		super.doPost(req, resp);
		resp.getWriter().write("Hello MVC");
	}
	
	

}
