/**
 * 
 */
app.controller('viewImageModalCtrl', [
		'$scope',
		'$http',
		'$pageUtils',
		'$dialogUtils',
		'$modalInstance',
		'parameter',
		'config',
		function($scope, $http, $pageUtils, $dialogUtils, $modalInstance,
				parameter, config) {
			$scope.selectedItem = parameter.item;
			$scope.model = {
				src : parameter.src
			};
			$scope.init = function() {
			};
			$scope.close = function(img) {
				var ret = {
					item : $scope.selectedItem,
					thumbnail : img
				};
				$modalInstance.close(ret);
				// $modalInstance.dismiss('cancel');
			};
		} ]);