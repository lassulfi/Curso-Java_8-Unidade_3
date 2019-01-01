package com.abctreinamentos.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

	private Connection connection;
	
	public CustomerDAOImpl(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public void create(Customer customer) throws SQLException {
		String sql = "insert into cliente values (?,?,?)";
		PreparedStatement pst = connection.prepareStatement(sql);
		pst.setLong(1, customer.getCpf());
		pst.setString(2, customer.getName());
		pst.setString(3, customer.getEmail());

		pst.executeUpdate();

		connection.commit();
	}

	@Override
	public void update(Customer customer) throws SQLException {
		String sql = "update cliente set nome=?, email=? where cpf=?";

		PreparedStatement pst = connection.prepareStatement(sql);
		pst.setString(1, customer.getName());
		pst.setString(2, customer.getEmail());
		pst.setLong(3, customer.getCpf());
		pst.executeUpdate();

		connection.commit();

	}

	@Override
	public void delete(long cpf) throws SQLException {
		String sql = "delete from cliente where cpf=?";
		
		PreparedStatement pst = connection.prepareStatement(sql);
		pst.setLong(1, cpf);
		pst.executeUpdate();
		
		connection.commit();
	}

	@Override
	public Customer read(long cpf) throws SQLException {
		String sql = "select * from cliente where cpf=?";
		
		PreparedStatement pst = connection.prepareStatement(sql);
		pst.setLong(1, cpf);
		ResultSet rs = pst.executeQuery();
		
		if(rs.next()) {
			long resultCPF = rs.getLong(1);
			String resultName = rs.getString(2);
			String resultEmail = rs.getString(3);
			
			return new Customer(resultCPF, resultName, resultEmail);
		}
		
		return null;
	}

	@Override
	public List<Customer> readAll() throws SQLException {
		String sql = "select * from cliente";
		
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		List<Customer> customers = new ArrayList<>();
		
		while(rs.next()) {
			long cpf = rs.getLong(1);
			String name = rs.getString(2);
			String email = rs.getString(3);
			
			customers.add(new Customer(cpf, name, email));
		}
		
		return customers;
	}

}
