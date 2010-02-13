package mcb.persistenz;

import java.sql.SQLException;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateStarter {

	private static SessionFactory sessionFactory;

	private static final String CONFIG_FILE = "mappingspec.cfg.xml";

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void initHibernate() {
		try {
			Configuration configuration = new Configuration().configure(CONFIG_FILE);
			configuration.addResource("MCB.hbm.xml", HibernateStarter.class.getClassLoader());
			sessionFactory = configuration.buildSessionFactory();
		} catch (Throwable t) {
			throw new ExceptionInInitializerError(t);
		}
	}

	public static void stopHibernate() throws SQLException {
		getSessionFactory().close();
	}

}
