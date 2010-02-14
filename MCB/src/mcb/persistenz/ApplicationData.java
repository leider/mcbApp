package mcb.persistenz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import mcb.persistenz.filter.AdresseFilter;
import mcb.persistenz.filter.AlleFilter;
import mcb.persistenz.filter.SucheFilter;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import com.jgoodies.binding.beans.Model;
import com.jgoodies.binding.list.ArrayListModel;

public class ApplicationData {

	public static final AlleFilter ALLE_FILTER = new AlleFilter();

	public static final SucheFilter SUCHE_FILTER = new SucheFilter();

	public static DateFormat DATE_FORMAT = DateFormat.getDateInstance();

	private static List<Adresse> adressen = new ArrayListModel<Adresse>();

	private static List<Treffen> treffen = new ArrayListModel<Treffen>();

	private static Summaries summaries = new Summaries();

	private static AdresseFilter filter = ApplicationData.ALLE_FILTER;

	static {
		HibernateStarter.initHibernate();
		ApplicationData.loadDaten();
	}

	private static void closeSession(Session session) {
		session.close();
		int samstag = 0;
		int sonntag = 0;
		int meldungen = 0;
		for (Adresse adresse : ApplicationData.getAlleAdressen()) {
			if (adresse.getAktuellesTreffen() != null) {
				samstag = samstag + adresse.getAktuellesTreffen().getFruehstueckSamstag();
				sonntag = sonntag + adresse.getAktuellesTreffen().getFruehstueckSonntag();
				meldungen++;
			}
		}

		ApplicationData.summaries.setFruehstueckSamstag(samstag);
		ApplicationData.summaries.setFruehstueckSonntag(sonntag);
		ApplicationData.summaries.setAnzahlMeldungen(meldungen);
	}

	private static Adresse createAdresse(String line) {
		Adresse result = new Adresse();
		result.parse(line);
		if (result.getName().equals("")) {
			return null;
		}
		return result;
	}

	private static Treffen createOrGetTreffen(int i) {
		for (Treffen theTreffen : ApplicationData.getAlleTreffen()) {
			if (theTreffen.getJahr() == i) {
				return theTreffen;
			}
		}
		Treffen result = new Treffen();
		result.setErsterTagString("1.1." + i);
		result.setLetzterTagString("31.12." + i);
		result.setName("Dummy " + i);
		ApplicationData.saveTreffen(result);
		ApplicationData.getAlleTreffen().add(result);
		return result;
	}

	public static void exportAdressen(File file, boolean alle) {
		try {
			FileOutputStream stream = new FileOutputStream(file);
			OutputStreamWriter writer = new OutputStreamWriter(stream, "ISO-8859-1");
			Adresse.writeHeaderOn(writer);
			for (Adresse adresse : alle ? ApplicationData.adressen : ApplicationData.getAlleAdressen()) {
				adresse.writeOn(writer, alle);
			}
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static List<Treffen> findAlleTreffen(String line) {
		List<Treffen> result = new ArrayList<Treffen>();
		String[] tokens = line.split(";");
		if (ApplicationData.istWahr(tokens[12])) {
			result.add(ApplicationData.createOrGetTreffen(2004));
		}
		if (ApplicationData.istWahr(tokens[13])) {
			result.add(ApplicationData.createOrGetTreffen(2005));
		}
		if (ApplicationData.istWahr(tokens[14])) {
			result.add(ApplicationData.createOrGetTreffen(2006));
		}
		if (ApplicationData.istWahr(tokens[15])) {
			result.add(ApplicationData.createOrGetTreffen(2007));
		}
		if (ApplicationData.istWahr(tokens[16])) {
			result.add(ApplicationData.createOrGetTreffen(2008));
		}
		if (ApplicationData.istWahr(tokens[17])) {
			result.add(ApplicationData.createOrGetTreffen(2009));
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

			public int compare(Treffen o1, Treffen o2) {
				return -1 * o1.getErsterTag().compareTo(o2.getErsterTag());
			}
		});
		return treffenCopy.get(0);
	}

	public static Summaries getSummaries() {
		return ApplicationData.summaries;
	}

	public static void importiere(File file) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			if (line.startsWith("id")) {
				line = reader.readLine();
			}
			while (line != null) {
				Adresse adresse = ApplicationData.createAdresse(line);
				if (adresse != null) {
					ApplicationData.saveAdresse(adresse);
					List<Treffen> findTreffen = ApplicationData.findAlleTreffen(line);
					for (Treffen theTreffen : findTreffen) {
						adresse.addTreffen(theTreffen);
					}
					ApplicationData.saveAdresse(adresse);
				}
				line = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static boolean istWahr(String zwoelf) {
		return !zwoelf.trim().equals("-");
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

	public static void loescheModel(Model model) {
		Session session = HibernateStarter.getSessionFactory().openSession();
		Transaction transe = session.beginTransaction();
		session.delete(model);
		if (model instanceof Adresse) {
			ApplicationData.adressen.remove(model);
		}
		if (model instanceof Treffen) {
			ApplicationData.treffen.remove(model);
		}
		transe.commit();
		ApplicationData.closeSession(session);
	}

	public static void saveAdresse(Adresse adresse) {
		Session session = HibernateStarter.getSessionFactory().openSession();
		Transaction transe = session.beginTransaction();
		for (Besuch besuch : adresse.getBesuchteTreffen()) {
			session.saveOrUpdate(besuch);
		}
		boolean neu = adresse.getId() == null;
		session.saveOrUpdate(adresse);
		transe.commit();
		if (neu) {
			ApplicationData.adressen.add(adresse);
		}
		ApplicationData.closeSession(session);
	}

	public static void saveTreffen(Treffen theTreffen) {
		Session session = HibernateStarter.getSessionFactory().openSession();
		Transaction transe = session.beginTransaction();
		boolean neu = theTreffen.getId() == null;
		session.saveOrUpdate(theTreffen);
		transe.commit();
		if (neu) {
			ApplicationData.treffen.add(theTreffen);
		}
		ApplicationData.closeSession(session);
	}

	public static void setFilter(AdresseFilter filter2) {
		ApplicationData.filter = filter2;
	}
}
