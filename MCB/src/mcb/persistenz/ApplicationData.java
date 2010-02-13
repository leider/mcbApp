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

import mcb.mail.MailSender;
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

	private static AdresseFilter filter = ALLE_FILTER;

	static {
		HibernateStarter.initHibernate();
		loadDaten();
	}

	private static void closeSession(Session session) {
		session.close();
		int samstag = 0;
		int sonntag = 0;
		int meldungen = 0;
		for (Adresse adresse : getAlleAdressen()) {
			if (adresse.getAktuellesTreffen() != null) {
				samstag = samstag + adresse.getAktuellesTreffen().getFruehstueckSamstag();
				sonntag = sonntag + adresse.getAktuellesTreffen().getFruehstueckSonntag();
				meldungen++;
			}
		}

		summaries.setFruehstueckSamstag(samstag);
		summaries.setFruehstueckSonntag(sonntag);
		summaries.setAnzahlMeldungen(meldungen);
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
		for (Treffen treffen : getAlleTreffen()) {
			if (treffen.getJahr() == i) {
				return treffen;
			}
		}
		Treffen treffen = new Treffen();
		treffen.setErsterTagString("1.1." + i);
		treffen.setLetzterTagString("31.12." + i);
		treffen.setName("Dummy " + i);
		saveTreffen(treffen);
		getAlleTreffen().add(treffen);
		return treffen;
	}

	public static void exportAdressen(File file, boolean alle) {
		try {
			FileOutputStream stream = new FileOutputStream(file);
			OutputStreamWriter writer = new OutputStreamWriter(stream, "ISO-8859-1");
			Adresse.writeHeaderOn(writer);
			for (Adresse adresse : alle ? adressen : getAlleAdressen()) {
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
		if (istWahr(tokens[12])) {
			result.add(createOrGetTreffen(2004));
		}
		if (istWahr(tokens[13])) {
			result.add(createOrGetTreffen(2005));
		}
		if (istWahr(tokens[14])) {
			result.add(createOrGetTreffen(2006));
		}
		if (istWahr(tokens[15])) {
			result.add(createOrGetTreffen(2007));
		}
		if (istWahr(tokens[16])) {
			result.add(createOrGetTreffen(2008));
		}
		if (istWahr(tokens[17])) {
			result.add(createOrGetTreffen(2009));
		}
		return result;
	}

	public static Treffen getAktuellesTreffen() {
		for (Treffen treffen : getAlleTreffen()) {
			if (treffen.isAktuell()) {
				return treffen;
			}
		}
		return null;
	}

	public static List<Adresse> getAlleAdressen() {
		List<Adresse> result = new ArrayList<Adresse>();
		for (Adresse adresse : adressen) {
			if (filter.matches(adresse)) {
				result.add(adresse);
			}
		}
		return result;
	}

	public static List<Treffen> getAlleTreffen() {
		return treffen;
	}

	public static List<Adresse> getEmailAdressen() {
		List<Adresse> result = new ArrayList<Adresse>();
		List<Adresse> alleAdressen = getAlleAdressen();
		for (Adresse adresse : alleAdressen) {
			if (adresse.hatGueltigeEmail()) {
				result.add(adresse);
			}
		}
		return result;
	}

	public static Treffen getNeuestesTreffen() {
		if (getAlleTreffen().isEmpty()) {
			return null;
		}
		List<Treffen> treffenCopy = new ArrayList<Treffen>(getAlleTreffen());
		Collections.sort(treffenCopy, new Comparator<Treffen>() {

			public int compare(Treffen o1, Treffen o2) {
				return -1 * o1.getErsterTag().compareTo(o2.getErsterTag());
			}
		});
		return treffenCopy.get(0);
	}

	public static Summaries getSummaries() {
		return summaries;
	}

	public static void importiere(File file) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			if (line.startsWith("id")) {
				line = reader.readLine();
			}
			while (line != null) {
				Adresse adresse = createAdresse(line);
				if (adresse != null) {
					saveAdresse(adresse);
					List<Treffen> findTreffen = findAlleTreffen(line);
					for (Treffen treffen : findTreffen) {
						adresse.addTreffen(treffen);
					}
					saveAdresse(adresse);
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
		adressen.addAll(query.list());
		query = session.createQuery("from Treffen");
		treffen.addAll(query.list());
		Collections.sort(treffen);
		closeSession(session);
	}

	public static void loescheModel(Model model) {
		Session session = HibernateStarter.getSessionFactory().openSession();
		Transaction transe = session.beginTransaction();
		session.delete(model);
		if (model instanceof Adresse) {
			adressen.remove(model);
		}
		if (model instanceof Treffen) {
			treffen.remove(model);
		}
		transe.commit();
		closeSession(session);
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
			adressen.add(adresse);
		}
		closeSession(session);
	}

	public static void saveTreffen(Treffen treffen) {
		Session session = HibernateStarter.getSessionFactory().openSession();
		Transaction transe = session.beginTransaction();
		boolean neu = treffen.getId() == null;
		session.saveOrUpdate(treffen);
		transe.commit();
		if (neu) {
			ApplicationData.treffen.add(treffen);
		}
		closeSession(session);
	}

	public static void sendMail() {
		MailSender sender = new MailSender();
		String subject = getNeuestesTreffen().getBeschreibung();
		for (Adresse adresse : getEmailAdressen()) {
			String to = adresse.getEmail();
			String body = getNeuestesTreffen().getEmailPreviewText(adresse.getVorname());
			try {
				sender.send(to, subject, body);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// Properties props = new Properties();
		// javax.mail.Session session = javax.mail.Session
		// .getDefaultInstance(props);
		// MimeMessage mimeMessage = new MimeMessage(session);
		// mimeMessage.setText(getNeuestesTreffen().getEmailPreviewText());
		// Address absender = new InternetAddress("leider@me.com");
		// mimeMessage.setFrom(absender);
		// mimeMessage.setRecipients(javax.mail.Message.RecipientType.BCC,
		// getEmailAdressen());
		// mimeMessage.setSubject(getNeuestesTreffen().getBeschreibung());
		// Transport.send(mimeMessage);
	}

	public static void setFilter(AdresseFilter filter2) {
		filter = filter2;
	}
}
