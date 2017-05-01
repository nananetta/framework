app.controller('forgetPasswordCtrl', [
		'$scope',
		'$http',
		'$dialogUtils',
		'$pageUtils',
		'$modal',
		function($scope, $http, $dialogUtils, $pageUtils, $modal) {

			$scope.model = {};
			$scope.init = function() {
				
			};	
			
			$scope.cancel = function(){
				window.location = contextPath
				+ "/index.html";
			};
		} ]);