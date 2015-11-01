<%@page import="br.com.hightechcursos.entidades.Usuario"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista Usuários</title>

<script type="text/javascript">
	function confirmaExclusao(id){
		if(window.confirm("Tem certeza que deseja excluir?"))
		location.href="usucontroller.do?acao=exc&id="+id;
			}
</script>
<script>
	function novo() {
		location.href='usucontroller.do?acao=cad';
	}
</script>

</head>
<body>
	<%@include file = "menu.jsp" %>
	
	

<%
	List<Usuario> listaUsuario = (List<Usuario>)request.getAttribute("chaveLista"); //Conversão: request recebe um object mas 
	//mas queremos uma lista
%>																					
	</br>
	<table border = 1>
	<tr> <th> ID </th>  <th> Nome </th>  <th> Ação </th> </tr>


	<%for(Usuario usu : listaUsuario){ %>
	<tr>
	<td><%=usu.getNome()%></td>
	<td><%=usu.getId()%></td>
	<td><a href="javascript:confirmaExclusao(<%=usu.getId()%>)">Excluir</a>  | <a href="usucontroller.do?acao=alt&id=<%=usu.getId()%>"> Editar</a></td> 	 
	
    </tr>	
<%}%>	
	
	</table>
	</br>
	<input type="button" value="Novo" onclick="javascript:novo()"/>

</body>
</html>