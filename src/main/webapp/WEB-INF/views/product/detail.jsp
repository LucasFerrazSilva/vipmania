<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>VipMania</title>
</head>
<body>

	<div>
		Nome: ${product.name}
	</div>
	<div>
		Descrição: ${product.description}
	</div>
	<div>
		Valor: ${product.value}
	</div>

	<c:url value="/cart/add" var="addPath"/>

	<form:form servletRelativeAction="${addPath}" method="POST">
		<input type="hidden" name="id" value="${product.id}" />
		
		<button type="submit">Adicionar ao carrinho</button>
	</form:form>
	
</body>
</html>