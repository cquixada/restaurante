package br.com.fa7.restaurante.util;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.jmx.StatisticsService;
import org.hibernate.stat.Statistics;

public class HibernateFactory {

	private static SessionFactory sf;

	public static synchronized Session getHibernateSession() throws Exception {
		if (sf == null) {
			Configuration c = new AnnotationConfiguration();
			c.setInterceptor(new AuditInterceptor());
			sf = c.configure().buildSessionFactory();

			StatisticsService statsMBean = new StatisticsService();
			statsMBean.setSessionFactory(sf);
			statsMBean.setStatisticsEnabled(true);

			MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
			mBeanServer.registerMBean(statsMBean, new ObjectName("Hibernate:application=Statistics"));
		}

		return sf.getCurrentSession();
	}

	public static Statistics getStatistics() {
		if (!sf.getStatistics().isStatisticsEnabled()) {
			sf.getStatistics().setStatisticsEnabled(true);
		}

		return sf.getStatistics();
	}

	public static void closeSessionFactory() {
		if (sf != null) {
			sf.close();
		}
	}
}
