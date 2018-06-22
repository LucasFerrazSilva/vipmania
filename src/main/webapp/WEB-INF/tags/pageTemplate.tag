<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ attribute name="title" required="true" %>
<%@ attribute name="bodyClass" required="false" %>
<%@ attribute name="extraScripts" fragment="true" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>


<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<title>${title} VipMania</title>
	
	<c:url value="/resources" var="resourcePath" />
	
	<link rel="stylesheet" href="${resourcePath}/css/bootstrap.min.css">

	<link rel="stylesheet" href="${resourcePath}/css/bootstrap-theme.min.css">
	
	<script src="${resourcePath}/js/jquery-3.2.1.js" ></script>
	<script src="${resourcePath}/js/bootstrap.min.js"></script>
	
	<style>
		body { 
			padding-top: 70px; 
		}
	
		th, td{
			text-align: center;
			vertical-align: middle !important;
		}
		
		.product-item {
			margin: 5px 0px;
		}
		
		.product-item-a {
			color: inherit;
			text-decoration: none;
		}
		
		.product-item-a:hover {
			color: inherit;
			text-decoration: none;
		}
		
		#vipmania-footer {
			padding: 1px 0;
			box-shadow: 0 -1px 0 rgba(255,255,255,.15), 0 -1px 5px rgba(0,0,0,.075);
			background-color: #f8f8f8;
			border-color: #e7e7e7;
		}
		
		.allign-vertical-center {
			display: inline-block;
			vertical-align: middle;
			float: none;
		}
	</style>	
</head>
<body class="${bodyClass}">

	<%@ include file="/WEB-INF/views/header.jsp" %>
	
	<jsp:doBody />
	
	<jsp:invoke fragment="extraScripts" /> 
	
	<%@ include file="/WEB-INF/views/footer.jsp" %>


	<script>
		$('.product-item-a').mouseover(function(){
			$(this).css("font-weight", 'bold');
		});
		
		$('.product-item-a').mouseout(function(){
			$(this).css("font-weight", 'inherit');
		});
		
		if($(window).height() >= $(document).height()) {
			console.log(2);
	       $('#vipmania-footer').addClass('navbar-fixed-bottom');
	   }
	</script>

</body>
</html>