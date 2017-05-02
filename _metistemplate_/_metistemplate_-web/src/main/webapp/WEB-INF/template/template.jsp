<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>

<meta name="viewport" content="width=device-width" />
<title>_webtemplate_ System</title>
<link rel="stylesheet" href="${context}/resources/styles/bootstrap-Cerulean.min.css" />
<link rel="stylesheet" href="${context}/resources/styles/style.min.css" />
<script type="text/javascript" src="${context}/resources/scripts/jquery-2.1.3.js"></script>
<script type="text/javascript" src="${context}/resources/scripts/jquery.validate/1.14.0/jquery.validate.min.js"></script>
<script type="text/javascript" src="${context}/resources/scripts/jquery.validate/1.14.0/additional-methods.min.js"></script>
<script type="text/javascript" src="${context}/resources/scripts/angular.min.js"></script>
<script type="text/javascript" src="${context}/resources/scripts/bootstrap.min.js"></script>
<script type="text/javascript" src="${context}/resources/scripts/bootbox.min.js"></script>
<script type="text/javascript" src="${context}/resources/scripts/ui-bootstrap-tpls-0.13.0.min.js"></script>
<script>
	var contextPath = "${pageContext.request.contextPath}"
</script>
<script type="text/javascript" src="${context}/resources/scripts/pages/app.js"></script>
<script type="text/javascript" src="${context}/resources/scripts/pages/all.js"></script>
<script type="text/javascript" src="${context}/resources/scripts/jasny-bootstrap.min.js"></script>

<script type="text/javascript" src="${context}/resources/scripts/common_initial.js"></script>
<script type="text/javascript" src="${context}/resources/scripts/session-idletime.js"></script>

<link href="${context}/resources/img/_webtemplate__logo.png" rel="shortcut icon" type="image/x-icon" />

<script type="text/javascript">
	$(document).ready(function() {
		//*** scan to replace label required field with red star *** 
		common_js.initialRequiredField();

		session_idletime.initIdleTimer(this);
	});
</script>

</head>
<body ng-app="project" ng-controller="pageCtrl" ng-init="init()">
	<div id="pleaseWaitDialog" class="modal" data-backdrop="static" data-keyboard="false" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Process...</h4>
				</div>
				<div class="modal-body">

					<div class="progress">
						<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="40" aria-valuemin="0"
							aria-valuemax="100" style="width: 100%"></div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="AlertModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">Modal title</h4>
				</div>
				<div class="modal-body">
					<p></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<!-- 	        <button type="button" class="btn btn-primary">Save changes</button> -->
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<div class="container-fluid" style="padding-top: 15px; padding-bottom: 15px;">
		<div class="container-fluid" style="border: 3px groove #800000; border-radius: 10px;">
			<div class="row">
				<div class="col-md-12" style="padding-top: 8px;">
					<a href="#"><img src="${context}/resources/img/_webtemplate__logo.png"
						style="vertical-align: text-bottom; width: 70px;" /></a>
					<div style="display: inline-block; font-size: 18px;">&nbsp;_webtemplate_ System</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<hr style="margin: 0px 0px 5px 0px;" />
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
					&nbsp;&nbsp;<a href="${context}/index.html"><span class="glyphicon glyphicon-home"></span> Main</a> <span
						ng-repeat="menu in menus"> | <a href="${context}/{{menu.url}}">{{menu.caption}} </a>
					</span>

				</div>
				<div class="col-md-4 text-center">
					<span style="font-size: large"><strong>{{title}}</strong></span>
				</div>
				<div class="col-md-4 text-right">
					<a href="${context}/umeMaster/umeChangePassword.html?id={{userProfile.id}}" id="lbtnUser"><span
						class="glyphicon glyphicon-user"></span> {{userProfile.userCode}}</a>&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="${context}/logout" id="lbtnLogout"><span class="glyphicon glyphicon-log-out"></span> Logout</a>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="col-md-12 table-bordered" style="border: 1px solid #808080; border-radius: 10px; padding-top: 10px;">
						<div id="alert" style="margin-top: 10px;"></div>
						<tiles:insertAttribute name="body"></tiles:insertAttribute>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<p style="color: gray; padding-top: 5px; padding-bottom: 5px; margin-bottom: 0px">
						Metis Corperation Co.,Ltd. 089-968-7244<span></span>
					</p>
				</div>
				<div class="col-md-6 text-right">
					<p style="color: gray; padding-top: 5px; padding-bottom: 5px; margin-bottom: 0px">
						<span>__Release 1.0.0 - 20160707-0711__</span>
					</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>



