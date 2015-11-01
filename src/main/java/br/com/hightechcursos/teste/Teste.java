package br.com.hightechcursos.teste;

import br.com.hightechcursos.dao.UsuarioDAO;
import br.com.hightechcursos.entidades.Usuario;
import br.com.hightechcursos.jdbc.Conexao;

public class Teste {

	public static void main(String[] args) {
		//testeCadastro();
		//testeAlterar();
		//salvargenerico();
		//consultaId();
		autenticar();
	}

	private static void testeCadastro() {
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usu = new Usuario();
		usu.setNome("Bruno");
		usu.setLogin("Bruno");
		usu.setSenha("123");
		
		dao.cadastrar(usu);
	}
	
	private static void testeAlterar() {
		Usuario usu = new Usuario();
		usu.setId(1);
		usu.setNome("SucessoAltarar");
		usu.setLogin("Alterado");
		usu.setSenha("sigma");

		UsuarioDAO dao = new UsuarioDAO();
		dao.alterar(usu);
	}
	
	private static void salvargenerico() {
		Usuario usu = new Usuario();
		usu.setId(1);
		usu.setNome("generico");
		usu.setLogin("generico");
		usu.setSenha("sigma");

		UsuarioDAO dao = new UsuarioDAO();
		dao.salvar(usu);
	}
	
	private static void consultaId() {
	

		UsuarioDAO dao = new UsuarioDAO();
		Usuario usu = dao.buscaPorId(1);
		
		System.out.println(usu);
	
	}
	
	private static void autenticar() {
		

		UsuarioDAO dao = new UsuarioDAO();
		Usuario usu = new Usuario();
		usu.setLogin("Bruno");
		usu.setSenha("123");
		
		Usuario usuautenticado = dao.autenticarUsu(usu);
		
		System.out.println(usuautenticado);
		
		
	
	}

}
