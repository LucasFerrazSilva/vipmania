<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate title="Categorias">
	<div class="container">
		<div class="col-sm-6 col-sm-offset-3">
			<h1>Login</h1>
			
			<hr />
			
			<div class="col-sm-12">
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
				
					<hr />
					
					<div class="row">
						<div class="col-sm-6">
							<button type="submit" class="btn btn-success btn-block">Logar</button>
						</div>
						<div class="col-sm-6">
							<a class="btn btn-primary btn-block" href="<c:url value="/user/createAccount" />" role="button">Cadastrar</a>
						</div>
					</div>
				
					<hr />
				</form:form>
			</div>
		</div>
	</div>
</tags:pageTemplate>