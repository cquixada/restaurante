package br.com.fa7.restaurante.business;

import java.util.ArrayList;
import java.util.List;

public class ValidacaoException extends Exception {
	private static final long serialVersionUID = 7569137943780192780L;

	private List<Erro> erros = new ArrayList<>();

	public List<Erro> getErros() {
		return erros;
	}

	public void adicionar(String entidade, String propriedade, String mensagem) {
		erros.add(new Erro(entidade, propriedade, mensagem));
	}

	public class Erro {
		private String entidade;

		private String propriedade;

		private String mensagem;

		public Erro(String entidade, String propriedade, String mensagem) {
			super();
			this.entidade = entidade;
			this.propriedade = propriedade;
			this.mensagem = mensagem;
		}

		public String getEntidade() {
			return entidade;
		}

		public String getPropriedade() {
			return propriedade;
		}

		public String getMensagem() {
			return mensagem;
		}

	}
}
