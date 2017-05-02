<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="col-md-12" ng-controller="umeChangePasswordCtrl" ng-init="init()">			
			<form class="form-horizontal form-group-sm">			
				<div class="row">
					<div class="col-md-12 form-group">
						<label class="control-label col-md-5" for="username">{{all_msg.message_user}} :</label>
						<div class="col-md-4">
							<label class="control-label">{{model.userName}}</label>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12 form-group">
						<label class="control-label col-md-5" for="oldPassword">{{all_msg.message_old_pass}} :</label>
						<div class="col-md-2">
							<input type="password" class="form-control" ng-model="model.password">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12 form-group">
						<label class="control-label col-md-5" for="password">{{all_msg.message_pass}} :</label>
						<div class="col-md-2">
							<input type="password" class="form-control" ng-model="model.newPassword">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12 form-group">
						<label class="control-label col-md-5" for="confirmPassword">{{all_msg.message_confirm}} :</label>
						<div class="col-md-2">
							<input type="password" class="form-control" ng-model="model.confirmPassword">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-2 col-md-offset-10">
						<button type="button" class="btn btn-block btn-primary btn-form-action" ng-click="save()">{{all_msg.common_button_change}}</button>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<ul ng-show="isValid==false">
							<li ng-repeat="error in errors">{{error}}</li>
						</ul>
					</div>
				</div>
			</form>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>