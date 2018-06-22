<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate title="">
	<div class="container">
		<div class="col-sm-6 col-sm-offset-3">
			<h1>Detalhes do Endereço</h1>
			
			<hr />
			
			<div class="col-sm-12">
				<strong>${address.receiver}</strong><br />
				${address.street}, ${address.number} ${address.complement}<br />
				${address.district}<br />
				${address.city}, ${address.state} ${address.cep}<br /> 
				${address.reference}<br />
			</div>
			
			<div class="col-sm-12">
				<hr />
			</div>
		</div>
	</div>
</tags:pageTemplate>