package br.com.fa7.restaurante.business;

public class LojaNaoEncontradaException extends Exception {
	private static final long serialVersionUID = -4895111353308787075L;

	public LojaNaoEncontradaException() {
		super("Nenhuma loja encontrada!");
	}
}
