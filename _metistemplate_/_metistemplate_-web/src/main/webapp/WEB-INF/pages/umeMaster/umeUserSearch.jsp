<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="col-md-12" ng-controller="umeUserSearchCtrl" ng-init="init()">
		    <div class="panel panel-default">
		    <form id="frmUserSearch">
		        <div class="col-md-12 form-horizontal form-group-sm">
		            <div class="row">
		                <div class="col-md-6 form-group">
		                    <label class="col-md-5 control-label">รหัสผู้ใช้งาน :</label>
		                    <div class="col-md-6"><input type="text" ng-model="model.userCode" class="form-control" /></div>
		                </div>
		                <div class="col-md-6 form-group">
		                    <label class="col-md-5 control-label">ชื่อ-สกุล :</label>
		                    <div class="col-md-6"><input type="text" ng-model="model.userName" class="form-control" /></div>
		                </div>
		            </div>
		            <div class="row">
		                <div class="col-md-6 form-group">
		                    <label class="col-md-5 control-label">รหัส บทบาท/หน้าที่ :</label>
		                    <div class="col-md-6"><input type="text" ng-model="model.roleCode" class="form-control" /></div>
		                </div>
		                <div class="col-md-6 form-group">
		                    <label class="col-md-5 control-label">บทบาท/หน้าที่ :</label>
		                    <div class="col-md-6"><input type="text" ng-model="model.roleName" class="form-control" /></div>
		                </div>
		            </div>
		            <div class="row">
		                <div class="col-md-2 col-md-offset-8">
		                    <button type="button" class="btn btn-block btn-primary" ng-click="search()">{{all_msg.common_button_search}}</button> 
		                </div>
		                <div class="col-md-2">
		                    <button type="button" class="btn btn-block btn-primary" ng-click="clear()">{{all_msg.common_button_clearScreen}}</button>
		                </div>
		            </div>
		        </div>
		    </form>
		    <div>&nbsp;</div>
		    <div class="col-md-12" ng-if="showResult()">
		        <div class="row">
		            <table class="table table-bordered table-striped table-hover table-condensed" ng-show="results.length >0">
		                <thead>
		                    <tr>
		                        <th class="text-center">เรียกดู</th>
		                        <th class="text-center">แก้ไข</th>
		                        <th class="text-center">ลบ</th>
		                        <th class="text-center">รหัสผู้ใช้งาน</th>
		                        <th class="text-center">ชื่อ-สกุล</th>
		                    </tr>
		                </thead>
		                <tbody>
		                    <tr ng-repeat="item in results">
		                        <td class="text-center">
		                            <a href="" ng-click="edit(item.id)"><span class="glyphicon glyphicon-search"></span></a>
		                        </td>
		                         <td class="text-center">
		                            <a href="" ng-click="edit(item.id)"><span class="glyphicon glyphicon-pencil"></span></a>
		                        </td>
		                         <td class="text-center">
		                            <a href="" ng-click="remove(item)"><span class="glyphicon glyphicon-trash"></span></a>
		                        </td>
		                        <td>{{item.userCode}}</td>
		                        <td>{{item.userName}}</td>
		                    </tr>
		                </tbody>
		                <tfoot></tfoot>
		            </table>
		        </div>
			<div>Page: {{model.page}}/{{numPages}} of {{totalItems}} results</div>
			<pagination ng-model="model.page" ng-change="search()" total-items="totalItems" num-pages="numPages" items-per-page="pageSize" max-size="maxSize" class="pagination-xs" boundary-links="true" rotate="false"></pagination>
		    </div>
		    </div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>