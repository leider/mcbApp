package mcb.persistenz;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateStarter {

	private static SessionFactory sessionFactory;

	private static final String CONFIG_FILE = "mappingspec.cfg.xml";

	public static SessionFactory getSessionFactory() {
		return HibernateStarter.sessionFactory;
	}

	public static void initHibernate() {
		try {
			Configuration configuration = new Configuration().configure(HibernateStarter.CONFIG_FILE);
			configuration.addResource("MCB.hbm.xml", HibernateStarter.class.getClassLoader());
			HibernateStarter.sessionFactory = configuration.buildSessionFactory();
		} catch (Throwable t) {
			throw new ExceptionInInitializerError(t);
		}
	}

	public static void stopHibernate() {
		HibernateStarter.getSessionFactory().close();
	}

}
