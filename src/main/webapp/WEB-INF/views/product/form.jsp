<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate title="">
	<div class="container">
		<h1>Cadastro de Produto</h1>
		
		<c:url value="/product/save" var="savePath" />
		
		<form:form servletRelativeAction="${savePath}" method="POST" commandName="product" enctype="multipart/form-data">
			<div class="form-group">
				<label>Nome</label>
				<form:input path="name" cssClass="form-control" />
				<form:errors path="name" />
			</div>
			
			<div class="form-group">
				<label>Descrição</label>
				<form:textarea path="description" rows="3" cols="20" cssClass="form-control" ></form:textarea>
				<form:errors path="description" />
			</div>
			
			<div class="form-group">
				<label>Valor</label>
				<form:input path="value" cssClass="form-control" />
				<form:errors path="value" />
			</div>
			
			<div class="form-group">
				<label>Categoria</label>
				<form:input path="category" cssClass="form-control"  />
				<form:errors path="category" />
			</div>
			
			<div class="form-group">
				<label>Valido de</label>
				<form:input path="validFrom" cssClass="form-control" />
				<form:errors path="validFrom" />
			</div>
			
			<div class="form-group">
				<label>Valido até</label>
				<form:input path="validTo" cssClass="form-control" />
				<form:errors path="validTo" />
			</div>
			
			<div class="form-group">
				<label>Foto</label>
				<input type="file" name="photo" />
			</div>
			
	<%-- 		<c:forEach items="${tipos}" var="tipoPreco" varStatus="status"> --%>
	<%-- 			<input type="text" name="precos[${status.index}]"> --%>
	<%-- 		</c:forEach> --%>
			
			<button type="submit" class="btn btn-default">Cadastrar</button>
		</form:form>
	</div>
</tags:pageTemplate>