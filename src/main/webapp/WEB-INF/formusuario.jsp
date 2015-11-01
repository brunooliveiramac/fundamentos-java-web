<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@page import="br.com.hightechcursos.entidades.Usuario"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insert title here</title>



</head>
<body>
	<%@include file = "menu.jsp" %>

		<!-- Comments are not displayed in the browser
		Action: ControladorServlet (Servlet: Entende Java (Request e Response) e devolve para a Tela (Criada Pelo WEB Container))
		Method: Post ou Get
		Type: Texto, Botão etc..
		Value: display, o que vai paga o req.getParameter -->
		
		<%
		Usuario usu = (Usuario)request.getAttribute("usu"); //carrega o obj usuario dentro do request
		
		%>
		
		<form action="usucontroller.do" method="post">
			ID: <input type="number" name="id" value="<%=usu.getId()%>"/>
			Nome: <input type="text" name="nome" value="<%=usu.getNome()%>"/>
			Login: <input type="text" name="login" value="<%=usu.getLogin()%>"/>
			Senha: <input type="password" name="senha" value="<%=usu.getSenha()%>"/>
			
		<input type="submit" value="Salvar"/>
		
		
		</form>
		
		
</body>
</html>