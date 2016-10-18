var LojaProxy = {
	url : "api/lojas",
	obterPorId : function(id, $http) {
		return $http({
			method : "GET",
			url : this.url + "/" + id
		});
	},
	listarTodos : function($http) {
		return $http({
			method : "GET",
			url : this.url
		});
	}
};