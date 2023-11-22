<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:th="http://www.thymeleaf.org">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Menu empleados</title>

	<link rel="stylesheet" th:href="@{/static/css/styles.css}">
</head>
<body>
	<div th:replace="views/components/botonVuelta :: botonVuelta"></div>

	<div th:switch="${empty content or content == null}">
		<p th:case="true" th:replace="views/inicio :: inicio"></p>
		<div th:case="false" th:replace="${content} :: ${content}"></div>
	</div>
</body>
</html>