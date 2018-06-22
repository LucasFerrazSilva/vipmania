<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate title="Categorias">
	<div class="container">
		<div class="col-sm-6 col-sm-offset-3">	
			<h1>Lista de Categorias</h1>
		
			<hr />
			
			<table class="table table-bordered table-hover table-striped col-sm-6">
				<thead>
					<tr>
						<th>Nome</th>
					</tr>
				</thead>
				<tbody style="text-align: center;">
					<c:forEach items="${categories}" var="category">
						<tr>
							<td><a href="<c:url value="/category/form/${category.id}" />">${category.name}</a></td>
						</tr>
					</c:forEach>
					<tr>
						<td style="text-align: center;">
							<a class="btn btn-success btn-block" href="<c:url value="/category/form/0" />" role="button">+</a>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</tags:pageTemplate>