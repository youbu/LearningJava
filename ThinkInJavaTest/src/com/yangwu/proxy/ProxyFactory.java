package com.yangwu.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class ProxyFactory implements MethodInterceptor {

	private Class<?> target;

	public ProxyFactory(Class<?> target) {
		this.target = target;
	}
	
	public Object getProxy() {
		Enhancer en = new Enhancer();
		en.setSuperclass(target);
		en.setCallback(this);
		return en.create();
	}

	@Override
	public Object intercept(Object arg0, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println("==========before==============");
		proxy.invokeSuper(arg0, args);
		System.out.println("==========after===============");
		return arg0;
	}

}
