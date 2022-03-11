//em qual pacote a classe esta
package factory;

//importando classes necess�rias
import java.sql.Connection;
//Conex�o SQL para java
import java.sql.DriverManager;
//importando o driver de conex�o SQL
import java.sql.SQLException;
//importando a classe de tratamento de exe��es;

public class ConnectionFactory {
	// definindo os atributos para a conex�o

	/*
	 * CoonectionFactory signica fabrica de conex�es e � o nome da classe que faz
	 * inetrface com o driver JDBC de conex�o com qualquer banco de dados
	 */

	public Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost/cadastrojava", "root", "");
		} catch (SQLException excecao) {
			throw new RuntimeException(excecao);
		}
	}
}
