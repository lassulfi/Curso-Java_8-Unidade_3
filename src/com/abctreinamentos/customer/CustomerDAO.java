package com.abctreinamentos.customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO {
	
	public void create(Customer customer) throws SQLException;
	public void update(Customer customer) throws SQLException;
	public void delete(long cpf) throws SQLException;
	public Customer read(long cpf) throws SQLException;
	public List<Customer> readAll() throws SQLException;
}
