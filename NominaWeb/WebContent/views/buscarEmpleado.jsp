<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Buscar Empleado</title>

<link rel="stylesheet" href="css/styles.css">

</head>
<body>
    <a id="volver" href="empresa?opcion=index">
    	<img src="img/x-solid.svg"></img>
    </a>

	<div class="container">
		<h1>Buscar Empleado</h1>

		<form method="post" action="empresa">
			<label for="dniBusqueda">Buscar por DNI:</label> <input
				type="text" id="dniBusqueda" name="valorBusqueda" /> <input
				type="hidden" name="opcion" value="buscarEmpleado"> <input
				type="hidden" name="tipoBusqueda" value="dni"> <input
				type="submit" value="Buscar">
				<c:if test="${not empty dniError}">
				    <div class="error">${dniError}</div>
				</c:if>
		</form>

		<form method="post" action="empresa">
			<label for="nombreBusqueda">Buscar por Nombre:</label> <input
				type="text" id="nombreBusqueda" name="valorBusqueda" /> <input
				type="hidden" name="opcion" value="buscarEmpleado"> <input
				type="hidden" name="tipoBusqueda" value="nombre"> <input
				type="submit" value="Buscar">
		</form>

		<form method="post" action="empresa">
			<label for="sexoBusqueda">Buscar por Sexo:</label> <input
				type="text" id="sexoBusqueda" name="valorBusqueda" /> <input
				type="hidden" name="opcion" value="buscarEmpleado"> <input
				type="hidden" name="tipoBusqueda" value="sexo"> <input
				type="submit" value="Buscar">
				<c:if test="${not empty sexoError}">
				    <div class="error">${sexoError}</div>
				</c:if>
		</form>

		<form method="post" action="empresa">
			<label for="categoriaBusqueda">Buscar por Categoria:</label> <input
				type="text" id="categoriaBusqueda" name="valorBusqueda" /> <input
				type="hidden" name="opcion" value="buscarEmpleado"> <input
				type="hidden" name="tipoBusqueda" value="categoria"> <input
				type="submit" value="Buscar">
				<c:if test="${not empty categoriaError}">
				    <div class="error">${categoriaError}</div>
				</c:if>
		</form>
		
		<form method="post" action="empresa" id="ultimoForm">
			<label for="anyosBusqueda">Buscar por A�os:</label> <input
				type="text" id="anyosBusqueda" name="valorBusqueda" /> <input
				type="hidden" name="opcion" value="buscarEmpleado"> <input
				type="hidden" name="tipoBusqueda" value="anyos"> <input
				type="submit" value="Buscar">
				<c:if test="${not empty anyosError}">
				    <div class="error">${anyosError}</div>
				</c:if>
		</form>

		<c:if test="${not empty lista}">
			<div class="tabla" id="lista">
				<table border="1">
					<tr>
						<td>DNI</td>
						<td>Nombre</td>
						<td>Sexo</td>
						<td>Categor�a</td>
						<td>A�os Trabajados</td>
						<td colspan="2">Accion</td>
					</tr>
					<c:forEach var="empleado" items="${lista}">
						<tr>
							<td>${empleado.dni}</td>
							<td>${empleado.nombre}</td>
							<td>${empleado.sexo}</td>
							<td>${empleado.categoria}</td>
							<td>${empleado.anyos}</td>
							<td><a
							href="empresa?opcion=eliminar&dni=<c:out value="${ empleado.dni}"></c:out>">
								Eliminar </a></td>
							<td><a
							href="empresa?opcion=meditar&dni=<c:out value="${ empleado.dni}"></c:out>">
								Editar </a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</c:if>
	</div>
</body>
</html>