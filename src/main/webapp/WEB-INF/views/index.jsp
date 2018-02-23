<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate title="">
	<div class="container">	
		<div>${message}</div>
		
		<table class="table table-bordered table-hover table-striped">
			<thead>
				<tr>
					<th>Nome</th>
					<th>Valor</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${products}" var="product">
					<tr>
						<td><a href="${s:mvcUrl('PC#detail').arg(0, product.id).build()}">${product.name}</a></td>
						<td>${product.value}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</tags:pageTemplate>