package com.yangwu.util;

import java.util.ArrayList;
import java.util.List;

import com.yangwu.entity.Person;

import net.sf.json.JSONObject;

public class JsonUtil {
	public static void main(String[] args) {
		Person person = new Person("ÕÅÈý", "male", 20);
		Person son1 = new Person("son1","m",12);
		Person son2 = new Person("son2","m",12);
		List<Person>son = new ArrayList<>();
		son.add(son1);
		son.add(son2);	
		person.setSon(son);
		
		
		JSONObject fromObject = JSONObject.fromObject(person);
		String jsonStr = fromObject.toString();
		System.out.println(jsonStr);
		
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		Person person2 = (Person) JSONObject.toBean(jsonObject, Person.class);
		System.out.println(person2.toString());
		
	}
}
