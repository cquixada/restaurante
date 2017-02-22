package br.com.fa7.restaurante.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import br.com.fa7.restaurante.util.HibernateFactory;

public abstract class GenericDAO<T extends Serializable> {

	protected final Session session;

	private Class<T> classeEntidade;

	public GenericDAO(Class<T> classeEntidade) throws Exception {
		this.classeEntidade = classeEntidade;
		this.session = HibernateFactory.getHibernateSession();
	}

	public void salvar(T t) {
		session.saveOrUpdate(t);
	}

	@SuppressWarnings("unchecked")
	public List<T> listarTodos() {
		return session.createQuery("from " + classeEntidade.getName()).list();
	}

	@SuppressWarnings("unchecked")
	public T obterPorId(Serializable id) {
		return (T) session.get(classeEntidade, id);
	}

	public void remover(T t) {
		session.delete(t);
	}
}
