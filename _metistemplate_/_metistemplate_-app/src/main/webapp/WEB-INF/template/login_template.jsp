<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<title>_metistemplate_</title>
<link rel="stylesheet" href="${context}/resources/styles/bootstrap.css" />
<script type="text/javascript" src="${context}/resources/scripts/jquery-2.1.3.js"></script>
<script type="text/javascript" src="${context}/resources/scripts/bootstrap.min.js"></script>
<link rel="stylesheet" href="${context}/resources/styles/style.min.css" />
<link href="${context}/resources/img/_metistemplate__logo.ico" rel="shortcut icon" type="image/x-icon" />
</head>
<body>
	<div class="row"><div>&nbsp;</div></div>
	<div class="container-fluid">
		<tiles:insertAttribute name="body"></tiles:insertAttribute>
	</div>
	<div class="col-md-offset-6">
		<p style="color: white">__Release 1.0.0 - 20160707-0711__</p>
	</div>
</body>
</html>