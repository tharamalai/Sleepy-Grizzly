package com.sleepygrizzly.core;

public class Person {
	public static Person person;
	private static boolean flag = false;
	private String name;
	private Integer score = 0;
	
	public Person(String name, Integer score){
		this.name = name;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
	
	public static Person getPerson(){
		if (flag) return Person.person;
		Person.person = new Person("Hello", 999);
		Person.flag = true;
		return Person.person;
	}

}
