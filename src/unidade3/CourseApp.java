package unidade3;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CourseApp {

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

	public static void create(int cdcurso, String nome, double valor, String url) throws SQLException {
		String sql = "insert into curso values (" + cdcurso + ", '" + nome + "', " + valor + ", '" + url + "')";

		Statement st = connection.createStatement();
		st.executeUpdate(sql);

		connection.commit();
	}

	public static void createWithPS(int cdcurso, String nome, double valor, String url) throws SQLException {
		String sql = "insert into curso values (?,?,?,?)";
		PreparedStatement pst = connection.prepareStatement(sql);
		pst.setInt(1, cdcurso);
		pst.setString(2, nome);
		pst.setDouble(3, valor);
		pst.setString(4, url);

		pst.executeUpdate();

		connection.commit();
	}

	public static void read(int cdcodigo) throws SQLException {
		String sql = "select * from curso where cdcurso=" + cdcodigo;

		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			String result = "cd_curso: " + rs.getString(1) + " nome: " + rs.getString(2) + " valor: " + rs.getString(3)
					+ " url: " + rs.getString(4);
			System.out.println(result);
		}
	}

	public static void readAll() throws SQLException {

		String sql = "select * from curso";

		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery(sql);

		int count = 0;
		while (rs.next()) {
			String result = "cd_curso: " + rs.getString(1) + " nome: " + rs.getString(2) + " valor: " + rs.getString(3)
					+ " url: " + rs.getString(4);
			System.out.println(result);
			System.out.println("=================================================================================");
			count++;
		}

		System.out.println("Número de clientes listados: " + count + "\n");
	}

	public static void update(int cdcurso, String nome, double valor, String url) throws SQLException {
		String sql = "update curso set nome='" + nome + "', valor=" + valor + ", url='" + url + "' where cdcurso="
				+ cdcurso;

		Statement st = connection.createStatement();
		st.executeUpdate(sql);

		connection.commit();
	}

	public static void delete(int cdcurso) throws SQLException {
		String sql = "delete from curso where cdcurso= " + cdcurso;

		Statement st = connection.createStatement();
		st.executeUpdate(sql);

		connection.commit();
	}

	public static void main(String[] args) {

		try {
			connect();

			Scanner sc = new Scanner(System.in);
			int option = 0;
			int cdcurso;
			String name, url;
			double valor;

			while (option != 6) {
				System.out.println("======================================");
				System.out.println("= Sistema de Gerenciamento de Cursos =");
				System.out.println("======================================\n");
				System.out.println("Digite [1] para consultar todos os cursos");
				System.out.println("Digite [2] para consultar um curso específico");
				System.out.println("Digite [3] para cadastrar um novo curso");
				System.out.println("Digite [4] para alterar um curso");
				System.out.println("Digite [5] para excluir um curso");
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
					System.out.println("[2] - Consultar um curso específico");
					System.out.print("Informe o código do curso: ");
					cdcurso = sc.nextInt();
					read(cdcurso);
					break;
				case 3:
					System.out.println("[3] - Cadastrar novo curso");
					System.out.print("Informe o código do curso: ");
					cdcurso = sc.nextInt();
					System.out.print("Informe o nome: ");
					sc.nextLine();
					name = sc.nextLine();
					System.out.print("Informe o valor do curso: ");
					valor = sc.nextDouble();
					System.out.print("Informe a url do curso: ");
					sc.nextLine();
					url = sc.nextLine();
					create(cdcurso, name, valor, url);
					//createWithPS(cdcurso, name, valor, url);
					//createWithSP(cdcurso, name, valor, url);
					break;
				case 4:
					System.out.println("[4] - Alterar um curso");
					System.out.print("Informe o código do curso: ");
					cdcurso = sc.nextInt();
					System.out.print("Informe o nome: ");
					sc.nextLine();
					name = sc.nextLine();
					System.out.print("Informe o valor do curso: ");
					valor = sc.nextDouble();
					System.out.print("Informe a url do curso: ");
					sc.nextLine();
					url = sc.nextLine();
					update(cdcurso, name, valor, url);
					break;
				case 5:
					System.out.println("[5] - Excluir um curso");
					System.out.print("Informe o código do curso: ");
					cdcurso = sc.nextInt();
					delete(cdcurso);
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
