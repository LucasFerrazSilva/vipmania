<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate title="">
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
</tags:pageTemplate>