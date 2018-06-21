package com.yangwu.entity;

import java.util.ArrayList;
import java.util.List;

public class Person {

	public Person() {

	}

	public Person(String name, String sex, int age) {
		this.name = name;
		this.sex = sex;
		this.age = age;
	}

	private String name;

	private String sex;

	private int age;

	private List<Person>son;
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		String str = "name : " + name + " sex : " + sex + " age : " + age + " son : " + son.size();
		return str;
	}

	public List<Person> getSon() {
		return son;
	}

	public void setSon(List<Person> son) {
		this.son = son;
	}

}
