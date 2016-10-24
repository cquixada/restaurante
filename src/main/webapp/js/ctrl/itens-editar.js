app.controller('itensEditarCtrl', function($scope, $http) {
	var id = obterParametroDaUrlPorNome('id');

	if (id) {
		ItemProxy.obterPorId(id, $http).then(function(response) {
			$scope.item = response.data;
		});
	}

	$scope.remover = function() {
		id = $scope.item.id;

		ItemProxy.remover(id, $http).then(
				function(response) {
					$scope.item = null;

					$("#global-message").addClass("alert-success").text(
							"Item excluído com sucesso.").show();
				});
	}

	$scope.salvar = function() {
		id = $scope.item.id;

		limparMensagensErro();

		console.log(JSON.stringify($scope.item));

		if (id) {
			ItemProxy.atualizar(id, $scope.item, $http).then(
					function(response) {
						$("#global-message").addClass("alert-success").text(
								"Item atualizado com sucesso.").show();
					}, tratarErro);

		} else {
			ItemProxy.inserir($scope.item, $http).then(
					function(response) {console.log(JSON.stringify($scope.item));
						$scope.item.id = response.data;

						$("#global-message").addClass("alert-success").text(
								"Item com id = " + response.data
										+ " criado com sucesso.").show();
					}, tratarErro);
		}
	}
});