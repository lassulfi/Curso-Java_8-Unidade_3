package com.abctreinamentos.course;

public class Course {
	
	private int code;
	private String name;
	private double value;
	private String url;
	
	public Course() {}
	
	public Course(int code, String name, double value, String url) {
		this.code = code;
		this.name = name;
		this.value = value;
		this.url = url;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@Override
	public String toString() {
		return "código: " + code + ", nome: " + name + ", valor: " + String.format("%.2f", value) + ", url: " + url;
	}
}
