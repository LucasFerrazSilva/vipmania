<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<nav id="main-menu" class="navbar navbar-default navbar-fixed-top">
	<div class="container-fluid">
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="<c:url value="/" />"><s:message code="home" /></a></li>
			</ul>

			<ul class="nav navbar-nav">
				<li>
					<a href="<c:url value="/product/list/camisas" />"><s:message code="shirts" /></a>
				</li>
			</ul>

			<ul class="nav navbar-nav">
				<li>
					<a href="<c:url value="/product/list/calças" />"><s:message code="pants" /></a>
				</li>
			</ul>

			<ul class="nav navbar-nav">
				<li>
					<a href="<c:url value="/product/list/vestidos" />"><s:message code="dresses" /></a>
				</li>
			</ul>

			<form class="navbar-form navbar-left" action="<c:url value="/product/search" />">
				<div class="input-group">
					<input type="text" class="form-control" placeholder="Search" name="word">
					<span class="input-group-btn">
						<button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
					</span> 
				</div>
			</form>

			<security:authorize access="hasRole('ROLE_ADMIN')">
				<ul class="nav navbar-nav">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><s:message code="maintenance" /><span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li><a href="<c:url value="/product/list" />"><s:message code="products" /></a></li>
						<li><a href="<c:url value="/category/list" />"><s:message code="categories" /></a></li>
						<li><a href="<c:url value="/size/list" />"><s:message code="sizes" /></a></li>
						<li><a href="<c:url value="/user/list" />"><s:message code="users" /></a></li>
					</ul></li>
				</ul>
			</security:authorize>

			<ul class="nav navbar-nav navbar-right">
				<li><a href="<c:url value="/cart" />"> <s:message code="cart"/> <span class="badge">${cart.quantity}</span></a></li>
			</ul>

			<security:authorize access="!isAuthenticated()">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="<c:url value='/login' />"><s:message code="login" /></a></li>
				</ul>
			</security:authorize>

			<security:authorize access="isAuthenticated()">
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"> 
							<security:authentication property="principal.name" /><span class="caret"></span>
						</a>
						<ul class="dropdown-menu">
							<li><a href="<c:url value="/user/home" />"><s:message code="account" /></a></li>
							<li><a href="<c:url value="/order/list" />"><s:message code="orders" /></a></li>
							<li><a href="<c:url value="/address/list" />"><s:message code="adresses" /></a></li>
							<li><a href="<c:url value='/logout' />"><s:message code="logout" /></a></li>
						</ul>
					</li>
				</ul>
			</security:authorize>

<!-- 			<ul class="nav navbar-nav navbar-right"> -->
<!-- 				<li class="dropdown"> -->
<!-- 					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">  -->
<%-- 						<s:message code="language" /><span class="caret"></span> --%>
<!-- 					</a> -->
<!-- 					<ul class="dropdown-menu"> -->
<%-- 						<li><a href="<c:url value="?locale=pt" />"><s:message code="language.pt" /></a></li> --%>
<%-- 						<li><a href="<c:url value="?locale=en_US" />"><s:message code="language.en" /></a></li> --%>
<!-- 					</ul> -->
<!-- 				</li> -->
<!-- 			</ul> -->
		</div>
	</div>
</nav>

<div class="container">	
	<c:if test="${not empty message}">
		<div class="alert alert-info alert-dismissible text-center" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			${message}
		</div>
	</c:if>
</div>