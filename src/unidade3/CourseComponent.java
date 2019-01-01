package unidade3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.abctreinamentos.course.Course;
import com.abctreinamentos.course.CourseDAO;
import com.abctreinamentos.course.CourseDAOImpl;

public class CourseComponent {

	public static void start(Connection connection, Scanner sc) {

		try {

			int option = 0;
			int cdcurso;
			String name, url;
			double valor;

			CourseDAO courseDAO = new CourseDAOImpl(connection);

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
					List<Course> courses = courseDAO.list();
					for(Course course : courses) {
						System.out.println(course.toString());
						System.out.println("========================================================\n");
					}
					System.out.println("Total de cursos cadastrados: " + courses.size());
					break;
				case 2:
					System.out.println("[2] - Consultar um curso específico");
					System.out.print("Informe o código do curso: ");
					cdcurso = sc.nextInt();
					Course course = courseDAO.read(cdcurso);
					System.out.println(course.toString());
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
					courseDAO.create(new Course(cdcurso, name, valor, url));
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
					courseDAO.update(new Course(cdcurso, name, valor, url));
					break;
				case 5:
					System.out.println("[5] - Excluir um curso");
					System.out.print("Informe o código do curso: ");
					cdcurso = sc.nextInt();
					courseDAO.delete(cdcurso);
					break;
				case 6:
					System.out.println("Encerrando o sistema");
					break;
				default:
					System.out.println("Opção inválida");
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
