package com.yangwu.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yangwu.framework.annotation.YWAutowired;
import com.yangwu.framework.annotation.YWController;
import com.yangwu.framework.annotation.YWRequestMapping;
import com.yangwu.framework.annotation.YWResquestParam;
import com.yangwu.service.YWService;

@YWController
@YWRequestMapping("/demo")
public class ControllerOne {
	
	@YWAutowired
	private YWService service;
	
	@YWRequestMapping("/get")
	public void query(HttpServletRequest request,HttpServletResponse response,@YWResquestParam("name")  String name) {
		String result = service.get(name);
		
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@YWRequestMapping("/getAll")
	public void queryAll(HttpServletRequest request,HttpServletResponse response,@YWResquestParam("name")  String name) {
		String result = service.getAll(name);
		
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
