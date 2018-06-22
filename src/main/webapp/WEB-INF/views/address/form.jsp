<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate title="">
	<div class="container">
		<div class="col-sm-6 col-sm-offset-3">
			<h1>Cadastro de Endereços</h1>
			
			<hr />
			
			<c:url value="/address/save" var="savePath" />
			
			<div class="col-sm-12">
				<form:form servletRelativeAction="${savePath}" method="POST" commandName="address">
					<form:input type="hidden" path="id" />
					<input type="hidden" name="user" value="${address.userId}"/>
				
					<div class="row">
						<div class="form-group col-sm-8">
							<label>Recebedor</label>
							<form:input path="receiver" cssClass="form-control" />
							<form:errors path="receiver" />
						</div>
					
						<div class="form-group col-sm-4">
							<label>CEP</label>
							<form:input path="cep" cssClass="form-control" />
							<form:errors path="cep" />
						</div>
					</div>
				
					<div class="row">
						<div class="form-group col-sm-8">
							<label>Endereço</label>
							<form:input path="street" cssClass="form-control" />
							<form:errors path="street" />
						</div>
					
						<div class="form-group col-sm-4">
							<label>Número</label>
							<form:input path="number" cssClass="form-control" />
							<form:errors path="number" />
						</div>
					</div>
				
					<div class="row">
						<div class="form-group col-sm-8">
							<label>Bairro</label>
							<form:input path="district" cssClass="form-control" />
							<form:errors path="district" />
						</div>
					
						<div class="form-group col-sm-4">
							<label>Complemento</label>
							<form:input path="complement" cssClass="form-control" />
							<form:errors path="complement" />
						</div>
					</div>
				
					<div class="row">
						<div class="form-group col-sm-8">
							<label>Cidade</label>
							<form:input path="city" cssClass="form-control" />
							<form:errors path="city" />
						</div>
					
						<div class="form-group col-sm-4">
							<label>Estado</label>
							<form:input path="state" cssClass="form-control" />
							<form:errors path="state" />
						</div>
					</div>
				
					<div class="form-group">
						<label>Referências</label>
						<form:textarea path="reference" cssClass="form-control" />
						<form:errors path="reference" />
					</div>
					
					<hr />
					
					<button type="submit" class="btn btn-success btn-block">Cadastrar</button>
				</form:form>
			</div>
			
			<div class="col-sm-12">
				<hr />
			</div>
		</div>
	</div>
</tags:pageTemplate>