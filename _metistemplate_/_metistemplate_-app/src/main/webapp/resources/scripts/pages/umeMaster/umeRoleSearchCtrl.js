/**
 * 
 */
app.controller('umeRoleSearchCtrl', ['$scope','$http','config','$pageUtils','$modalInstance','parameter', 
                                      function ($scope, $http, config, $pageUtils, $modalInstance, parameter) {
	$scope.pageSize = config.pageSize;
	$scope.maxSize = config.paginationSize;
	$scope.totalItems = 0;
	$scope.loading = false;

	$scope.model = {
		page : 1
	};
	$scope.results = undefined;
	$scope.init = function() {

	};
	$scope.search = function(pageIndex) {		
		$pageUtils.washValues($scope.model);
		$scope.loading = true;
		$http.post(contextPath + "/user/role/search", $scope.model)
				.success(function(data) {
					$scope.results = data.list;
					$scope.totalItems = data.total;
					$scope.loading = false;
				});
	};
	$scope.select = function(item) {
		var ret = {			
			roleItem : item
		}
		$modalInstance.close(ret);
	}	
	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};
	$scope.showResult = function() {
		return !(typeof $scope.results === "undefined");
	};
}]);
