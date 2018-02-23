<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li><a href="<c:url value="/" />"><s:message code="home"/></a></li>
      </ul>
      <security:authorize access="hasRole('ROLE_ADMIN')">
	      <ul class="nav navbar-nav">
	        <li class="dropdown">
	          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><s:message code="products"/><span class="caret"></span></a>
	          <ul class="dropdown-menu">
	            <li><a href="<c:url value="/product/list" />"><s:message code="product.list"/></a></li>
	            <li><a href="<c:url value="/product/form" />"><s:message code="product.form"/></a></li>
	          </ul>
	        </li>
	      </ul>
      </security:authorize>
      <ul class="nav navbar-nav navbar-right">
        <li>
        	<a href="<c:url value="/cart" />">
        		<s:message code="cart" arguments="${cart.quantity}" />
        	</a>
        </li>
      </ul>
      <security:authorize access="isAuthenticated()">
	      <ul class="nav navbar-nav navbar-right">
	      	<li><a href="<c:url value='/logout' />"><s:message code="logout"/></a></li>
	      </ul>
	      <ul class="nav navbar-nav navbar-right">
	      	<li><a href="#"><security:authentication property="principal.username" /></a></li>
	      </ul>
	  </security:authorize>
		<ul class="nav navbar-nav">
			<li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
					<s:message code="language"/><span class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<li><a href="<c:url value="?locale=pt" />"><s:message code="language.pt"/></a></li>
					<li><a href="<c:url value="?locale=en_US" />"><s:message code="language.en"/></a></li>
				</ul>
			</li>
		</ul>
    </div>
  </div>
</nav>
