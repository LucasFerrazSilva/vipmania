<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate title="">
	<div class="container">
		<div class="col-sm-6 col-sm-offset-3">
			<h1>Cadastro de Produto</h1>
			
			<hr />
			
			<c:url value="/product/save" var="savePath" />
			
			<input type="hidden" value="<c:url value="/size/list-items/"/>" id="size-list-items" />
			
			<form:form servletRelativeAction="${savePath}" method="POST" commandName="product" enctype="multipart/form-data">
				<form:input type="hidden" path="id" />
			
				<div class="form-group col-sm-12">
					<label>Nome</label>
					<form:input path="name" cssClass="form-control" />
					<form:errors path="name" />
				</div>
				
				<div class="form-group col-sm-12">
					<label>Descrição</label>
					<form:textarea path="description" rows="7" cols="20" cssClass="form-control" ></form:textarea>
					<form:errors path="description" />
				</div>
				
				<div class="form-group col-sm-6">
					<label>Valor</label>
					<form:input path="value" cssClass="form-control" />
					<form:errors path="value" />
				</div>
				
				<div class="form-group col-sm-6">
					<label>Categoria</label>
					<form:select path="category" cssClass="form-control">
						<form:options items="${categories}" itemValue="id" itemLabel="name"/>
					</form:select>
					<form:errors path="category" />
				</div>
				
				<div class="form-group col-sm-6">
					<label>Valido de</label>
					<form:input path="validFrom" cssClass="form-control" />
					<form:errors path="validFrom" />
				</div>
				
				<div class="form-group col-sm-6">
					<label>Valido até</label>
					<form:input path="validTo" cssClass="form-control" />
					<form:errors path="validTo" />
				</div>
				
				<div class="form-group col-sm-12">
					<label>Tipo de tamanhos</label>
					<form:select path="size" cssClass="form-control" id="select-size">
						<form:options items="${sizes}" itemValue="id" itemLabel="name"/>
					</form:select>
					<form:errors path="category" />
				</div>	
				
				<div class="col-sm-12 sizes">
					<label>Tamanhos</label>
					<c:forEach items="${sizeItems}" var="item" varStatus="i">
						<c:set var="sizeQuantity" value="${product.getQuantity(item.id)}" />
					
						<div class="form-group col-sm-12">
							<input type="hidden" name="quantities[${i.index}].id" value="${sizeQuantity.id}" />
							<input type="hidden" name="quantities[${i.index}].product" value="${product.id}" />
							<input type="hidden" name="quantities[${i.index}].sizeItem" value="${item.id}" />
							<label>${item.name}</label>
							<input type="number" class="form-control" name="quantities[${i.index}].quantity" value="${sizeQuantity.quantity}" />														
						</div>			
					</c:forEach>
				</div>
				
<!-- 				<div class="col-sm-12 sizes"> -->
<!-- 					<label>Tamanhos</label> -->
<%-- 					<c:forEach items="${product.quantities}" var="sizeQuantity" varStatus="i"> --%>
<!-- 						<div class="form-group col-sm-12"> -->
<%-- 							<input type="hidden" name="quantities[${i.index}].id" value="${sizeQuantity.id}" /> --%>
<%-- 							<input type="hidden" name="quantities[${i.index}].product" value="${product.id}" /> --%>
<%-- 							<input type="hidden" name="quantities[${i.index}].sizeItem" value="${sizeQuantity.sizeItemId}" /> --%>
<%-- 							<label>${sizeQuantity.sizeItemName}</label> --%>
<%-- 							<input type="number" class="form-control" name="quantities[${i.index}].quantity" value="${sizeQuantity.quantity}" />														 --%>
<!-- 						</div>			 -->
<%-- 					</c:forEach> --%>
<!-- 				</div> -->
				
				<div class="form-group col-sm-12 text-center">
					<label>Foto</label>
					<input type="file" name="photo"/>
					<input type="hidden" name="photoPath" value="${product.photoPath}" />
					<img src="<c:url value="/image/${product.id}" />" height="400" width="400"/><br />
					
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
			</form:form>
		</div>
	</div>
</tags:pageTemplate>

<script>
	$('#select-size').on('change', function(){
		var sizeId = $('#select-size').find(':selected').val();
		var url = $('#size-list-items').val() + sizeId;
		
		var textToAppend = '<div class="form-group col-sm-12">'
								+ '<input type="hidden" name="quantities[index].id" />'
								+ '<input type="hidden" name="quantities[index].product" value="productId" />'
								+ '<input type="hidden" name="quantities[index].sizeItem" value="sizeItemId" />'
								+ '<label>sizeItemName</label>'
								+ '<input type="number" class="form-control" name="quantities[index].quantity" />'														
							+ '</div>';
		
		var i = 0;	

		var productId = $('input#id').val();
							
		$.ajax({
			url:  url,
			dataType: 'json',
			method: 'GET',
			success: function(data){
				$('div.sizes').empty();
				
				$('div.sizes').append('<label>Tamanhos</label>');
				
				var sizes = data;
				
				$.each(sizes, function(){
					var newInput = textToAppend;
					
					newInput = newInput.replace(/index/g, i);
					newInput = newInput.replace(/productId/g, productId);
					newInput = newInput.replace(/sizeItemId/g, this.id);
					newInput = newInput.replace(/sizeItemName/g, this.name);
					
					$('div.sizes').append(newInput);
					
					i++;
				});
			},
			error: function(){
				console.log('fail');
			}
		});
	});

	if($('div.sizes > div')[0] == undefined)
		$('#select-size').trigger('change');
	
</script>