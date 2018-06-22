<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate title="">
	<div class="container">
		<div class="col-sm-12">
			Página inicial
		</div>
	
		<br />
		<div class="col-sm-12">
			<hr />
		</div>
	
		<c:forEach items="${products}" var="product">
			<div class="col-sm-3 product-item text-center">
				<a class="product-item-a" href="<c:url value="/product/${product.id}" />">
					<img src="<c:url value="/image/${product.id}" />" height="150" width="150"/><br />
					${product.name} <br />
					${product.formattedValue}
				</a>
			</div>
		</c:forEach>
	</div>
</tags:pageTemplate>