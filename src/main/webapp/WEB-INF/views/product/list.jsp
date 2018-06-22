<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate title="">
	<div class="container">	
		<h1>Lista de Produtos</h1>
		
		<hr />
		
		<table class="table table-bordered table-hover table-striped">
			<thead>
				<tr>
					<th>Nome</th>
					<th>Valor</th>
					<th>Categoria</th>
					<th>Valido De</th>
					<th>Valido Até</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${products}" var="product">
					<tr>
						<td><a href="<c:url value="/product/form/${product.id}" />">${product.name}</a></td>
						<td>${product.value}</td>
						<td>${product.categoryName}</td>
						<td>${product.formattedValidFrom}</td>
						<td>${product.formattedValidTo}</td>
					</tr>
				</c:forEach>
				
      			<security:authorize access="hasRole('ROLE_ADMIN')">
					<tr>
						<td style="text-align: center;" colspan="5">
							<a class="btn btn-success btn-block" href="<c:url value="/product/form/0" />" role="button">+</a>
						</td>
					</tr>
				</security:authorize>
			</tbody>
		</table>
	</div>
</tags:pageTemplate>