<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<title>_webtemplate_</title>
<link rel="stylesheet" href="${context}/resources/styles/bootstrap-Cerulean.css"/>
<link rel="stylesheet" href="${context}/resources/styles/style.min.css"/>
<link rel="stylesheet" href="${context }/resources/styles/kendo/2014.3.1119/kendo.common-bootstrap.min.css"/>
<link rel="stylesheet" href="${context }/resources/styles/kendo/2014.3.1119/kendo.bootstrap.min.css"/>
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
<script type="text/javascript" src="${context}/resources/scripts/common_initial.js"></script>
<script type="text/javascript" src="${context}/resources/scripts/ng-file-upload-shim.min.js"></script>
<script type="text/javascript" src="${context}/resources/scripts/ng-file-upload.min.js"></script>
<script type="text/javascript" src="${context}/resources/scripts/pages/popupCtrl.js"></script>
<script type="text/javascript" src="${context}/resources/scripts/kendo/kendo.ui.core.min.js"></script>
<script type="text/javascript" src="${context}/resources/scripts/kendo/kendo.angular.min.js"></script>
<link href="${context}/resources/img/_webtemplate__logo.ico" rel="shortcut icon" type="image/x-icon" />

</head>
<body ng-app="project" ng-controller="popupCtrl" ng-init="init()">
	<div id="pleaseWaitDialog" class="modal" data-backdrop="static" data-keyboard="false" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Process...</h4>
				</div>
				<div class="modal-body">

					<div class="progress">
						<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="40" aria-valuemin="0"
							aria-valuemax="100" style="width: 100%;"></div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="AlertModal" >
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title">Modal title</h4>
	      </div>
	      <div class="modal-body">
	        <p></p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
<!-- 	        <button type="button" class="btn btn-primary">Save changes</button> -->
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->

	<div class="container-fluid">
		<tiles:insertAttribute name="body"></tiles:insertAttribute>
	</div>
</body>
</html>