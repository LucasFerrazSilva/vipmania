<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate title="Endereços">
	<div class="container col-sm-6 col-sm-offset-3">
		<h1>Lista de Endereços</h1>
	
		<hr />
		
		<div class="col-sm-12">
			<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<th>Endereços</th>
					</tr>
				</thead>
				<tbody style="text-align: center;">
					<c:if test="${empty adresses}">
						<tr><td>Nenhum endereço cadastrado</td></tr>
					</c:if>
					<c:forEach items="${adresses}" var="address">
						<tr>
							<td><a href="<c:url value="/address/form/${address.id}" />">${address.formattedAddress}</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<hr />
			
			<a class="btn btn-success btn-block" href="<c:url value="/address/form/0" />">Cadastrar novo endereço</a>
			
			<hr />
		</div>
	</div>
</tags:pageTemplate>