<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
	 <h1>Iniciar Sesi�n</h1>
	 <form action="empresa" method="post">
	  	<c:set var="usuario" value="${usuario}"></c:set>
	  	<input type="hidden" name="opcion" value="login">
	  	
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
	   	
	  	<input type="submit" value="Iniciar sesi�n">
	 </form>
	 
	 <p><a href="empresa?opcion=registration">�No tienes cuenta?<br>Reg�strate</a></p>
</div>