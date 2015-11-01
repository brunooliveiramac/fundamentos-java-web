package br.com.hightechcursos.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.hightechcursos.dao.UsuarioDAO;
import br.com.hightechcursos.entidades.Usuario;

//http://localhost:8080/FundamentosJava/usucontroller.do
@WebServlet("/usucontroller.do")
public class UsuarioServlet extends HttpServlet {
			

	private static final long serialVersionUID = 1L;
	
	
	public UsuarioServlet(){
		System.out.println("Construtor");
	}
	
	@Override //Inicia após o construtor
	public void init() throws ServletException {
		System.out.println("Init");
		super.init();
	}
	
	
	

	@Override			//HttpRequest: Tudo o que vem do browser
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException { 
		
		resp.setContentType("text/html");
		//DoGet: Busca e exclusão
		String acao = req.getParameter("acao");
		if(acao.equals("exc")){
			String id = req.getParameter("id");   //Tudo o que vem da Tela sempre é uma String
			Usuario usu = new Usuario();
			if(id != null)
				usu.setId(new Integer(id));  //Tudo o que vem da Tela sempre é uma String, necessario conversão
			UsuarioDAO dao = new  UsuarioDAO();
			dao.excluir(usu);
			resp.getWriter().print("<b>Excluido com sucesso!</b>");;
			

			resp.sendRedirect("usucontroller.do?acao=list");
		}else if(acao.equals("list")){
			UsuarioDAO dao = new  UsuarioDAO();
			List<Usuario> lista = dao.buscarTodos();
			//Colocar lista dentro do request que sera enviado atrvés do servlet para o jsp q tratá a visualização
			req.setAttribute("chaveLista", lista); //chaveLista no jsp
			RequestDispatcher dipacher = req.getRequestDispatcher("WEB-INF/listausu.jsp"); // constroi uma instacia de ReqDispacher para poder enviar (Foward)
			dipacher.forward(req, resp);	
			//Editar
		}else if(acao.equals("alt")){//Toda vez que vier um click dizendo "Quero alterar"
			String id = req.getParameter("id"); //Identificamos quem queremos alterar
			UsuarioDAO dao = new UsuarioDAO();
			Usuario usu = dao.buscaPorId(new Integer(id));//buscamos no banco
			req.setAttribute("usu",usu); //colocamos dentro do request o obj
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/formusuario.jsp"); //objeto para redirecionar
			dispatcher.forward(req, resp);//redirecionamos para o formulario e este joga o objeto nos campos
			
			//AQUI SÓ FEZ O REDIRECIONAMENTO COM O OBJETO
			
		}else if(acao.equals("cad")){//Não sera um usuario vindo do banco commo no editar
			Usuario usu = new Usuario();
			usu.setId(0);
			usu.setLogin("");
			usu.setNome("");
			usu.setSenha("");
			req.setAttribute("usu",usu); //colocamos dentro do request o obj
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/formusuario.jsp"); //objeto para redirecionar
			dispatcher.forward(req, resp);//redirecionamos para o formulario e este joga o objeto nos campos
			
				
			
		}
		
	}
	 
	//No método GET eu irformo na URL, no método POST os dados vão escondidos no protocólo
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//Dados que virão do browser (JSP)	//Pegamos através do Request
		String id = req.getParameter("id");   //Tudo o que vem da Tela sempre é uma String
		String nome = req.getParameter("nome");
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		
		Usuario usu = new Usuario();
		if(id != null)
			usu.setId(new Integer(id));  //Tudo o que vem da Tela sempre é uma String, necessario conversão
		
		usu.setLogin(login);
		usu.setNome(nome);
		usu.setSenha(senha);
		
		UsuarioDAO dao = new  UsuarioDAO();
		dao.salvar(usu);
		
		//Resposta na TELA do usuario
		resp.getWriter().print("<b>Cadastrado com sucesso!</b>");
		resp.sendRedirect("usucontroller.do?acao=list");

	}
	
	
	@Override
	public void destroy() {
		System.out.println("Destroy");
		super.destroy();
	}
	 
	 
}
