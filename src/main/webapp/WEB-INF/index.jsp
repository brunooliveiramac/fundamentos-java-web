<%@page import="br.com.hightechcursos.entidades.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%@include file="menu.jsp"%> 

	<h3>Olá Usuario <%
	Usuario usu = (Usuario)request.getSession().getAttribute("usuAutenticado");
	out.print(usu.getLogin());
	
	%> </h3>
</body>
</html>