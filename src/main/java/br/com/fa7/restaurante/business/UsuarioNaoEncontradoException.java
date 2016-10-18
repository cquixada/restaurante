package br.com.fa7.restaurante.business;

public class UsuarioNaoEncontradoException extends Exception {
	private static final long serialVersionUID = -4895111353308787075L;

	public UsuarioNaoEncontradoException() {
		super("Login e/ou senha invalidos!");
	}
}
