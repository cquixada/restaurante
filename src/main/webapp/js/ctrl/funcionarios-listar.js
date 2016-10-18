app.controller('funcionariosListarCtrl', function($scope, $http) {
	FuncionarioProxy.listarTodos($http).then(function(response) {
		$scope.funcionarios = response.data;
	});
});