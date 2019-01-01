package unidade3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.abctreinamentos.payment.Payment;
import com.abctreinamentos.payment.PaymentDAO;
import com.abctreinamentos.payment.PaymentDAOImpl;

public class LojaVirtual {

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

			int code, course;
			long cpf;
			Date subscription;
			
			PaymentDAO paymentDAO = new PaymentDAOImpl(connection);
			
			int option = 0;
			while (option != 10) {
				System.out.println("================================");
				System.out.println("= Loja Virual de cursos online =");
				System.out.println("================================\n");
				System.out.println("Digite [1] para acessar o sistema de gestão de clientes");
				System.out.println("Digite [2] para acessar o sistema de gestão de cursos");
				System.out.println("Digite [3] para consultar uma compra por id");
				System.out.println("Digite [4] para consultar todas as compras");
				System.out.println("Digite [5] para cadastrar uma nova compra");
				System.out.println("Digite [6] para alterar uma compra");
				System.out.println("Digite [7] para excluir uma compra");
				System.out.println("Digite [8] para sair");
				System.out.println("================================\n");
				System.out.print("opcao: ");
				option = sc.nextInt();

				switch (option) {
				case 1:
					CustomerComponent.start(connection, sc);
					break;
				case 2:
					CourseComponent.start(connection, sc);
					break;
				case 3:
					System.out.println("[3] - Consulta de compra por id");
					System.out.print("ID da compra: ");
					code = sc.nextInt();
					Payment payment = paymentDAO.read(code);
					System.out.println(payment.toString());
					break;
				case 4:
					System.out.println("[4] - Consulta de todas as compras");
					List<Payment> payments = paymentDAO.list();
					for(Payment p : payments) {
						System.out.println(p.toString());
						System.out.println("================================");
					}
					break;
				case 5:
					System.out.println("[5] - Cadastrar nova compra");
					System.out.print("ID da compra: ");
					code = sc.nextInt();
					sc.nextLine();
					System.out.println("CPF do cliente: ");
					cpf = sc.nextLong();
					sc.nextLine();
					System.out.println("Código do curso: ");
					course = sc.nextInt();
					paymentDAO.create(new Payment(code, cpf, course, new Date()));
					break;
				case 6:
					System.out.println("[6] - Alterar uma conta");
					System.out.print("ID da compra: ");
					code = sc.nextInt();
					sc.nextLine();
					System.out.println("CPF do cliente: ");
					cpf = sc.nextLong();
					sc.nextLine();
					System.out.println("Código do curso: ");
					course = sc.nextInt();
					sc.nextLine();
					System.out.println("Dia: ");
					int day = sc.nextInt();
					sc.nextLine();
					System.out.println("Mês: ");
					int month = sc.nextInt();
					sc.nextLine();
					System.out.println("Ano: ");
					int year = sc.nextInt();
					paymentDAO.create(new Payment(code, cpf, course, new Date(year, month, day)));
					break;
				case 7:
					System.out.println("[7] - Excluir um item de compra");
					System.out.print("ID da compra: ");
					code = sc.nextInt();
					paymentDAO.delete(code);
					break;
				case 8:
					closeConnection();
					System.out.println("Saindo da Loja Virtual");
				default:
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}