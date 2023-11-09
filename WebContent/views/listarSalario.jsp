<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.empresa.model.Empleado, com.empresa.model.Nomina"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Listar Salarios</title>

<link rel="stylesheet" href="css/styles.css">

</head>
<body>
    <a id="volver" href="empresa?opcion=index">
    	<img src="img/x-solid.svg"></img>
    </a>
  
	<div class="container">
		<h1>Buscar Nóminas</h1>

		<form method="post" action="empresa" id="ultimoForm">
			<label for="dniBusqueda">Buscar por DNI:</label> <input type="text"
				id="dniBusqueda" name="dniBusqueda" /> <input type="hidden"
				name="opcion" value="buscarSalario"> <input type="submit"
				value="Buscar" />
				<c:if test="${not empty dniError}">
				    <div class="error">${dniError}</div>
				</c:if>
		</form>

		<div class="tabla" id="lista">
			<%if (request.getAttribute("empleado") != null && request.getAttribute("sueldo") != null) {%>
				<table border="1">
					<tr>
						<td>DNI</td>
						<td>Nomina</td>
					</tr>
					<tr>
						<td><%=request.getAttribute("empleado") != null ? ((Empleado) request.getAttribute("empleado")).getDni() : ""%></td>
						<td><%=request.getAttribute("sueldo") != null ? request.getAttribute("sueldo") : ""%></td>
					</tr>
				</table>
			<%}%>
		</div>
	</div>
</body>
</html>