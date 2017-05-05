<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<title>_metistemplate_</title>
<link rel="stylesheet" href="${context}/resources/styles/bootstrap-Cerulean.min.css" />
<link rel="stylesheet" href="${context}/resources/styles/style.min.css" />
<script type="text/javascript" src="${context}/resources/scripts/jquery-2.1.3.js"></script>
<script type="text/javascript" src="${context}/resources/scripts/jquery.validate/1.14.0/jquery.validate.min.js"></script>
<script type="text/javascript" src="${context}/resources/scripts/jquery.validate/1.14.0/additional-methods.min.js"></script>
<script type="text/javascript" src="${context}/resources/scripts/angular.min.js"></script>
<script type="text/javascript" src="${context}/resources/scripts/bootstrap.min.js"></script>
<script type="text/javascript" src="${context}/resources/scripts/bootbox.min.js"></script>
<script type="text/javascript" src="${context}/resources/scripts/ui-bootstrap-tpls-0.13.0.min.js"></script>
<script>var contextPath = "${pageContext.request.contextPath}"</script>
<script type="text/javascript" src="${context}/resources/scripts/pages/app.js"></script>
<script type="text/javascript" src="${context}/resources/scripts/pages/all.js"></script>
<script type="text/javascript" src="${context}/resources/scripts/jasny-bootstrap.min.js"></script>
<script type="text/javascript" src="${context}/resources/scripts/kendo/kendo.ui.core.min.js"></script>
<script type="text/javascript" src="${context}/resources/scripts/kendo/kendo.angular.min.js"></script>

<link href="${context}/resources/img/_metistemplate__logo.ico" rel="shortcut icon" type="image/x-icon" />

<script type="text/javascript">
    $(document).ready(function() {
        common_js.initialRequiredField();
    });
</script>

</head>
<body ng-app="project" ng-controller="pageCtrl" ng-init="init()">
	<div class="container-fluid wrapper" style="padding-top: 15px; padding-bottom: 15px; background-color: #EEEEEE;">
		<div class="col-md-12 ">
			<div class="col-md-12">
				<div class="container-fluid templateG" style="border-radius: 10px; background-color: #ffffff;">
					<div class="col-md-12 ">
						<div class="col-md-12">
							<div class="col-md-12">
								<div class="row">
									<div class="col-md-12" style="padding-top: 8px;">
										<a href="#"><img src="${context}/resources/img/_metistemplate__logo.png" style="vertical-align: text-bottom;width: 120px;height: 50px;"/></a>
										<div style="display: inline-block; font-size: 18px;">_metistemplate_ System</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<hr style="margin: 0px 0px 5px 0px;" />
									</div>
								</div>
							</div>
						</div>
					</div>
					<div>&nbsp;</div>
					<div style="padding-top: 10px; padding-left: 10px">
						<tiles:insertAttribute name="body"></tiles:insertAttribute>
					</div>					
					<div class="col-md-12">							
						<div class="col-md-6">
							<p style="color: gray; padding-top: 5px; padding-bottom: 5px; margin-bottom: 0px">
								<span>@ Copyright Metis</span>
							</p>
						</div>
						<div class="col-md-6 text-right">
							<p style="color: gray; padding-top: 5px; padding-bottom: 5px; margin-bottom: 0px">
								<span>__Release 1.0.0 - 20160707-0711__</span>
							</p>
						</div>
						<div class="row">
							<div class="col-sm-6">&nbsp;</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>	
</body>
</html>



