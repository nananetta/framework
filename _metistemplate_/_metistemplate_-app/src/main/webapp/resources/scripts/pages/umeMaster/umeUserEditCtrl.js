/**
 * 
 */
app
		.controller(
				'umeUserEditCtrl',
				[
						'$scope',
						'$http',
						'$modal',
						'$pageUtils',
						function($scope, $http, $modal, $pageUtils) {
							$scope.model = {};
							$scope.role = {};
							$scope.init = function() {
								var user_id = angular.element(document
										.querySelector('#hdId'))[0].value;
								if (!user_id) {
									user_id = 0;
								}
								$scope.$parent.menus = [ {
									url : 'umeMaster/umeUserSearch.html',
									caption : 'Search'
								} ]
								if (user_id != 0) {
									$scope.$parent.title = 'แก้ไข - ข้อมูลผู้ใช้งาน/สิทธิ์การใช้งาน'
									$scope.$parent.menus.push({
										url : 'umeMaster/umeUserEntry.html',
										caption : 'Create'
									})
								} else {
									window.location = contextPath
											+ "/umeMaster/umeUserEntry.html";
								}
								$http.get(contextPath + "/user/getById", {
									params : {
										key : user_id
									}
								}).success(function(data) {
									$scope.model = data;

									if ($scope.model.roles == null) {
										$scope.model.roles = [];
									}
								});
							};
							$scope.addRole = function() {
								var modalInstance = $modal.open({
									templateUrl : contextPath
											+ '/umeMaster/umeRoleSearch.html',
									controller : 'umeRoleSearchCtrl',
									size : 'lg',
									backdrop : false,
									animation : true,
									resolve : {
										parameter : function() {
											return model = {

												page : 1
											};
										}
									}
								})
								modalInstance.result
										.then(
												function(selectedItem) {
													if (selectedItem.roleItem != undefined) {
														if($scope.model.roles == undefined){
															$scope.model.roles = [selectedItem.roleItem];
														}else{
															$scope.model.roles.push(selectedItem.roleItem);
														}
													}
												}, function() {
												});
							};
							// $scope.addRole = function() {
							// window.parentScope = $scope;
							// var w = 850;
							// var h = 700;
							// var left = (screen.width / 2) - (w / 2);
							// var tops = (screen.height / 2) - (h / 2);
							// window.open("umeRoleSearch.html", "Popup",
							// "width=" + w + ", height=" + h
							// + ", left=" + left + ", top="
							// + tops);
							// };
							$scope.delRole = function(index) {
								$scope.model.roles.splice(index, 1);
							};
							$scope.save = function() {
								for (var index = 0; index < $scope.model.roles.length; index++) {
									var role = $scope.model.roles[index];
								}
								$pageUtils.washValues($scope.model);
								$http
										.post(contextPath + "/user/save",
												$scope.model)
										.success(
												function(data) {
													window.location = "umeUserEdit.html?id="
															+ data.id;
												});
							};
						} ]);