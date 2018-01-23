<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>VipMania</title>
	
	<c:url value="/resources" var="resourcePath" />
	
	<link rel="stylesheet" href="${resourcePath}/css/bootstrap.min.css">

	<link rel="stylesheet" href="${resourcePath}/css/bootstrap-theme.min.css">
	
	<script src="${resourcePath}/js/jquery-3.2.1.js" ></script>
	<script src="${resourcePath}/js/bootstrap.min.js"></script>	
</head>
<body>
	<nav class="navbar navbar-default">
	  <div class="container-fluid">
	    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	      <ul class="nav navbar-nav">
	        <li><a href="${s:mvcUrl('HC#index').build()}">Início</a></li>
	      </ul>
	      <security:authorize access="hasRole('ROLE_ADMIN')">
		      <ul class="nav navbar-nav">
		        <li class="dropdown">
		          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Produtos <span class="caret"></span></a>
		          <ul class="dropdown-menu">
		            <li><a href="${s:mvcUrl('PC#list').build()}">Lista</a></li>
		            <li><a href="${s:mvcUrl('PC#form').build()}">Novo</a></li>
		          </ul>
		        </li>
		      </ul>
	      </security:authorize>
	      <ul class="nav navbar-nav navbar-right">
	        <li><a href="${s:mvcUrl('CC#list').build()}">Carrinho (${cart.quantity})</a></li>
	      </ul>
	      <security:authorize access="isAuthenticated()">
		      <ul class="nav navbar-nav navbar-right">
		      	<li><a href="<c:url value='/logout' />">Sair</a></li>
		      </ul>
		      <ul class="nav navbar-nav navbar-right">
		      	<li><a href="#"><security:authentication property="principal.username" /></a></li>
		      </ul>
		  </security:authorize>
	    </div><!-- /.navbar-collapse -->
	  </div><!-- /.container-fluid -->
	</nav>
		
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
</body>
</html>