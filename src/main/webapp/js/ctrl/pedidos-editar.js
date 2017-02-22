app.controller('pedidosEditarCtrl', function($scope, $http) {
	var itensSelecionados;
	var id = obterParametroDaUrlPorNome('id');

	$scope.iniciar = function() {
		let itensCardapioAux = [];
		let itensPedidoAux = [];
		itensSelecionados = [];
		$scope.total = 0;
		
		ItemProxy.listarTodos($http).then(function(response) {
			$scope.itens = response.data;
	
			$scope.itens.forEach(function(item, ind) {
				let itemCardapio = {
					id : null,
					especificacaoItem : item,
					quantidade : 0
				};
				
				itensCardapioAux.push(itemCardapio);
			});
			
			if (id) {
				PedidoProxy.obterPorId(id, $http).then(function(response) {
					$scope.pedido = response.data;
					itensPedidoAux = $scope.pedido.itensPedido;
					
					itensCardapioAux.forEach(function(itemC, indC) {
						itensPedidoAux.forEach(function(itemP, indP) {
							if (itemC.especificacaoItem.id == itemP.especificacaoItem.id) {
								itensCardapioAux[indC].id = itemP.id;
								itensCardapioAux[indC].quantidade = itemP.quantidade;
								
								$scope.alternarItem(itensCardapioAux[indC]);
							}
						});
					});
				});
			}
			
			$scope.itensCardapio = itensCardapioAux;
		});
	}

	$scope.alternarItem = function(item) {
		delete item.$$hashKey;

		let indice = itensSelecionados.indexOf(item);

		if (indice == -1) {
			if (item.quantidade == 0) {
				item.quantidade = 1;
			}
			
			itensSelecionados.push(item);
			
		} else {
			item.quantidade = 0;
			
			delete itensSelecionados[indice];
		}
		
		$scope.calcularTotal();
	}
	
	$scope.calcularTotal = function() {
		$scope.total = 0;
		
		itensSelecionados.forEach(function(itemS, indS) {
			let qtde = itemS.quantidade == 0 ? 1 : itemS.quantidade;
			
			$scope.total += parseInt(qtde) * parseFloat(itemS.especificacaoItem.preco);
		});
	}

	$scope.remover = function() {
		id = $scope.pedido.id;

		PedidoProxy.remover(id, $http).then(function(response) {
			$("#global-message").addClass("alert-success").text(
				"Pedido com id = " + id +
					" excluído com sucesso.").show();
			
			$scope.pedido = null;
			id = null;
			$scope.iniciar();
		});
	}

	$scope.salvar = function() {
		id = $scope.pedido.id;

		limparMensagensErro();

		let itensPedido = [];

		itensSelecionados.forEach(function(itemS, indS) {
			if (itemS != null) {
				let itemPedido = {
					id : itemS.id,
					especificacaoItem : itemS.especificacaoItem,
					quantidade : itemS.quantidade == 0 ? 1 : itemS.quantidade
				};
	
				itensPedido.push(itemPedido);
			}
		});
		
		if (itensPedido.length == 0) {
			$("#global-message").addClass("alert-danger").text(
				"Informe pelo menos um item para o pedido.").show();
			
			return;
		}
		
		$scope.pedido.itensPedido = itensPedido;
		
		if (id) {
			PedidoProxy.atualizar(id, $scope.pedido, $http).then(
				function(response) {
					$("#global-message").addClass("alert-success").text(
						"Pedido com id = " + id +
							" atualizado com sucesso.").show();
			}, tratarErro);

		} else {
			PedidoProxy.inserir($scope.pedido, $http).then(
				function(response) {
					$("#global-message").addClass("alert-success").text(
						"Pedido com id = " + response.data
							+ " criado com sucesso.").show();
			}, tratarErro);
		}
		
		$("#salvar").hide();
	}
	
	$scope.iniciar();
});