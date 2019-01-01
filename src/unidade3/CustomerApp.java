package unidade3;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CustomerApp {

	static String url = "jdbc:oracle:thin:@localhost:1521:XE";
	static String username = "curso_java";
	static String password = "schema";

	static Connection connection;

	/**
	 * Open a connection with database
	 * 
	 * @throws SQLException
	 */
	public static void connect() throws SQLException {
		connection = DriverManager.getConnection(url, username, password);
		connection.setAutoCommit(false);
	}

	/**
	 * Close the connection with database
	 * 
	 * @throws SQLException
	 */
	public static void closeConnection() throws SQLException {
		connection.close();
	}

	public static void create(long cpf, String name, String email) throws SQLException {
		String sql = "insert into cliente values (" + cpf + ", '" + name + "', '" + email + "')";

		Statement st = connection.createStatement();
		st.executeUpdate(sql);

		connection.commit();
	}

	public static void createWithPS(long cpf, String name, String email) throws SQLException {
		String sql = "insert into cliente values (?,?,?)";
		PreparedStatement pst = connection.prepareStatement(sql);
		pst.setLong(1, cpf);
		pst.setString(2, name);
		pst.setString(3, email);

		pst.executeUpdate();

		connection.commit();
	}
	
	public static void createWithSP(long cpf, String name, String email) throws SQLException {
		String sql = "{call sp_create_customer(?,?,?)}";
		
		CallableStatement cst = connection.prepareCall(sql);
		
		cst.setLong(1, cpf);
		cst.setString(2, name);
		cst.setString(3, email);

		cst.execute();

		connection.commit();
	}

	public static void read(long cpf) throws SQLException {
		String sql = "select * from cliente where cpf=" + cpf;

		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			String result = "cpf: " + rs.getString(1) + " nome: " + rs.getString(2) + " email: " + rs.getString(3);
			System.out.println(result);
		}
	}

	public static void readAll() throws SQLException {

		String sql = "select * from cliente";

		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery(sql);

		int count = 0;
		while (rs.next()) {
			String result = "cpf: " + rs.getString(1) + " nome: " + rs.getString(2) + " email: " + rs.getString(3);
			System.out.println(result);
			System.out.println("=================================================================================");
			count++;
		}

		System.out.println("Número de clientes listados: " + count + "\n");
	}

	public static void update(long cpf, String name, String email) throws SQLException {
		String sql = "update cliente set nome='" + name + "', email='" + email + "' where cpf=" + cpf;

		Statement st = connection.createStatement();
		st.executeUpdate(sql);

		connection.commit();
	}

	public static void delete(long cpf) throws SQLException {
		String sql = "delete from cliente where cpf= " + cpf;

		Statement st = connection.createStatement();
		st.executeUpdate(sql);

		connection.commit();
	}

	public static void main(String[] args) {

		try {
			connect();

			Scanner sc = new Scanner(System.in);
			int option = 0;
			long cpf;
			String name, email;

			while (option != 6) {
				System.out.println("========================================");
				System.out.println("= Sistema de Gerenciamento de Clientes =");
				System.out.println("========================================\n");
				System.out.println("Digite [1] para consultar todos os clientes");
				System.out.println("Digite [2] para consultar um cliente específico");
				System.out.println("Digite [3] para cadastrar um novo cliente");
				System.out.println("Digite [4] para alterar um cliente");
				System.out.println("Digite [5] para excluir um cliente");
				System.out.println("Digite [6] para sair");
				System.out.println("========================================\n");
				System.out.print("opcao: ");
				option = sc.nextInt();

				switch (option) {
				case 1:
					System.out.println("[1] - Consultar todos");
					readAll();
					break;
				case 2:
					System.out.println("[2] - Consultar um cliente específico");
					System.out.print("Informe o cpf: ");
					cpf = sc.nextLong();
					read(cpf);
					break;
				case 3:
					System.out.println("[3] - Cadastrar novo cliente");
					System.out.print("Informe o cpf: ");
					cpf = sc.nextLong();
					System.out.print("Informe o nome: ");
					sc.nextLine();
					name = sc.nextLine();
					System.out.print("Informe o email: ");
					email = sc.nextLine();
					// create(cpf, name, email);
					//createWithPS(cpf, name, email);
					createWithSP(cpf, name, email);
					break;
				case 4:
					System.out.println("[4] - Alterar um cliente");
					System.out.print("Informe o cpf: ");
					cpf = sc.nextLong();
					System.out.print("Informe o nome: ");
					sc.nextLine();
					name = sc.nextLine();
					System.out.print("Informe o email: ");
					email = sc.nextLine();
					update(cpf, name, email);
					break;
				case 5:
					System.out.println("[5] - Excluir um cliente");
					System.out.print("Informe o cpf: ");
					cpf = sc.nextLong();
					delete(cpf);
					break;
				case 6:
					closeConnection();
					System.out.println("Encerrando o sistema");
					break;
				default:
					System.out.println("Opção inválida");
					break;
				}
			}

			sc.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
