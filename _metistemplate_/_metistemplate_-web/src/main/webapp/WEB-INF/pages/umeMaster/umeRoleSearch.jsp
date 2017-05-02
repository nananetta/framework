<%@page contentType="text/html" pageEncoding="UTF-8"%>
<progressbar class="progress-striped active" value="dynamic" ng-show="loading"> </progressbar>
<div class="modal-header">
	<h3 class="modal-title">Search User</h3>
</div>
<div class="modal-body col-xs-12 form-horizontal form-group-sm table-bordered" ng-init="init()">
	<div>&nbsp;</div>
		
		<form id="frmRoleSearch">
			<div class="col-md-12 form-horizontal form-group-sm">
				<div class="row">						
					<div class="col-md-6 form-group">
						<label class="col-md-5 control-label">รหัส บทบาท/หน้าที่ :</label>
						<div class="col-md-6">
							<input type="text" ng-model="model.roleCode"
								class="form-control" />
						</div>
					</div>
					<div class="col-md-6 form-group">
						<label class="col-md-5 control-label">คำอธิบาย :</label>
						<div class="col-md-6">
							<input type="text" ng-model="model.roleName"
								class="form-control" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-2 col-md-offset-8">
						<button type="button" class="btn btn-primary btn-block" id="btnSearch" ng-click="search()">ค้นหา</button>
					</div>
	                <div class="col-md-2">
						<button type="button" class="btn btn-primary btn-block" id="btnClear" ng-click="cancel()">ปิด</button>
						
					</div>
				</div>
			</div>
		</form>
		<div>&nbsp;</div>
		<div class="col-md-12">
			<div class="row">
				<table
					class="table table-bordered table-striped table-hover table-condensed">
					<thead>
						<tr>
							<th class="text-center">เลือก</th>
							<th class="text-center">รหัส บทบาท/หน้าที่</th>
							<th class="text-center">คำอธิบาย</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="item in results">
							<td class="col-md-1 text-center">
								<button class="btn btn-primary btn-block" ng-click="select(item)">เลือก</button>
							</td>
							<td>{{item.roleCode}}</td>
							<td>{{item.roleName}}</td>
						</tr>
					</tbody>
					<tfoot></tfoot>
				</table>
			</div>
		</div>
	</div>
</div>
<div class="modal-footer"></div>