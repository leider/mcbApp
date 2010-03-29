package mcb.persistenz;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import mcb.model.Adresse;
import mcb.model.Besuch;
import mcb.model.McbModel;
import mcb.model.Summaries;
import mcb.model.Treffen;
import mcb.persistenz.filter.AdresseFilter;
import mcb.persistenz.filter.AlleFilter;
import mcb.persistenz.filter.SucheFilter;

import org.hibernate.Query;
import org.hibernate.classic.Session;

import com.jgoodies.binding.list.ArrayListModel;

public class ApplicationData {

	public static final AlleFilter ALLE_FILTER = new AlleFilter();

	public static final SucheFilter SUCHE_FILTER = new SucheFilter();

	public static final DateFormat DATE_FORMAT = DateFormat.getDateInstance();

	static List<Adresse> adressen = new ArrayListModel<Adresse>();

	static List<Treffen> treffen = new ArrayListModel<Treffen>();

	private static Summaries summaries = new Summaries();

	private static AdresseFilter filter = ApplicationData.ALLE_FILTER;

	static {
		HibernateStarter.initHibernate();
		ApplicationData.loadDaten();
	}

	static void closeSession(Session session) {
		session.close();
		ApplicationData.summaries.initForBesuche();
	}

	public static List<Besuch> getAktuelleBesuche() {
		List<Besuch> result = new ArrayList<Besuch>();
		for (Adresse adresse : ApplicationData.adressen) {
			if (adresse.getAktuellenBesuch() != null) {
				result.add(adresse.getAktuellenBesuch());
			}
		}
		return result;
	}

	public static Treffen getAktuellesTreffen() {
		for (Treffen theTreffen : ApplicationData.getAlleTreffen()) {
			if (theTreffen.isAktuell()) {
				return theTreffen;
			}
		}
		return null;
	}

	public static List<Adresse> getAlleAdressen() {
		List<Adresse> result = new ArrayList<Adresse>();
		for (Adresse adresse : ApplicationData.adressen) {
			if (ApplicationData.filter.matches(adresse)) {
				result.add(adresse);
			}
		}
		Comparator<Adresse> adresseComparator = new Comparator<Adresse>() {

			@Override
			public int compare(Adresse adresse1, Adresse adresse2) {
				return adresse1.getName().toUpperCase().compareTo(adresse2.getName().toUpperCase());
			}

		};
		Collections.sort(result, adresseComparator);
		return result;
	}

	public static List<Treffen> getAlleTreffen() {
		return ApplicationData.treffen;
	}

	public static List<Adresse> getEmailAdressen() {
		List<Adresse> result = new ArrayList<Adresse>();
		List<Adresse> alleAdressen = ApplicationData.getAlleAdressen();
		for (Adresse adresse : alleAdressen) {
			if (adresse.hatGueltigeEmail()) {
				result.add(adresse);
			}
		}
		return result;
	}

	public static Treffen getNeuestesTreffen() {
		if (ApplicationData.getAlleTreffen().isEmpty()) {
			return null;
		}
		List<Treffen> treffenCopy = new ArrayList<Treffen>(ApplicationData.getAlleTreffen());
		Collections.sort(treffenCopy, new Comparator<Treffen>() {

			public int compare(Treffen t1, Treffen t2) {
				return -1 * t1.getErsterTag().compareTo(t2.getErsterTag());
			}
		});
		return treffenCopy.get(0);
	}

	public static Summaries getSummaries() {
		return ApplicationData.summaries;
	}

	@SuppressWarnings("unchecked")
	private static void loadDaten() {
		Session session = HibernateStarter.getSessionFactory().openSession();
		Query query = session.createQuery("from Adresse");
		ApplicationData.adressen.addAll(query.list());
		query = session.createQuery("from Treffen");
		ApplicationData.treffen.addAll(query.list());
		Collections.sort(ApplicationData.treffen);
		ApplicationData.closeSession(session);
	}

	public static void loescheModel(final McbModel model) throws McbException {
		new PersistenceActionPerformer().performInTransaction(new TransactionAction() {

			@Override
			public void runIn(PersistenceActionPerformer persistenceActionPerformer) {
				persistenceActionPerformer.delete(model);
				if (model instanceof Adresse) {
					ApplicationData.adressen.remove(model);
				}
				if (model instanceof Treffen) {
					ApplicationData.treffen.remove(model);
				}
			}
		});
	}

	public static void saveAdresse(final Adresse adresse) throws McbException {
		new PersistenceActionPerformer().performInTransaction(new TransactionAction() {

			@Override
			public void runIn(PersistenceActionPerformer persistenceActionPerformer) {
				for (McbModel besuch : adresse.getBesuchteTreffen()) {
					persistenceActionPerformer.saveOrUpdate(besuch);
				}
				boolean neu = adresse.getId() == null;
				persistenceActionPerformer.saveOrUpdate(adresse);
				if (neu) {
					ApplicationData.adressen.add(adresse);
				}
			}
		});
	}

	public static void saveTreffen(final Treffen theTreffen) throws McbException {
		new PersistenceActionPerformer().performInTransaction(new TransactionAction() {

			@Override
			public void runIn(PersistenceActionPerformer persistenceActionPerformer) {
				boolean neu = theTreffen.getId() == null;
				persistenceActionPerformer.saveOrUpdate(theTreffen);
				if (neu) {
					ApplicationData.treffen.add(theTreffen);
				}

			}
		});
	}

	public static void setFilter(AdresseFilter theFilter) {
		ApplicationData.filter = theFilter;
	}

}
