<%@page contentType="text/html" pageEncoding="UTF-8"%>
<progressbar class="progress-striped active" value="dynamic" ng-show="loading"> </progressbar>
<div class="modal-body col-md-12 form-horizontal form-group-sm table-bordered" ng-init="init()">
	<div class="col-md-12">
		<div class="row">
			<div class="col-md-12">
				<div class="col-md-offset-9">
					<button type="button" class="btn btn-danger" ng-click="close(model.src)">ปิดหน้าจอ (CLOSE)</button>
				</div>
			</div>						
			<div class="col-md-12">
				<img class="img-responsive" ng-src="{{model.src}}" alt="image" />
			</div>
		</div>
	</div>
</div>
<div class="modal-footer"></div>