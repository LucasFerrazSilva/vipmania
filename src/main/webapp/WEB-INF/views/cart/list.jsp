<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>VipMania</title>
</head>
<body>

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
						<form action="${s:mvcUrl('CC#remove').arg(0, item.product.id).build()}" method="POST">
							<button type="submit">X</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<p>Valor da compra: ${cart.totalValue}</p>
	
	<form action="${s:mvcUrl('CC#finalize').build()}" method="POST">
		<button type="submit">Finalizar compra</button>
	</form>

</body>
</html>