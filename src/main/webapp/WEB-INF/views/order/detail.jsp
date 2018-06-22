<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate title="">
	<div class="container">
		<div class=" col-sm-6 col-sm-offset-3">
			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="row">
						<div class="col-sm-4">
							Pedido: ${order.id}
						</div>
						
						<div class="col-sm-4">
							Data: ${order.formattedDate}
						</div>
						
						<div class="col-sm-4">
							Valor total: ${order.formattedTotalValue}
						</div>
					</div>
				</div>
				<div class="panel-body">
					<div class="col-sm-12">
						<table class="table table-bordered table-hover table-striped col-sm-6">
							<thead>
								<tr>
									<th>Produto</th>
									<th>Valor unitário</th>
									<th>Quantidade</th>
									<th>Valor</th>
								</tr>
							</thead>
							<tbody style="text-align: center;">
								<c:forEach items="${order.itens}" var="item">
									<c:url value="/product/${item.productId}" var="productDetailPath"/>
									<tr>
										<td><a href="${productDetailPath}">${item.productName}</a></td>
										<td>${item.formattedProductValue}</td>
										<td>${item.quantity}</td>
										<td>${item.formattedValue}</td>
									</tr>
									
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</tags:pageTemplate>