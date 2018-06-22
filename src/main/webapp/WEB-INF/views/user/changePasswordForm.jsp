<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate title="">
	<div class="container">
		<div class="col-sm-6 col-sm-offset-3">
			<h1>Alterar senha</h1>
			
			<hr />
			
			<div class="col-sm-12">
				<c:url value="/user/changePassword" var="savePath" />
				
				<form:form servletRelativeAction="${savePath}" method="POST" commandName="user">
					<form:hidden path="id" />
					<form:hidden path="name" />
					<form:hidden path="email" />
					<form:hidden path="password" />
		
					<div class="form-group">
						<label>Senha atual</label>
						<form:password path="actualPassword" cssClass="form-control" />
						<form:errors path="actualPassword" />
					</div>
		
					<div class="form-group">
						<label>Nova senha</label>
						<form:input path="newPassword" cssClass="form-control" />
						<form:errors path="newPassword" />
					</div>
					
					<hr />
				
					<button type="submit" class="btn btn-success btn-block">Alterar</button>
					
					<hr />
				</form:form>
			</div>
		</div>
	</div>
</tags:pageTemplate>

<script>
	$('input#password').attr('value', '')
</script>