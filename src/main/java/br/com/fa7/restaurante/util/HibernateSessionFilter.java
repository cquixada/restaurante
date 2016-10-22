package br.com.fa7.restaurante.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.Session;

public class HibernateSessionFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		Session session = null;

		try {
			session = HibernateFactory.getHibernateSession();

			session.beginTransaction();
			chain.doFilter(request, response);
			session.getTransaction().commit();

		} catch (Throwable e) {
			if (session.getTransaction().isActive()) {
				session.getTransaction().rollback();
			}

			throw new ServletException(e);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

}
