<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<c:set var="categoryPath" value="list/${category}?page=" />
<c:set var="searchPath" value="search?word=${word}&page=" />

<c:set var="relativePath" value="/product/${empty category ? searchPath : categoryPath }" />

<tags:pageTemplate title="">
	<div class="container">
		<div class="col-sm-12">
			<a href="<c:url value='/' />">Página inicial</a> /
			<c:choose>
				<c:when test="${not empty category}">
					${category}				
				</c:when>
				<c:otherwise>
					Busca: "${word}"					
				</c:otherwise>
			</c:choose>
		</div>
	
		<br />
		<div class="col-sm-12">
			<hr />
		</div>
	
		<c:choose>
			<c:when test="${not empty products}">
				<c:forEach items="${products}" var="product">
					<div class="col-sm-3 product-item text-center">
						<a class="product-item-a" href="<c:url value="/product/${product.id}" />">
							<img src="<c:url value="/image/${product.id}" />" height="150" width="150"/><br />
							${product.name} <br />
							${product.formattedValue}
						</a>
					</div>
				</c:forEach>
				
				<div class="col-sm-12">
					<hr />
				</div>

				<nav class="col-sm-12 text-center" aria-label="Page navigation">
					<ul class="pagination">
						<c:if test="${finalPage != 1}">
							<c:if test="${not empty actualPage && actualPage != 1}">
								<li>
									<a href="<c:url value="${relativePath}${actualPage - 1}" />" aria-label="Previous">
										<span aria-hidden="true">&laquo;</span>
									</a>
								</li>
							</c:if>
							<c:forEach items="${pages}" var="page">
								<li ${page eq actualPage ? 'class="active"' : ''}>
									<a href="<c:url value="${relativePath}${page}"/>">${page}</a>
								</li>
							</c:forEach>
							<c:if test="${not empty actualPage && actualPage != finalPage}">
								<li>
								<a href="<c:url value="${relativePath}${actualPage + 1}" />" aria-label="Next" >
										<span aria-hidden="true">&raquo;</span>
									</a>
								</li>
							</c:if>
						</c:if>
					</ul>
				</nav>
			</c:when>
			<c:otherwise>
				<div class="col-sm-12">
					<h3>Nenhum produto encontrado</h3>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</tags:pageTemplate>