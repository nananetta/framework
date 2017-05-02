<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="row">
		    <div class="col-md-12" ng-controller="umeUserEntryCtrl" ng-init="init()">
		        <form id="frmUserEntry">
		            <div class="col-md-12 form-horizontal form-group-sm">
		                <div class="row">
		                    <div class="col-md-12 form-group">
		                        <label class="col-md-5 control-label" for="txtUserCode">รหัสผู้ใช้งาน :</label>
		                        <div class="col-md-3"><input type="text" id="txtUserCode" name="userCode" ng-model="model.userCode" class="form-control" required="required"/></div>
		                    </div>
		                </div>
		                <div class="row">
		                    <div class="col-md-12 form-group">
		                        <label class="col-md-5 control-label" for="txtUserName">ชื่อ-สกุล :</label>
		                        <div class="col-md-3"><input type="text" id="txtUserName" name="userName" ng-model="model.userName" class="form-control" required="required"/></div>
		                    </div>
		                </div>
		                <div class="row">
		                    <div class="col-md-1 col-md-offset-7">
		                        <button type="button" class="btn btn-block btn-primary btn-form-action" ng-click="save()">{{all_msg.common_button_save}}</button>
		                    </div>
		                </div>
		            </div>
		        </form>
		    </div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>