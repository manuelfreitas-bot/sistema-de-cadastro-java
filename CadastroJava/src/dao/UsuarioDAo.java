package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import factory.ConnectionFactory;
import model.Usuario;

public class UsuarioDAo {
//Criar, Consultar, Alterar, Deletar
	private Connection connection;
	long id;
	String nome;
	String cpf;
	String email;
	String telefone;

	public UsuarioDAo() {
		// criando conexão
		this.connection = new ConnectionFactory().getConnection();
	}

	/*
	 * public void adiciona(Usuario usuario){ String sql =
	 * "INSERT INTO usuario(nome,cpf,email,telefone) VALUES(?,?,?,?)"; try {
	 * PreparedStatement stmt = connection.prepareStatement(sql); stmt.setString(1,
	 * usuario.getNome()); stmt.setString(2, usuario.getCpf()); stmt.setString(3,
	 * usuario.getEmail()); stmt.setString(4, usuario.getTelefone());
	 * stmt.execute(); stmt.close(); } catch (SQLException u) { throw new
	 * RuntimeException(u); }
	 */
	public void adcionarUsuario(Usuario usuario) {
		String sql = "INSERT INTO usuario(nome, cpf,email,telefone) VALUES(?,?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getCpf());
			stmt.setString(3, usuario.getEmail());
			stmt.setString(4, usuario.getTelefone());
			stmt.execute();
			stmt.close();
		} catch (SQLException excecao) {
			throw new RuntimeException(excecao);
		}

	}

	public boolean update(Usuario usuario) {
		Connection connection = new ConnectionFactory().getConnection();

		int valor = 0;
		try {

			// inserindo os valores no banco de dados atraves do prepaareStatement
			PreparedStatement stmt = connection.prepareStatement("update  usuario set nome=?, cpf=?, email=?, telefone=? where id= ?");
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getCpf());
			stmt.setString(3, usuario.getEmail());
			stmt.setString(4, usuario.getTelefone());
			stmt.setLong(5, usuario.getId());
			valor = stmt.executeUpdate();

			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (valor == 1) {
			return true;
		} else
			return false;

	}

	public boolean deletar(long l) {

		int valor = 0;
		try {
			Connection connection = new ConnectionFactory().getConnection();
			// inserindo os valores no banco de dados atraves do prepaareStatement
			PreparedStatement stmt = connection.prepareStatement("delete from usuario where id= ?");

			stmt.setLong(1, l);

			valor = stmt.executeUpdate();

			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (valor == 1) {
			return true;
		} else
			return false;

	}

	public List<Usuario> read() {
		ResultSet rs = null;
		List<Usuario> usuario = new ArrayList<>();
		try {
			Connection connection = new ConnectionFactory().getConnection();
			// inserindo os valores no banco de dados atraves do prepaareStatement
			PreparedStatement stmt = connection.prepareStatement("SELECT * from usuario ");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Usuario usuarios = new Usuario();
				usuarios.setId(rs.getLong("id"));
				usuarios.setNome(rs.getString("nome"));
				usuarios.setCpf(rs.getString("cpf"));
				usuarios.setEmail(rs.getString("email"));
				usuarios.setTelefone(rs.getString("telefone"));
				usuario.add(usuarios);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}
}
