package br.com.fa7.restaurante.business;

public class ItemNaoEncontradoException extends Exception {
	private static final long serialVersionUID = -4895111353308787075L;

	public ItemNaoEncontradoException() {
		super("Nenhum item encontrado!");
	}
}
