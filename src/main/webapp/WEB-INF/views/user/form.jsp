<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate title="">
	<div class="container">
		<div class="col-sm-6 col-sm-offset-3">
			<h1>Cadastro de Usuário</h1>
			
			<hr />
			
			<div class="col-sm-12">
				<c:url value="/user/changePasswordForm/${user.id}" var="changePasswordFormPath" />
				<c:url value="/user/save" var="savePath" />
				
				<form:form servletRelativeAction="${savePath}" method="POST" commandName="user">
					<form:hidden path="id" />
				
					<div class="form-group">
						<label>Nome</label>
						<form:input path="name" cssClass="form-control" />
						<form:errors path="name" />
					</div>
				
					<div class="form-group">
						<label>E-mail</label>
						<form:input path="email" cssClass="form-control" />
						<form:errors path="email" />
					</div>
					
					<c:choose>
						<c:when test="${empty user.password}">
							<div class="form-group">
								<label>Senha</label>
								<form:input path="password" cssClass="form-control" />
								<form:errors path="password" />
							</div>
						</c:when>
						<c:otherwise>
							<form:hidden path="password" />
						</c:otherwise>
					</c:choose>
					
					<hr />	
					
					<div class="row">
						<c:choose>
							<c:when test="${not empty user.id}">							
								<div class="col-sm-6">
									<button type="submit" class="btn btn-success btn-block">Cadastrar</button>
								</div>
								<div class="col-sm-6">
									<a class="btn btn-primary btn-block" href="${changePasswordFormPath}" role="button">Alterar senha</a>
								</div>
							</c:when>
							<c:otherwise>
								<div class="col-sm-12">
									<button type="submit" class="btn btn-success btn-block">Cadastrar</button>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
					
					<hr />
				</form:form>
			</div>
		</div>
	</div>
</tags:pageTemplate>