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
</head>
<body class="${bodyClass}">

	<%@ include file="/WEB-INF/views/header.jsp" %>
	
	<jsp:doBody />
	
	<jsp:invoke fragment="extraScripts" /> 
	
	<%@ include file="/WEB-INF/views/footer.jsp" %>

</body>
</html>