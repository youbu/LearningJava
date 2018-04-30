package com.yangwu.proxy;

public class Test {
	public static void main(String[] args) {
		
		ProxyFactory factory = new ProxyFactory(ProxyTest.class);
		
		ProxyTest test = (ProxyTest) factory.getProxy();

		test.test("aaaaa");
	}
}
