package com.abctreinamentos.customer;

public class Customer {

	private long cpf;
	private String name;
	private String email;
	
	public Customer() {
		
	}
	
	public Customer(long cpf, String name, String email) {
		this.cpf = cpf;
		this.name = name;
		this.email = email;
	}

	public long getCpf() {
		return cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "cpf: " + cpf + " nome: " + name + " e-mail: " + email;
	}
}
