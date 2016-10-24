app.controller('itensListarCtrl', function($scope, $http) {
	ItemProxy.listarTodos($http).then(function(response) {
		console.log(JSON.stringify(response.data));
		$scope.itens = response.data;
	});
});