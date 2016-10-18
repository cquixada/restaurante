package br.com.fa7.restaurante.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class GenericDAO<T extends BaseModel> {

	@PersistenceContext(unitName = "unidadePersistencia")
	protected EntityManager em;

	private Class<T> classeEntidade;

	public GenericDAO(Class<T> classeEntidade) {
		this.classeEntidade = classeEntidade;
	}

	public void salvar(T t) {
		if (t.getId() == null) {
			em.persist(t);

		} else {
			em.merge(t);
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> listarTodos() {
		return em.createQuery("from " + classeEntidade.getName()).getResultList();
	}

	public T obterPorId(Serializable id) {
		return em.find(classeEntidade, id);
	}

	public void remover(T t) {
		em.remove(t);
	}
}
