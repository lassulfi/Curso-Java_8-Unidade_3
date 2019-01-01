package unidade3;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DatabaseAccess {

	static String url = "jdbc:oracle:thin:@localhost:1521:XE";
	static String username = "curso_java";
	static String password = "schema";
	
	static Connection connection;
	
	public static void connect() throws SQLException {
		connection = DriverManager.getConnection(url, username, password);
		connection.setAutoCommit(false);
	}
	
	public static void queryCustomer() throws SQLException {
		String query = "select * from cliente";
		
		Statement statement = connection.createStatement();
		
		ResultSet rs = statement.executeQuery(query);
		
		while(rs.next()) {
			String result = "cpf: " + rs.getString(1) 
							+ " nome: " + rs.getString(2)
							+ " email: " + rs.getString(3);
			JOptionPane.showMessageDialog(null, result);
		}
	}
	
	public static void showDBMetaInfo() throws SQLException {
		DatabaseMetaData metaData = connection.getMetaData();
		String manufacture = metaData.getDatabaseProductName();
		String version = metaData.getDatabaseProductVersion();
		
		JOptionPane.showMessageDialog(null, manufacture + " - " + version);
	}
	
	public static void main(String[] args) {
		try {
			connect();
			showDBMetaInfo();
			queryCustomer();
			
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

}
