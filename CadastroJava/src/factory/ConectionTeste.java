package factory;

import java.sql.Connection;
//imortando conex�o com java
import java.sql.SQLException;
//importa tratamaento de exe��o dentro do SQL



public class ConectionTeste {

	
	 public static void main(String[] args) throws SQLException {
		 // throws : para que exce��o seja tratada outra classe ou metodo que venha lhe chamar
         Connection connection = new ConnectionFactory().getConnection();
         System.out.println("Conex�o aberta!");
         connection.close();
     }
}
