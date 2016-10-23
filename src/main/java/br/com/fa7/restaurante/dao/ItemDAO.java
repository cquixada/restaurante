package br.com.fa7.restaurante.dao;

import br.com.fa7.restaurante.model.EspecificacaoItem;

public class ItemDAO extends GenericDAO<EspecificacaoItem>{

	public ItemDAO() throws Exception {
		super(EspecificacaoItem.class);
	}
}
