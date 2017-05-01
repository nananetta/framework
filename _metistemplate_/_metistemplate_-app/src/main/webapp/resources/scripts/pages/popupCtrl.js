/**
 * 
 */

app.controller('popupCtrl', ['$scope','$http', function 
                             ($scope, $http) {
//	$scope.userProfile = undefined;
//	$scope.menus = [];
//	$scope.title = "Main Menu";
//	$scope.menus = [ {url:undefined, caption:undefined} ]

	$scope.init = function() {
//		$http.get(contextPath + "/user/getProfile").success(function(data) {
//			$scope.userProfile = data;
//		});

		$http.get(contextPath + "/config/listMessages").success(function(data) {
			$scope.all_msg = data;
		});
	};

}]);