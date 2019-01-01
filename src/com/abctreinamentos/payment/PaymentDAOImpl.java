package com.abctreinamentos.payment;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {

	private Connection connection;

	public PaymentDAOImpl(Connection connetion) {
		this.connection = connetion;
	}

	@Override
	public void create(Payment payment) throws SQLException {
		String sql = "insert into pagamento values(?,?,?,?)";

		PreparedStatement pst = connection.prepareStatement(sql);
		pst.setInt(1, payment.getCode());
		pst.setLong(2, payment.getCpf());
		pst.setInt(3, payment.getCourseID());
		pst.setDate(4, (Date) payment.getSubscription());
		pst.executeUpdate();

		connection.commit();
	}

	@Override
	public void update(Payment payment) throws SQLException {
		String sql = "update pagamento set cpf=?, cdcurso=?, datainscricao=? where cdpagamento=?";

		PreparedStatement pst = connection.prepareStatement(sql);
		pst.setLong(1, payment.getCpf());
		pst.setInt(2, payment.getCourseID());
		pst.setDate(3, (Date) payment.getSubscription());
		pst.setInt(4, payment.getCode());
		pst.executeUpdate();

		connection.commit();

	}

	@Override
	public void delete(int code) throws SQLException {
		String sql = "delete from pagamento where cdpagamento=?";
		
		PreparedStatement pst = connection.prepareStatement(sql);
		pst.setInt(1, code);
		pst.executeUpdate();

		connection.commit();
	}

	@Override
	public Payment read(int code) throws SQLException {
		String sql = "select * from pagamento where id=?";

		PreparedStatement pst = connection.prepareStatement(sql);
		pst.setInt(1, code);
		ResultSet rs = pst.executeQuery();
		
		if(rs.next()) {
			int resultCode = rs.getInt(1);
			long resultCPF = rs.getLong(2);
			int resultCourse = rs.getInt(3);
			java.util.Date resultSubscription = rs.getDate(4);
			
			return new Payment(resultCode, resultCPF, resultCourse, resultSubscription);
		}
		
		return null;
	}

	@Override
	public List<Payment> list() throws SQLException {
		String sql = "select * from pagamento";
		
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		List<Payment> payments = new LinkedList<>();
		
		while(rs.next()) {
			int code = rs.getInt(1);
			long cpf = rs.getLong(2);
			int course = rs.getInt(3);
			java.util.Date subscription = rs.getDate(4);
			
			payments.add(new Payment(code, cpf, course, subscription));
		}
		
		return payments;
	}

}
