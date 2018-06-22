<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate title="">
	<div class="container">
		<a href="<c:url value='/' />">Página inicial</a> /
		<a href="<c:url value='/product/list/${product.categoryName}' />">${product.categoryName}</a> /
		${product.name}

		<hr />

		<input type="hidden" value="<c:url value="/calcular-frete/"/>" id="calcular-frete-url" />

		<div class="col-sm-12">
			<input type="hidden" id="productId" value="${product.id}" />
		
			<div class="col-sm-4 text-center">
				<img src="<c:url value="/image/${product.id}" />" height="400" width="400"/>
			</div>
		
			<div class="col-sm-5 col-sm-offset-3">
				<div>
					${product.formattedDescription}
				</div>
				
				<hr />
				
				<div>
					Por: ${product.formattedValue}
				</div>
				
				<hr />

				<c:choose>
					<c:when test="${not empty product.availableSizes}">
						<div>
							<ul class="nav nav-pills">
								<c:forEach items="${product.availableSizes}" var="sizeItem">
									<li role="presentation">
										<button type="button" class="btn btn-default" data-id="${sizeItem.id}" data-name="${sizeItem.name}">${sizeItem.name}</button>
									</li>
								</c:forEach>
							</ul>
						</div>
						
						<hr />
					
						<div id="div-calcular-frete" class="row">
							<div class="col-sm-6">
								<input id="input-cep" type="text" name="cep" class="form-control" data-toggle="tooltip" data-placement="top" title="Ex: 01311-200"/>
							</div>
							
							<div class="col-sm-6">
								<button id="btn-calcular-frete" type="button" class="btn btn-success btn-block" data-loading-text="Calculando..." disabled="disabled">Calcular frete</button>
							</div>
						</div>
						
						<div id="div-frete">
						
						</div>
						
						<c:url value="/cart/add/" var="addPath"/>
					
						<hr />
							
						<c:if test="${product.buyable}">
							<form:form servletRelativeAction="${addPath}" id="form-add-to-cart" data-id="0">
								<button id="button-add-to-cart" type="submit" class="btn btn-success btn-block" disabled="disabled">
									Adicionar ao carrinho
								</button>
							</form:form>
							
							<hr />
						</c:if>
					</c:when>
					<c:otherwise>
						<h3 class="text-center"><em>Produto indisponível</em></h3>
						<hr />
					</c:otherwise>
				</c:choose>
			</div>
			<div class="col-sm-12">
				<hr />
			</div>
			<div class="col-sm-12 text-center">
				<div class="row">
					<div class="col-sm-4 col-sm-offset-4">
						<h2>Relacionados</h2>
						<hr />
					</div>
				</div>
				
				<c:forEach items="${relatedProducts}" var="relatedProduct">
					<div class="col-sm-3 product-item text-center">
						<a class="product-item-a" href="<c:url value="/product/${relatedProduct.id}" />">
							<img src="<c:url value="/image/${relatedProduct.id}" />" height="200" width="200"/><br />
							${relatedProduct.name}
						</a>
					</div>
				</c:forEach>
			</div>
			<div class="col-sm-12">
				<hr />
			</div>
		</div>
	</div>
</tags:pageTemplate>

<script>
	$('#input-cep').tooltip();

	function disabledEnableButton(){
		$('#button-add-to-cart').attr('disabled', $('.nav-pills button.btn-primary').length == 0);
	}
	
	$('.nav-pills button').on('click', function(){ 
		$(this).toggleClass('btn-primary')
		
		disabledEnableButton();
		
		var productId = $('#productId').val();
		
		var sizeItemId = $(this).data('id');
		var sizeName = $(this).data('name');
		
		var index = $('#form-add-to-cart').data('id');

		if($('#form-add-to-cart .size-' + sizeName).length > 0){
			$('#form-add-to-cart .size-' + sizeName).remove();
			
			$('#form-add-to-cart').data('id', index - 1);
		}
		else{
			$('#form-add-to-cart').data('id', index + 1);
			
			var inputsToAppend = '<div class="size-item sizeName">'
								+ '<input type="hidden" name="itens[index].product" value="prodId" />'
								+ '<input type="hidden" name="itens[index].productId" value="prodId" />'
								+ '<input type="hidden" name="itens[index].quantity" value="1" />'
								+ '<input type="hidden" class="input-size-item-id" name="itens[index].sizeItem" value="sizeItemId" />'
								+ '</div>';
								
			inputsToAppend = inputsToAppend.replace(/prodId/g, productId);
			inputsToAppend = inputsToAppend.replace(/sizeItemId/g, sizeItemId);
			inputsToAppend = inputsToAppend.replace(/sizeName/g, 'size-' + sizeName);
			
			$('#form-add-to-cart').append(inputsToAppend);
		}
	});
	
	$('#form-add-to-cart').on('submit', function(){
		var i = 0;
		
		$(this).find('.size-item').each(function(){
			$(this).find('input').each(function(){
				 $(this).attr('name', $(this).attr('name').replace(/index/g, i)); 
			});
			
			i++;
		});
		
		return true; 
	});
	
	$('#input-cep').on('keyup', function(){
		$('#btn-calcular-frete').attr('disabled', ($('#input-cep').val().length != 9));
	});
	
	$('#btn-calcular-frete').on('click', function(){
		var url = $('#calcular-frete-url').val() + $('#input-cep').val().replace('-', '');
		
		var $btn = $(this); 
			
		$btn.button('loading');
		
		$.ajax({
			url: url,
			dataType: 'json',
			success: function(data){
				var frete = data;
				
				var html = '<br /> Correios - R$ ' + frete.valor + ' - até ' + frete.prazo + ' dias úteis';
				
				$('#div-frete').empty();
				$('#div-frete').append(html);
		    },
		    complete: function(){
		    	$btn.button('reset');
		    }
		});
	});
</script>