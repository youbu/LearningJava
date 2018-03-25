package com.yangwu.service.impl;

import com.yangwu.service.YWService;

@com.yangwu.framework.annotation.YWService
public class YWServiceImpl implements YWService {

	public String get(String name) {
		return "Hello" + name;
	}

	public String getAll(String name) {
		return "getAll " + name;
	}

}
