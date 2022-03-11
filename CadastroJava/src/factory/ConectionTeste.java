package factory;

import java.sql.Connection;
//imortando conexão com java
import java.sql.SQLException;
//importa tratamaento de exeção dentro do SQL



public class ConectionTeste {

	
	 public static void main(String[] args) throws SQLException {
		 // throws : para que exceção seja tratada outra classe ou metodo que venha lhe chamar
         Connection connection = new ConnectionFactory().getConnection();
         System.out.println("Conexão aberta!");
         connection.close();
     }
}
