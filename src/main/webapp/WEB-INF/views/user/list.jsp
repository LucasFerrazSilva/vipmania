<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate title="">
	<div class="container">
		<div class="col-sm-6 col-sm-offset-3">	
			<h1>Lista de Usuários</h1>
			
			<hr />
			
			<table class="table table-bordered table-hover table-striped">
				<thead>
					<tr>
						<th>Nome</th>
						<th>E-mail</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${users}" var="user">
						<tr>
							<td><a href="<c:url value="/user/form/${user.id}" />">${user.name}</a></td>
							<td>${user.email}</td>
						</tr>
					</c:forEach>
					<tr>
						<td style="text-align: center;" colspan="5">
							<a class="btn btn-success btn-block" href="<c:url value="/user/form/0" />" role="button">+</a>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</tags:pageTemplate>