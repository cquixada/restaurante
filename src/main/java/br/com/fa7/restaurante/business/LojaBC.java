package br.com.fa7.restaurante.business;

import java.util.List;

import javax.inject.Inject;

import br.com.fa7.restaurante.dao.LojaDAO;
import br.com.fa7.restaurante.model.Loja;

public class LojaBC {
	@Inject
	private LojaDAO dao;

	public List<Loja> listarTodos() {
		return dao.listarTodos();
	}

	public Loja obterPorId(Long idLoja) throws LojaNaoEncontradaException {
		Loja loja = dao.obterPorId(idLoja);

		if (loja == null) {
			throw new LojaNaoEncontradaException();
		}

		return loja;
	}
}
