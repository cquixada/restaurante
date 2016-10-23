app.controller('pedidosListarCtrl', function($scope, $http) {
	PedidoProxy.listarTodos($http).then(function(response) {
		$scope.pedidos = response.data;
	});
});