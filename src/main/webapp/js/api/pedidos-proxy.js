var PedidoProxy = {
	url : "api/pedidos",
	obterPorId : function(id, $http) {
		return $http({
			method : "GET",
			url : this.url + "/" + id
		});
	},
	obterItensPedido : function(id, $http) {
		return $http({
			method : "GET",
			url : this.url + "/itensPedido/" + id
		});
	},
	listarTodos : function($http) {
		return $http({
			method : "GET",
			url : this.url
		});
	},
	inserir : function(entidade, $http) {
		return $http({
			method : "POST",
			url : this.url,
			data : removerHashKey(JSON.stringify(entidade)),
			headers : {
				'Content-Type' : 'application/json'
			}
		});
	},
	atualizar : function(id, entidade, $http) {
		return $http({
			method : "PUT",
			url : this.url + "/" + id,
			data : removerHashKey(JSON.stringify(entidade)),
			headers : {
				'Content-Type' : 'application/json'
			}
		});
	},
	remover : function(id, $http) {
		return $http({
			method : "DELETE",
			url : this.url + "/" + id
		});
	}
};