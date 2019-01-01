package com.abctreinamentos.payment;

import java.sql.SQLException;
import java.util.List;

public interface PaymentDAO {

	public void create(Payment payment) throws SQLException;
	public void update(Payment payment) throws SQLException;
	public void delete(int code) throws SQLException;
	public Payment read(int code) throws SQLException;
	public List<Payment> list() throws SQLException;
}
