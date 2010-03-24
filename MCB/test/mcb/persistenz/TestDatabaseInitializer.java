package mcb.persistenz;

import java.util.List;

import mcb.model.Adresse;

import org.hibernate.Transaction;
import org.hibernate.classic.Session;

public class TestDatabaseInitializer {

	public static void main(String[] bullshit) {
		HibernateStarter.initHibernate();
		Session session = HibernateStarter.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		Adresse adresse = new Adresse();
		adresse.setVorname("Andreas");
		adresse.setName("Leidig");
		session.save(adresse);
		adresse = new Adresse();
		adresse.setVorname("Jochen");
		adresse.setName("Naake");
		session.save(adresse);
		transaction.commit();
		session.close();

		List<Adresse> alleAdressen = ApplicationData.getAlleAdressen();
		System.out.println(alleAdressen.size());
		HibernateStarter.stopHibernate();
	}

}
