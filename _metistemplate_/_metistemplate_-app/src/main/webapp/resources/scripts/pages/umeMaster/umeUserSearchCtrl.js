/**
 * 
 */
app.controller('umeUserSearchCtrl', ['$scope','$http', 'config', function 
                                     ($scope, $http, config) {
	$scope.pageSize = config.pageSize;
	$scope.maxSize = config.paginationSize;
	$scope.totalItems = 0;
	$scope.$parent.title = 'ค้นหา-ข้อมูลผู้ใช้งาน/สิทธิ์การใช้งาน'
	$scope.$parent.menus = [ {
		url : 'umeMaster/umeUserEntry.html',
		caption : 'Create'
	} ];
	$scope.model = {
		userCode : undefined,
		userName : undefined,
		roleCode : undefined,
		roleName : undefined,
		page : 1
	};
	$scope.results = undefined;
	$scope.init = function() {

	};
	$scope.search = function() {
		$http.post(contextPath + "/user/search", $scope.model).success(function(data) {

			$scope.results = data.list;
			$scope.totalItems = data.total;

		});
	};
	$scope.clear = function() {
		$scope.results = undefined;
		$scope.model = {
			userCode : undefined,
			userName : undefined,
			roleCode : undefined,
			roleName : undefined,
			page : 1
		};
	};
	$scope.edit = function(id) {
		window.location = "umeUserEdit.html?id=" + id;
	};
	$scope.remove = function(item) {
		$http.post("../user/delete", item).success(function(data) {
			$scope.search();
		});
	};
	$scope.showResult = function() {
		return !(typeof $scope.results === "undefined");
	};
}]);