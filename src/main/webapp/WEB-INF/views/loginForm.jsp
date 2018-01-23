<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

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
	        <li><a href="${spring:mvcUrl('HC#index').build()}">Início</a></li>
	      </ul>
	      <ul class="nav navbar-nav">
	        <li class="dropdown">
	          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Produtos <span class="caret"></span></a>
	          <ul class="dropdown-menu">
	            <li><a href="${spring:mvcUrl('PC#list').build()}">Lista</a></li>
	            <li><a href="${spring:mvcUrl('PC#form').build()}">Novo</a></li>
	          </ul>
	        </li>
	      </ul>
	      <ul class="nav navbar-nav navbar-right">
	        <li><a href="${spring:mvcUrl('CC#list').build()}">Carrinho (${cart.quantity})</a></li>
	      </ul>
	    </div><!-- /.navbar-collapse -->
	  </div><!-- /.container-fluid -->
	</nav>
		
	<div class="container">
		<h1>Login</h1>
		
		<c:url value="/product/save" var="savePath" />
		
		<form:form servletRelativeAction="/login" method="POST">
			<div class="form-group">
				<label>E-mail</label>
				<input type="text" name="username" class="form-control" />
			</div>
			
			<div class="form-group">
				<label>Senha</label>
				<input type="password" name="password" class="form-control" />
			</div>
			
			<button type="submit" class="btn btn-default">Logar</button>
		</form:form>
	</div>

</body>
</html>