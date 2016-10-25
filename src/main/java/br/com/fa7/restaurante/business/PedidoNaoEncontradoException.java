package br.com.fa7.restaurante.business;

public class PedidoNaoEncontradoException extends Exception {
	private static final long serialVersionUID = -4895111353308787075L;

	public PedidoNaoEncontradoException() {
		super("Nenhum pedido encontrado!");
	}
}
