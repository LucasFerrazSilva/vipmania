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
		<h3 class="text-center">Redirecionando para o Paypal...</h3>
	</div>
	<form id="paypal-form" action="https://www.sandbox.paypal.com/cgi-bin/webscr" method="post">
	    <!--Tipo do botão-->
	    <input type="hidden" name="cmd" value="_cart" />
	 
		<input type="hidden" name="upload" value="1">
	    <input type="hidden" name="business" value="lucasferrazsilva@hotmail.com" />
	    <input type="hidden" name="return" value="http://localhost:8080/vipmania/" />
	    <input type="hidden" name="currency_code" value="BRL" />
	 
	    <!--Informações sobre os produtos-->
	    <input type="hidden" name="item_name_1" value="frete" />
    	<input type="hidden" name="amount_1" value="${frete.value}" />
    	<input type="hidden" name="quantity_1" value="1" />
	    
	    <c:forEach items="${items}" var="item" varStatus="i">
		    <input type="hidden" name="item_name_${i.index + 2}" value="${item.name} (${item.sizeItemName})" />
	    	<input type="hidden" name="amount_${i.index + 2}" value="${item.value}" />
	    	<input type="hidden" name="quantity_${i.index + 2}" value="${item.quantity}" />
	    </c:forEach>
	</form>

	<script>
		$('#paypal-form').submit();
	</script>

</jsp:body>	
</tags:pageTemplate>