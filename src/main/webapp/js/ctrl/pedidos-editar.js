﻿app.controller('pedidosEditarCtrl', function($scope, $http) {
	let itensSelecionados = [];
	let id = obterParametroDaUrlPorNome('id');

	ItemProxy.listarTodos($http).then(function(response) {
		$scope.itens = response.data;
		console.log($scope.itens);
	});
	
	$scope.adicionarItens = function(item){
		itensSelecionados.push(item);
	}

	if (id) {
		PedidoProxy.obterPorId(id, $http).then(function(response) {
			$scope.pedido = response.data;
		});
	}

	$scope.remover = function() {
		id = $scope.pedido.id;

		PedidoProxy.remover(id, $http).then(
				function(response) {
					$scope.pedido = null;

					$("#global-message").addClass("alert-success").text(
							"Funcionário excluído com sucesso.").show();
				});
	}

	$scope.salvar = function() {
		id = $scope.pedido.id;

		limparMensagensErro();
		
		console.log(JSON.stringify($scope.pedido));

		if (id) {
			PedidoProxy.atualizar(id, $scope.pedido, $http).then(
					function(response) {
						$("#global-message").addClass("alert-success").text(
								"Funcionário atualizado com sucesso.").show();
					}, tratarErro);

		} else {
			$scope.pedido.itens = itensSelecionados;
			PedidoProxy.inserir($scope.pedido, $http).then(
					function(response) {
						$scope.pedido.id = response.data;

						$("#global-message").addClass("alert-success").text(
								"Funcionário com id = " + response.data
										+ " criado com sucesso.").show();
					}, tratarErro);
		}
	}
});