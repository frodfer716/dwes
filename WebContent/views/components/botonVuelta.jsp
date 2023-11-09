<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not ((empty content && content == null) || content == '/views/menu.jsp' || content == '/views/inicio.jsp')}">
	<a id="volver" href="javascript:history.back()">
    	<img src="img/x-solid.svg"></img>
    </a>
</c:if>