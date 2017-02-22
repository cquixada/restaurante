function tratarErro(response) {
	switch (response.status) {
	case 404:
		$("#global-message").addClass("alert-danger").text(
				"O registro solicitado não foi encontrado!").show();

		break;

	case 406:
		$("form input").each(function() {
			var id = $(this).attr("id");
			var message = null;

			$.each(response.data, function(index, value) {
				if (id == value.propriedade) {
					message = value.mensagem;
				}
			});

			if (message) {
				$("#" + id).parent().addClass("has-error");
				$("#" + id + "-message").html(message).show();

				$(this).focus();

			} else {
				$("#" + id).parent().removeClass("has-error");
				$("#" + id + "-message").hide();
			}
		});

		$("#global-message").addClass("alert-danger").text(
				"Verifique erros no formulário!").show();

		break;

	default:
		$("#global-message").addClass("alert-danger").text("Erro inesperado.")
				.show();

		break;
	}
}

function obterParametroDaUrlPorNome(name) {
	name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");

	var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"), results = regex
			.exec(location.search);

	return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g,
			" "));
}

function limparMensagensErro() {
	$("#global-message").removeClass("alert-success alert-info alert-warning alert-danger").empty()
			.hide();
	$(".text-danger").parent().removeClass("has-error");
	$(".text-danger").hide();
}

function removerHashKey(str) {
	return str.replace(/,?\"\$\$hashKey\":\"\w+:\w+\"/i, "");
}