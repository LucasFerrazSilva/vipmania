<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate title="">
	<div class="container">
		<div class="col-sm-6 col-sm-offset-3">
			<h1>Cadastro de Categoria</h1>
			
			<hr />
			
			<c:url value="/category/save" var="savePath" />
			
			<form action="${savePath}" method="POST" accept-charset="UTF-8">
				<input type="hidden" name="id" value="${category.id}" />
			
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			
				<div class="form-group">
					<label>Nome</label>
					<input name="name" value="${category.name}" class="form-control" />
				</div>
				
				<hr />
				
				<button type="submit" class="btn btn-success btn-block">Cadastrar</button>
				
				<hr />
			</form>
		</div>
	</div>
</tags:pageTemplate>