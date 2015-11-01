package br.com.hightechcursos.interceptor;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

                                                      //Filtra qualquer Request depois da raiz do sistema
@WebFilter(dispatcherTypes={DispatcherType.REQUEST} , urlPatterns = "/*")
public class FiltroAutenticacao implements Filter {

	@Override
	public void destroy() {

		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequst = (HttpServletRequest) request; //Converte o servlet reqst em algo mais específico
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		//Caminho da requisição, URL
		String uri = httpRequst.getRequestURI();
		HttpSession session = httpRequst.getSession(false);
		
		if(session != null || uri.lastIndexOf("login.html") != -1 || uri.lastIndexOf("autenticador.do") != -1){
		
		chain.doFilter(request, response);//Fiscalizando os requests
		}else{
			httpResponse.sendRedirect("login.html");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

		
	}

}
