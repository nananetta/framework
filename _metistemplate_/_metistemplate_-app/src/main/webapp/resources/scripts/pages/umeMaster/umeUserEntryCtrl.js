/**
 * 
 */
app.controller('umeUserEntryCtrl', [
		'$scope',
		'$http',
		'$pageUtils',
		'$dialogUtils',
		'$modal',
		function($scope, $http, $pageUtils, $dialogUtils, $modal) {
			$scope.$parent.menus = [ {
				url : 'umeMaster/umeUserSearch.html',
				caption : 'Search'
			} ];
			$scope.$parent.title = 'สร้าง - ข้อมูลผู้ใช้งาน/สิทธิ์การใช้งาน'
			$scope.model = {
				id : 0,
				userCode : "",
				userName : ""
			};
			$scope.save = function() {
				if ($("#frmUserEntry").validate().form() === false) {
					return false;
				}
				$pageUtils.washValues($scope.model);
				$http.post(contextPath + "/user/save", $scope.model).success(
						function(data) {
							window.location = "umeUserEdit.html?id=" + data.id;
						});
			};	
		} ]);