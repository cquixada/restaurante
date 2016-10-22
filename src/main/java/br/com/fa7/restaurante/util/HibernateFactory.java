package br.com.fa7.restaurante.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateFactory {

	private static SessionFactory sf;

	public static synchronized Session getHibernateSession() throws Exception {
		if (sf == null) {
			sf = new AnnotationConfiguration().configure().buildSessionFactory();
		}

		return sf.getCurrentSession();
	}

	public static void closeSessionFactory() {
		if (sf != null) {
			sf.close();
		}
	}
}
