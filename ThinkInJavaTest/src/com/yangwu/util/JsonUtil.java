package com.yangwu.util;

import com.yangwu.entity.Person;

import net.sf.json.JSONObject;

public class JsonUtil {
	public static void main(String[] args) {
		Person person = new Person("уехЩ", "male", 20);
		JSONObject fromObject = JSONObject.fromObject(person);
		String jsonStr = fromObject.toString();
		System.out.println(jsonStr);
		
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		Person person2 = (Person) JSONObject.toBean(jsonObject, Person.class);
		System.out.println(person2.toString());
		
	}
}
