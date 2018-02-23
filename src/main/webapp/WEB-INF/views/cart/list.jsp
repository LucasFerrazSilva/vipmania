<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate title="">

<jsp:attribute name="extraScripts">
	<script>
		
	</script>
</jsp:attribute>

<jsp:body>
	<h1>Carrinho</h1>

	<div>${message}</div>
	
	<table>
		<thead>
			<tr>
				<th>Nome</th>
				<th>Valor unitário</th>
				<th>Quantidade</th>
				<th>Valor total</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${itens}" var="item" varStatus="i">
				<tr>
					<td>${item.name}</td>
					<td>${item.value}</td>
					<td>${item.quantity}</td>
					<td>${item.totalValue}</td>
					<td>
						<c:url value="/cart/remove/${item.productId}" var="removeItemPath"/>
						<form:form action="${removeItemPath}" method="POST">
							<button type="submit">X</button>
						</form:form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<p>Valor da compra: ${cart.totalValue}</p>
	
	<c:url value="/cart/finalize" var="finalizePath"/>
	<form:form action="${finalizePath}" method="POST">
		<button type="submit">Finalizar compra</button>
	</form:form>

</jsp:body>	
</tags:pageTemplate>