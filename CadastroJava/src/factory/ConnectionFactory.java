//em qual pacote a classe esta
package factory;

//importando classes necessárias
import java.sql.Connection;
//Conexão SQL para java
import java.sql.DriverManager;
//importando o driver de conexão SQL
import java.sql.SQLException;
//importando a classe de tratamento de exeções;

public class ConnectionFactory {
	// definindo os atributos para a conexão

	/*
	 * CoonectionFactory signica fabrica de conexões e é o nome da classe que faz
	 * inetrface com o driver JDBC de conexão com qualquer banco de dados
	 */

	public Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost/cadastrojava", "root", "");
		} catch (SQLException excecao) {
			throw new RuntimeException(excecao);
		}
	}
}
