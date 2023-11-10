<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
	 <h1>Iniciar Sesión</h1>
	 <form action="empresa" method="post">
	  	<c:set var="usuario" value="${usuario}"></c:set>
	  	<input type="hidden" name="opcion" value="loginUsuario">
	  	
	  	<div id="busqueda">
			<label for="email">Correo Electrónico:</label>
		    <input type="text" name="email" id="email" required>
		</div>
		
	  	<div id="busqueda">
		    <label for="password">Contraseña:</label>
		    <input type="password" name="password" id="password" placeholder="Mínimo 8 caracteres"
		    		pattern=".{8,}" title="La contraseña debe tener al menos 8 caracteres"
		    		maxlength="15" required>
		</div>
		
		
		<c:if test="${not empty mensajeError}">
			<div class="error">${mensajeError}</div>
		</c:if>
	   	
	  	<input type="submit" value="Iniciar sesión">
	 </form>
	 
	 <p><a href="empresa?opcion=registration">¿No tienes cuenta?<br>Regístrate</a></p>
</div>