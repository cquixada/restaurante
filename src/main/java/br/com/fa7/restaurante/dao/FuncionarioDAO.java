package br.com.fa7.restaurante.dao;

import br.com.fa7.restaurante.model.Funcionario;

public class FuncionarioDAO extends GenericDAO<Funcionario> {

	public FuncionarioDAO() throws Exception {
		super(Funcionario.class);
	}
}
