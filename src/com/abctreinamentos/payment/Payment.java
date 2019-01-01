package com.abctreinamentos.payment;

import java.util.Date;

public class Payment {

	private int code;
	private long cpf;
	private int courseID;
	private Date subscription;

	public Payment() {
	}

	public Payment(int code, long cpf, int courseID, Date subscription) {
		this.code = code;
		this.cpf = cpf;
		this.courseID = courseID;
		this.subscription = subscription;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public long getCpf() {
		return cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public Date getSubscription() {
		return subscription;
	}

	public void setSubscription(Date subscription) {
		this.subscription = subscription;
	}

	@Override
	public String toString() {
		return "código pagamento =" + code + ", CPF= " + cpf + ", Código do Curso =" + courseID
				+ ", Data de inscrição = " + subscription.toString();
	}
}
