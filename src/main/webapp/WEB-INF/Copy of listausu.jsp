<%@page import="br.com.hightechcursos.entidades.Usuario"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista Usuários</title>
</head>
<body>

<%
	List<Usuario> listaUsuario = (List<Usuario>)request.getAttribute("chaveLista"); //Conversão: request recebe um object mas 
																						//mas queremos uma lista
	
	out.print("<table border = 1>");
	
	out.print("<tr> <th> ID </th>  <th> Nome </th> </tr>");


	for(Usuario usu : listaUsuario){
		out.print("<tr>");
		out.print("<td>" + usu.getNome()+ " </td> <td> " + usu.getId()+ "</td>");
		out.print("</tr>");

		
	}
%>

</body>
</html>