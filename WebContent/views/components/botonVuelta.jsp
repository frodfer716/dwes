<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${content == '/views/login.jsp' || content == '/views/register.jsp'}">
	<a id="volver" href="empresa?opcion=inicio">
    	<img src="img/x-solid.svg"></img>
    </a>
</c:if>

<c:if test="${not ((empty content && content == null) ||
				content == '/views/menu.jsp' || content == '/views/inicio.jsp' ||
				content == '/views/login.jsp' || content == '/views/register.jsp')}">
	<a id="volver" href="empresa?opcion=menu">
    	<img src="img/x-solid.svg"></img>
    </a>
</c:if>

<c:choose>
	<c:when test = "${(empty content && content == null) || content == '/views/menu.jsp' || content == '/views/inicio.jsp'}"></c:when>
	
	<c:when test="${content == '/views/editar.jsp'}">
		<a id="volver" href="javascript:history.back()">
	    	<img src="img/x-solid.svg"></img>
	    </a>
	</c:when>
	
	<c:when test = "${content == '/views/login.jsp' || content == '/views/register.jsp'}">
		<a id="volver" href="empresa?opcion=inicio">
	    	<img src="img/x-solid.svg"></img>
	    </a>
	</c:when>
	
	<c:otherwise>
		<a id="volver" href="empresa?opcion=menu">
    		<img src="img/x-solid.svg"></img>
    	</a>
	</c:otherwise>
</c:choose>