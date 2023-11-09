<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Editar empleado</title>

<link rel="stylesheet" href="css/styles.css">

</head>
<body>
    <a id="volver" href="empresa?opcion=listarEmpleado">
    	<img src="img/x-solid.svg"></img>
    </a>

	<div class="container">
	 <h1>Editar Empleado</h1>
	 <form action="empresa" method="post">
	  	<c:set var="empleado" value="${empleado}"></c:set>
	  	<input type="hidden" name="opcion" value="editar">
	  	<input type="hidden" name="dni" value="${empleado.dni}">
	  	
	  	<div id="busqueda">
			<label for="nombre">Nombre:</label>
		    <input type="text" name="nombre" size="50" value="${empleado.nombre}">
		</div>
		
	  	<div id="busqueda">
		    <label for="sexo">Sexo:</label>
		    <input type="text" name="sexo" size="50" value="${empleado.sexo}">
			<c:if test="${not empty sexoError}">
				<div class="error" id="errorEditar">${sexoError}</div>
			</c:if>
		</div>
		
	  	<div id="busqueda">
		    <label for="categoria">Categoria:</label>
		    <input type="text" name="categoria" size="50" value="${empleado.categoria}">
			<c:if test="${not empty categoriaError}">
				<div class="error">${categoriaError}</div>
			</c:if>
		</div>
		
	  	<div id="busqueda">
		    <label for="anyos">Años:</label>
		    <input type="text" name="anyos" size="50" value="${empleado.anyos}">
		   	<c:if test="${not empty anyosError}">
				<div class="error">${anyosError}</div>
			</c:if>
	   	</div>
	   	
	  	<input type="submit" value="Guardar">
	 </form>
  	</div>
</body>
</html>