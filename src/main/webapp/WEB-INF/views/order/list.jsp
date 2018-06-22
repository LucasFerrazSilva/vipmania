<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate title="Pedidos">
	<div class="container">
		<div class=" col-sm-8 col-sm-offset-2">
			<div class="">	
				<h1>Lista de Pedidos</h1>
			</div>
			
			<hr />
			
			<div class="col-sm-12">
				<c:if test="${empty orders}">
					<h3>Nenhum pedido encontrado</h3>
				</c:if>
			
				<c:forEach items="${orders}" var="order">
					<div class="panel panel-default">
						<div class="panel-heading">
							<div class="row">
								<div class="col-sm-4">
									Nº do Pedido: ${order.id}
								</div>
								
								<div class="col-sm-4 text-center">
									Data: ${order.formattedDate}
								</div>
								
								<div class="col-sm-4 text-right">
									Valor total: ${order.formattedTotalValue}
								</div>
							</div>
						</div>
						<div class="panel-body">
							<div class="col-sm-12">
								<c:forEach items="${order.itens}" var="item" varStatus="i">
									<div class="row">
										
										<div class="col-sm-3 allign-vertical-center">
											<img src="<c:url value="/image/${item.productId}" />" height="100" width="100"/>
										</div>
										
										<div class="col-sm-8 allign-vertical-center">
											<a href="<c:url value="/product/${item.productId}" />">${item.productName}</a> (${item.quantity})<br />
											Tamanho ${item.sizeItemName} <br /> 
											${item.formattedValue}<br />
										</div>
										
										<hr />
									</div>
								</c:forEach>
								
								<div class="col-sm-12 text-center">
									<c:set var="address" value="${order.address}" />
									
									${address.receiver}<br />
									${address.street}, ${address.number} ${address.complement} - ${address.district} - ${address.city}, ${address.state} ${address.cep}<br /> 
									${address.reference}<br />
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			
			
			<div class="col-sm-12">
				<hr />
			</div>
		</div>				
	</div>
</tags:pageTemplate>