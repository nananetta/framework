<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<tiles:insertDefinition name="emptyTemplate">
    <tiles:putAttribute name="body">
    	<div class="row" ng-controller="forgetPasswordCtrl" ng-init="init()">
    		<form id="frmForgetPassword" class="form-horizontal form-group-sm">
	    		<div class="row">
	    			<div class="col-md-12 form-group">
	    				<label class="col-md-5 control-label" for="txtEmail">รหัสผู้ใช้ (E-mail Address) :</label>
	    				<div class="col-md-3"><input type="text" id="txtEmail" name="eamilAddress" class="form-control" required="required"></div>
	    			</div>
	    		</div>
	    		<div class="row">
	    			<div class="col-md-12 form-group">
	    				<label class="col-md-5 control-label" for="txtThaiCard">หมายเลขบัตร ประชาชน/หนังสือเดินทาง (Thai Card No.or Passport No.) :</label>
	    				<div class="col-md-3"><input type="text" id="txtThaiCard" name="thaiCardPassport" class="form-control" required="required"></div>
	    				<div class="col-md-1" style="margin-top:10px;color:red;"><span class="glyphicon glyphicon-exclamation-sign" data-toggle="tooltip" data-placement="right" title="ห้ามมีช่องว่างหรือเครื่องหมายขีด (Do not input space or dashes)"></span></div>
	    			</div>
	    		</div>
	    		<div class="row">
	    			<div class="col-md-12 form-group">
	    				<label class="col-md-5 control-label" for="txtName">ชื่อ (Name) :</label>
	    				<div class="col-md-3"><input type="text" id="txtName" name="name" class="form-control" required="required"></div>
	    				<div class="col-md-1" style="margin-top:10px;color:red;"><span class="glyphicon glyphicon-exclamation-sign" data-toggle="tooltip" data-placement="right" title="ไม่ต้องใส่คำนำหน้าชื่อ (Do not input Titlename)"></span></div>
	    			</div>
	    		</div>
	    		<div class="row">
	    			<div class="col-md-12 form-group">
	    				<label class="col-md-5 control-label" for="txtSurName">นามสกุล (Surname) :</label>
	    				<div class="col-md-3"><input type="text" id="txtSurname" name="surname" class="form-control" required="required"></div>
	    			</div>
	    		</div>
	    		<div class="row">
					<div class="col-md-12 form-group">									
						<div class="col-md-2 col-md-offset-5">
							<button type="button" class="btn btn-primary btn-block" ng-click="verify()">ตรวจสอบ (Verify)</button>
						</div>
						<div class="col-md-2">
							<button type="button" class="btn btn-primary btn-block" ng-click="cancel()">ยกเลิก (Cancel)</button>
						</div>
					</div>
				</div>
    		</form>
    	</div>
    </tiles:putAttribute>
</tiles:insertDefinition>