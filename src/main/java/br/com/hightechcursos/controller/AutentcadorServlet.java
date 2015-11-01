package br.com.hightechcursos.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.hightechcursos.dao.UsuarioDAO;
import br.com.hightechcursos.entidades.Usuario;

@WebServlet("/autenticador.do") 
public class AutentcadorServlet extends HttpServlet{


	private static final long serialVersionUID = 1L;
	
	//matar sessão
	//Se chamar pelo metodo get ele mata a sessão
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession(false);//Quando vc só quer acessar, sem criar...(true) acessa e cria e não achar
		
		if(session != null){
			session.invalidate();
			
		}
		
		resp.sendRedirect("login.html");
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

			String login = req.getParameter("login"); // pega dados da tela
			String senha = req.getParameter("senha");
			
			UsuarioDAO dao = new UsuarioDAO();
			Usuario usu = new Usuario();
			usu.setLogin(login);
			usu.setSenha(senha);
			
			Usuario usuarioAutenticado = dao.autenticarUsu(usu);
			if(usuarioAutenticado != null){
				//Request cria asession
				HttpSession session = req.getSession(); //Se usu for != null criamos um idSession, se ja estiver criada, pegamos a mesma
				session.setAttribute("usuAutenticado", usuarioAutenticado);
				session.setMaxInactiveInterval(300);
				
				req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);;//Redirecionamento
			}else{
				resp.getWriter().print("<script>window.alert('Erro ao autenticar');location.href='login.html';</script>");
			}
			
			
	}

}
