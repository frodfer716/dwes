<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menu empleados</title>

<link rel="stylesheet" href="css/styles.css">

</head>
<body>
	<%@ include file="views/components/botonVuelta.jsp" %>

	<c:choose>
	    <c:when test="${empty content && content == null}">
	        <%@ include file="views/menu.jsp" %>
	    </c:when>
	    <c:otherwise>
	        <jsp:include page="${content}" />
	    </c:otherwise>
	</c:choose>
</body>
</html>