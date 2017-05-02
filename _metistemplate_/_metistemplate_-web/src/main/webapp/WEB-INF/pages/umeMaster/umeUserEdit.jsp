<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="col-md-12" ng-controller="umeUserEditCtrl" ng-init="init()">
		    <form id="frmEditUser">
		        <div class="col-md-12 form-horizontal form-group-sm">
		            <div class="row">
		                <div class="col-md-6 form-group">
		                	<input type="hidden" id="hdId" ng-value="model.id" value="${param.id }"/>
		                    <label class="col-md-5 control-label">รหัสผู้ใช้งาน :</label>
		                    <div class="col-md-6"><input type="text" disabled ng-model="model.userCode" class="form-control" /></div>
		                </div>
		                <div class="col-md-6 form-group">
		                    <label class="col-md-5 control-label">ชื่อ-สกุล :</label>
		                    <div class="col-md-6"><input type="text" ng-model="model.userName" class="form-control" /></div>
		                </div>
		            </div>
		        </div>
		        <div>&nbsp;</div>
		        <div class="col-md-12">
		            <div class="row">
		                <table class="table table-bordered table-striped table-hover table-condensed">
		                    <thead>
		                        <tr>
		                            <th class="text-center">ลบ</th>
		                            <th class="text-center">รหัส บทบาท/หน้าที่</th>
		                            <th class="text-center">บทบาท/หน้าที่</th>
		                        </tr>
		                    </thead>
		                    <tbody>
		                        <tr ng-repeat="item in model.roles" >
		                            <td class="text-center">
		                                <a href="" ng-click="delRole($index)"><span class="glyphicon glyphicon-trash"></span></a>		                             
		                            </td>
		                            <td>{{item.roleCode}}</td>
		                            <td>{{item.roleName}}</td>
		                        </tr>
		                    </tbody>
		                    <tfoot>
		                        <tr>
		                            <td colspan="3">
		                                <button id="btnAdd" class="btn btn-primary" ng-click="addRole()">{{all_msg.common_button_add}}</button>
		                            </td>
		                        </tr>
		                    </tfoot>
		                </table>
		            </div>
		        </div>
		        <div class="row">
		            <div class="col-md-2 col-md-offset-10">
		                <button type="button" class="btn btn-block btn-primary btn-form-action" ng-click="save()">{{all_msg.common_button_save}}</button>
		            </div>
		        </div>
		    </form>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>