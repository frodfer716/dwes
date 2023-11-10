<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
	 <h1>Registrarse</h1>
	 <form action="empresa" method="post">
	  	<c:set var="usuario" value="${usuario}"></c:set>
	  	<input type="hidden" name="opcion" value="registrarUsuario">
	  	
	  	<div id="busqueda">
			<label for="name">Nombre:</label>
		    <input type="text" name="name" required maxlength="30">
		</div>
	  	
	  	<div id="busqueda">
			<label for="surnames">Apellidos:</label>
		    <input type="text" name="surnames" required maxlength="50">
		</div>
	  	
	  	<div id="busqueda">
			<label for="email">Correo Electr�nico:</label>
		    <input type="text" name="email" id="email" required>
		</div>
		
	  	<div id="busqueda">
		    <label for="password">Contrase�a:</label>
		    <input type="password" name="password" id="password" placeholder="M�nimo 8 caracteres"
		    		pattern=".{8,}" title="La contrase�a debe tener al menos 8 caracteres"
		    		maxlength="15" required>
		</div>
		
		<c:if test="${not empty mensajeError}">
			<div class="error">${mensajeError}</div>
		</c:if>
	   	
	  	<input type="submit" value="Reg�strate">
	 </form>
	 
	 <p><a href="empresa?opcion=log">�Ya tienes cuenta?<br>Inicia sesi�n</a></p>
</div>