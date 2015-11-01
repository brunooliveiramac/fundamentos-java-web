package br.com.hightechcursos.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.hightechcursos.entidades.Usuario;
import br.com.hightechcursos.jdbc.Conexao;

public class UsuarioDAO {

	private Connection con = Conexao.getConection();

	public void cadastrar(Usuario usuario) {

		String sql = "INSERT INTO usuario (nome, login, senha) values (?,?,md5(?))";
		try {
			// Preparador
			PreparedStatement preparador = con.prepareStatement(sql);

			// preparador substitui os dados pelo objeto, pela index
			preparador.setString(1, usuario.getNome());
			preparador.setString(2, usuario.getLogin());
			preparador.setString(3, usuario.getSenha());
			preparador.execute();
			preparador.close();
			System.out.println("Sucesso inclus�o");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void alterar(Usuario usuario) {

		String sql = " UPDATE usuario SET nome = ?, login = ?, senha=md5(?)  WHERE id = ? ";
		try {
			// Preparador
			PreparedStatement preparador = con.prepareStatement(sql);

			// preparador substitui os dados pelo objeto, pela index
			preparador.setString(1, usuario.getNome());
			preparador.setString(2, usuario.getLogin());
			preparador.setString(3, usuario.getSenha());
			preparador.setInt(4, usuario.getId());

			preparador.execute();
			preparador.close();
			System.out.println("Sucesso editar");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void excluir(Usuario usuario) {

		String sql = " DELETE FROM usuario  WHERE id = ? ";
		try {
			// Preparador
			PreparedStatement preparador = con.prepareStatement(sql);

			// preparador substitui os dados pelo objeto, pela index

			preparador.setInt(1, usuario.getId());

			preparador.execute();
			preparador.close();
			System.out.println("Sucesso excluir");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void salvar(Usuario usuario) {
		if (usuario.getId() != null && usuario.getId()!=0) {
			alterar(usuario);
		} else {
			cadastrar(usuario);
		}
	}

	public Usuario buscaPorId(Integer id) {
		// Objeto de retorno
		Usuario usuRetorno = null;

		String sql = "SELECT * FROM usuario WHERE id = ?";

		try {
			PreparedStatement prepare = con.prepareStatement(sql);
			prepare.setInt(1, id);

			// Retorno da consulta em resultset
			ResultSet resultado = prepare.executeQuery();
			// Se tem registro
			if (resultado.next()) {
				usuRetorno = new Usuario();
				usuRetorno.setId(resultado.getInt("id"));
				usuRetorno.setLogin(resultado.getString("login"));
				usuRetorno.setNome(resultado.getString("nome"));
				usuRetorno.setSenha(resultado.getString("senha"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuRetorno;
	}

	public List<Usuario> buscarTodos(){
		//Objeto de retorno
		
		List<Usuario> list = new ArrayList<Usuario>();

		String sql = "SELECT * FROM usuario";
		
		try {
			PreparedStatement prepare = con.prepareStatement(sql);
			
			//Retorno da consulta em resultset
			ResultSet resultado = prepare.executeQuery();
			//Se tem registro
			while(resultado.next()){
				Usuario usuRetorno  = new Usuario();
				usuRetorno.setId(resultado.getInt("id"));
				usuRetorno.setLogin(resultado.getString("login"));
				usuRetorno.setNome(resultado.getString("nome"));
				usuRetorno.setSenha(resultado.getString("senha"));
				list.add(usuRetorno);
			}
		} catch (Exception e) {
				e.printStackTrace();
		}
		return list;
	}

	public Usuario autenticarUsu(Usuario usuario) {
		String sql = "SELECT * from usuario WHERE login = ? and senha = md5(?)";
		try {
			PreparedStatement preprare = con.prepareStatement(sql);
			preprare.setString(1, usuario.getLogin());
			preprare.setString(2, usuario.getSenha());

			ResultSet resultado = preprare.executeQuery();
			if (resultado.next()) {
				resultado.getInt("id");
				resultado.getString("login");
				resultado.getString("nome");
				resultado.getString("senha");
				return usuario;

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

}
