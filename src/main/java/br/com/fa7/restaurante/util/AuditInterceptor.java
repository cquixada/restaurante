package br.com.fa7.restaurante.util;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

public class AuditInterceptor extends EmptyInterceptor {
	private static final long serialVersionUID = 1016359394597759588L;

	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {

		for (int i = 0; i < propertyNames.length; i++) {
			if ("dataAtualizacao".equals(propertyNames[i])) {
				currentState[i] = new Date();

				return true;
			}
		}

		return false;
	}

	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		for (int i = 0; i < propertyNames.length; i++) {
			if ("dataRegistro".equals(propertyNames[i])) {
				state[i] = new Date();

				return true;
			}
		}

		return false;
	}
}
