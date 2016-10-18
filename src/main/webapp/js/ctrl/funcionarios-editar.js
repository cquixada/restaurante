app.controller('funcionariosEditarCtrl', function($scope, $http) {
	var id = obterParametroDaUrlPorNome('id');

	LojaProxy.listarTodos($http).then(function(response) {
		$scope.lojas = response.data;
		console.log(JSON.stringify($scope.lojas));
	});

	if (id) {
		FuncionarioProxy.obterPorId(id, $http).then(function(response) {
			$scope.funcionario = response.data;
		});
	}

	$scope.remover = function() {
		id = $scope.funcionario.id;

		FuncionarioProxy.remover(id, $http).then(
				function(response) {
					$scope.funcionario = null;

					$("#global-message").addClass("alert-success").text(
							"Funcionário excluído com sucesso.").show();
				});
	}

	$scope.salvar = function() {
		id = $scope.funcionario.id;

		limparMensagensErro();

		LojaProxy.obterPorId($scope.funcionario.loja.id, $http).then(
				function(response) {
					$scope.funcionario.loja = response.data;
					console.log(JSON.stringify($scope.funcionario.loja));
				});

		if (id) {
			FuncionarioProxy.atualizar(id, $scope.funcionario, $http).then(
					function(response) {
						$("#global-message").addClass("alert-success").text(
								"Funcionário atualizado com sucesso.").show();
					}, tratarErro);

		} else {
			FuncionarioProxy.inserir($scope.funcionario, $http).then(
					function(response) {
						$scope.funcionario.id = response.data;

						$("#global-message").addClass("alert-success").text(
								"Funcionário com id = " + response.data
										+ " criado com sucesso.").show();
					}, tratarErro);
		}
	}
});