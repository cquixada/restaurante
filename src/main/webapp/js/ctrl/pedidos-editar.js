app.controller('pedidosEditarCtrl', function($scope, $http) {
	let	itensSelecionados = [];
	let	id = obterParametroDaUrlPorNome('id');

	ItemProxy.listarTodos($http).then(function(response) {
		$scope.itens = response.data;
		console.log($scope.itens);
	});

	if (id) {
		PedidoProxy.obterPorId(id, $http).then(function(response) {
			$scope.pedido = response.data;
		});
	}
	
	$scope.adicionarItens = function(item) {
		delete item.$$hashKey;
		itensSelecionados.push(item);
	}

	$scope.remover = function() {
		id = $scope.pedido.id;

		PedidoProxy.remover(id, $http).then(
				function(response) {
					$scope.pedido = null;

					$("#global-message").addClass("alert-success").text(
							"Pedido excluído com sucesso.").show();
				});
	}

	$scope.salvar = function() {
		id = $scope.pedido.id;

		limparMensagensErro();

		$scope.pedido.itens = itensSelecionados;

		console.log(JSON.stringify($scope.pedido));

		if (id) {
			PedidoProxy.atualizar(id, $scope.pedido, $http).then(
					function(response) {
						$("#global-message").addClass("alert-success").text(
								"Pedido atualizado com sucesso.").show();
					}, tratarErro);

		} else {
			PedidoProxy.inserir($scope.pedido, $http).then(
					function(response) {
						$scope.pedido.id = response.data;

						$("#global-message").addClass("alert-success").text(
								"Pedido com id = " + response.data
										+ " criado com sucesso.").show();
					}, tratarErro);
		}
	}
});