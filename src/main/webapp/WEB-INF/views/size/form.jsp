<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate title="">
	<div class="container">
		<div class="col-sm-6 col-sm-offset-3">
			<h1>Cadastro de Tamanho</h1>
			
			<hr />
			
			<c:url value="/size/save" var="savePath" />
			
			<div class="col-sm-12">
				<form action="${savePath}" method="POST" accept-charset="UTF-8">
					<input type="hidden" name="id" value="${size.id}" />
				
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				
					<div class="form-group col-sm-12">
						<label>Nome</label>
						<input name="name" value="${size.name}" class="form-control" />
					</div>
					
					<div id="div-itens" class="form-group col-sm-12">
						<label>Itens</label>
						
						<c:forEach items="${items}" var="item" varStatus="i">
							<input class="btn-item-index" type="hidden" name="itens[${i.index}].id" value="${item.id}" data-index="${i.index}"/>
							<input type="hidden" name="itens[${i.index}].size" value="${size.id}" />
							<input type="text" name="itens[${i.index}].name" value="${item.name}" class="form-control  text-center" />					
						</c:forEach>
					</div>
					
					<div class="col-sm-12 text-center">
						<button id="btn-add-item" type="button" class="btn btn-success">+</button>
					</div>
					
					<div class="col-sm-12">
						<hr />
					</div>
					
					<div class="col-sm-12">
						<button type="submit" class="btn btn-success btn-block">Cadastrar</button>
					</div>
					
					<div class="col-sm-12">
						<hr />
					</div>
				</form>
			</div>
		</div>
	</div>
</tags:pageTemplate>

<script>
	$('#btn-add-item').on('click', function(){
		var i = 0;
		
		$('.btn-item-index').each(function(){
			var j = $(this).data('index');
			i = (j > i ? j : i); 
		});
		
		i++;
		
		var input = '<input class="btn-item-index" type="hidden" name="itens[' + i + '].id" data-index="' + i + '"/>'
					+ '<input type="hidden" name="itens[' + i + '].size" value="' + ${size.id} + '"/>'
					+ '<input type="text" name="itens[' + i + '].name" class="form-control text-center" />';
		
		$('#div-itens').append(input);
	});
</script>