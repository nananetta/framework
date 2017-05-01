/**
 * 
 */
app
		.controller(

				'umeChangePasswordCtrl',
				[
						'$scope',
						'$http',
						'$modal',
						'$pageUtils',
						'$dialogUtils',
						function($scope, $http, $modal, $pageUtils,
								$dialogUtils) {
							$scope.model = {

							};
							$scope.isValid = true;
							$scope.errors = [];
							$scope.init = function() {
								$scope.urlParameter = $pageUtils.getUrlVars();
								$scope.$parent.title = 'Change password'
								$scope.Id = $scope.urlParameter.id;
								$scope.load();
							};
							$scope.load = function() {
								if (angular.isDefined($scope.Id)) {
									$http.get(contextPath + "/user/getById", {
										params : {
											key : $scope.Id
										}
									}).success(function(data) {
										$scope.model = data;
										if ($scope.model.id == undefined) {
											$scope.model.id = 0;
										}

									});
								}
								;
								$scope.save = function() {
									var isValid = $scope.validate();
									if (isValid == true) {
										if (angular.isDefined($scope.Id) == false) {
											$scope.model.id = 0;
										}
										

										$http
												.post(
														contextPath
																+ "/user/save",
														$scope.model)
												.success(
														function(data) {
															window.location = contextPath
																	+ "/index.html";
//															window.location = contextPath
//																	+ "/index.html?id="
//																	+ data.id;
														});
									}
								};
							};
							$scope.validate = function() {
								$scope.errors = [];
								$scope.isValid = true;
								if ($scope.model.password == null
										|| $scope.model.password == "") {
									$scope.errors.push("กรุณากรอกรหัสผ่านเก่า");
								}
								if ($scope.model.newPassword == null
										|| $scope.model.newPassword == "") {
									$scope.errors.push("กรุณากรอกรหัสผ่านใหม่");
								}
								if ($scope.model.confirmPassword == null
										|| $scope.model.confirmPassword == "") {
									$scope.errors
											.push("กรุณากรอกยืนยันรหัสผ่าน");
								}
								if (($scope.model.password != null && $scope.model.password != "")
										&& ($scope.model.newPassword != null && $scope.model.newPassword != "")
										&& ($scope.model.confirmPassword != null && $scope.model.confirmPassword != "")) {
									if ($scope.model.password == $scope.model.newPassword) {
										$scope.errors
												.push("รหัสผ่านใหม่ตรงกับรหัสผ่านเก่า");
									}
									if ($scope.model.newPassword != $scope.model.confirmPassword) {
										$scope.errors
												.push("ยืนยันรหัสผ่านใหม่ไม่ตรงกัน");
									}
								}

								if ($scope.errors.length > 0) {
									$scope.isValid = false;
								} else {
									$scope.isValid = true;
								}
								return $scope.isValid;

							};
						} ]);
