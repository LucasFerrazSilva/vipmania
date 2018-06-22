<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate title="">

<jsp:attribute name="extraScripts">
	<script>
		
	</script>
</jsp:attribute>

<jsp:body>
	<div class="container">
		<div class="col-sm-10 col-sm-offset-1">
			<h1>Carrinho</h1>
			
			<hr />
			
			<c:url value="/cart/finalize" var="finalizePath"/>
			
			<input type="hidden" value="<c:url value="/calcular-frete/"/>" id="calcular-frete-url" />
		
			<div class="col-sm-12">
				<c:choose>
					<c:when test="${not empty itens}">
						<form:form action="${finalizePath}" method="POST">
					<table class="table table-bordered table-hover table-striped">
						<thead>
							<tr>
								<th class="col-sm-3">Nome</th>
								<th class="col-sm-3">Tamanho</th>
								<th class="col-sm-2">Valor unitário</th>
								<th class="col-sm-2">Quantidade</th>
								<th class="col-sm-2">Valor total</th>
								<th class="col-sm-1"></th>
							</tr>
						</thead>
						<tbody>	
							<c:forEach items="${itens}" var="item" varStatus="i">
								<input type="hidden" name="itens[${i.index}].product" value="${item.productId}" />
								<input type="hidden" name="itens[${i.index}].sizeItem" value="${item.sizeItemId}" />
								<tr>
									<input type="hidden" class="item-value" value="${item.value}" />
									
									<td>
										<a class="product-item-a" href="<c:url value="/product/${item.product.id}" />">
											<img src="<c:url value="/image/${item.product.id}" />" height="50" width="50"/><br />
											${item.product.name}
										</a>
									</td>
									<td>${item.sizeItemName}
									<td>${item.formattedValue}</td>
									<td>
										<div class="col-sm-10 col-sm-offset-1">
											<input type="number" class="form-control item-quantity text-center" min="1" max="${item.maxQuantity}" name="itens[${i.index}].quantity" value="${item.quantity}"/>
										</div>
									</td>
									<td class="total-value">${item.formattedTotalValue}</td>
									<td>
										<c:url value="/cart/remove?product=${item.productId}&sizeItem=${item.sizeItemId}" var="removeItemPath"/>
										<a class="btn btn-danger btn-sm" href="${removeItemPath}">&times;</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				
					<div class="col-sm-12">
						<div class="col-sm-3 col-sm-offset-9 text-right">
							<p class="text-right">Valor da compra: <span id="totalValue">${cart.formattedTotalValue}</span></p>
							<c:if test="${not empty frete}">
								<p class="text-right">Frete: R$ <span id="frete">${frete.valor}</span></p>
							</c:if>
							<hr />
							<p class="text-right">Total: R$ <span id="total">${(cart.totalValue + frete.value).toString().replace('.', ',')}</span></p>
						</div>
					</div>
					
					<div class="col-sm-12">
						<hr />
					</div>					
					
					<security:authorize access="!isAuthenticated()">
						<div class="row text-center">
							<em>Faça <a href="<c:url value='/login' />">Login</a> para finalizar a compra</em>
						</div>
					</security:authorize>
					
					<security:authorize access="isAuthenticated()">
						<div class="form-group col-sm-12">
							<label>Selecione o endereço de entrega</label>
							
							<c:choose>
								<c:when test="${not empty adresses}">
									<select id="select-address" name="address" class="form-control">
										<c:forEach items="${adresses}" var="address">
											<option value="${address.id}" data-cep="${address.cep}">${address.formattedAddress}</option>
										</c:forEach>
									</select>
								</c:when>
								<c:otherwise>
									<p class="text-center">Você não possui endereço cadastrado. <a href="<c:url value="/address/form/0" />">Clique aqui</a> para adicionar um novo endereço.</p>
								</c:otherwise>
							</c:choose>
						</div>
						
						<div class="col-sm-12">
							<hr />
						</div>
						
						<div class="col-sm-12">
							<button type="submit" class="btn btn-success btn-block">Finalizar compra</button>
						</div>
						
						<div class="col-sm-12">
							<hr />
						</div>
					</security:authorize>
					
					<hr />
				</form:form>
					</c:when>
					<c:otherwise>
						<h3 class="text-center">Seu carrinho está vazio</h3>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>

	<script>
		$('.item-quantity').on('change keyup', function(){
			var value = $(this).val();
			var $tr = $(this).closest('tr');
			var itemValue = $tr.find('.item-value').val();
			var totalValue = (value * itemValue).toFixed(2);
			
			$tr.find('.total-value').text('R$ ' + totalValue.replace('.', ','));
			
			calcTotalValue();
		});

		function calcTotalValue(){
			var total = 0;
				
			$('.total-value').each(function(){
				var val = $(this).text().replace('R$ ', '').replace(',', '.');
				
				total += parseFloat(val);
			});
			
			$('#totalValue').text('R$ ' + total.toFixed(2).replace('.', ','));
			
			var frete = $('#frete').text().replace(',', '.');
			
			var totalValue = (parseFloat(frete) + total).toFixed(2).toString().replace('.', ',');
			
			$('#total').text(totalValue);
		}
		
		$('#select-address').on('change', function(){
			var cep = $(this).find('option:selected').data('cep').toString();
			
			var url = $('#calcular-frete-url').val() + cep.replace('-', '');
			
			$.ajax({
				url: url,
				dataType: 'json',
				success: function(data){
					var valor = data.valor;
					
					$('#frete').text(valor);
					
					calcTotalValue();
			    }
			});
		});
	</script>

</jsp:body>	
</tags:pageTemplate>