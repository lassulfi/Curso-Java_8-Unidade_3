package unidade3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.abctreinamentos.customer.Customer;
import com.abctreinamentos.customer.CustomerDAO;
import com.abctreinamentos.customer.CustomerDAOImpl;

public class CustomerApp2 {

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

	public static void main(String[] args) {

		try {
			connect();

			Scanner sc = new Scanner(System.in);
			int option = 0;
			long cpf;
			String name, email;

			CustomerDAO customerDAO = new CustomerDAOImpl(connection);

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
					List<Customer> customers = customerDAO.readAll();
					for (Customer customer : customers) {
						System.out.println(customer.toString());
						System.out.println("==========================================================\n");
					}
					System.out.println("Número de clientes cadastrados: " + customers.size());
					break;
				case 2:
					System.out.println("[2] - Consultar um cliente específico");
					System.out.print("Informe o cpf: ");
					cpf = sc.nextLong();
					Customer customer = customerDAO.read(cpf);
					System.out.println(customer.toString());
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
					customerDAO.create(new Customer(cpf, name, email));
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
					customerDAO.update(new Customer(cpf, name, email));
					break;
				case 5:
					System.out.println("[5] - Excluir um cliente");
					System.out.print("Informe o cpf: ");
					cpf = sc.nextLong();
					customerDAO.delete(cpf);
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
