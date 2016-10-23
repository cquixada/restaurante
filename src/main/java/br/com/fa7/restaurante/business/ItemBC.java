package br.com.fa7.restaurante.business;

import java.util.List;

import javax.inject.Inject;

import br.com.fa7.restaurante.dao.ItemDAO;
import br.com.fa7.restaurante.model.EspecificacaoItem;

public class ItemBC {
	@Inject
	private ItemDAO dao;

	public List<EspecificacaoItem> listarTodos() {
		return dao.listarTodos();
	}

	public EspecificacaoItem obterPorId(Long idLoja) throws LojaNaoEncontradaException {
		EspecificacaoItem item = dao.obterPorId(idLoja);

		if (item == null) {
			throw new LojaNaoEncontradaException();
		}

		return item;
	}
}
