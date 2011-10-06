package mcb.persistenz;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import mcb.model.Adresse;
import mcb.model.Besuch;
import mcb.model.McbModel;
import mcb.model.Summaries;
import mcb.model.Treffen;
import mcb.persistenz.filter.AdresseFilter;
import mcb.persistenz.filter.AlleFilter;
import mcb.persistenz.filter.SucheFilter;
import mcb.persistenz.json.ImAndExporter;

import com.jgoodies.binding.list.ArrayListModel;

public class ApplicationData {

	private static final File TREFFEN_FILE = new File("./data/treffen.json");

	private static final File ADRESSEN_FILE = new File("./data/adressen.json");

	public static final AlleFilter ALLE_FILTER = new AlleFilter();

	public static final SucheFilter SUCHE_FILTER = new SucheFilter();

	private static final DateFormat DATE_FORMAT = DateFormat.getDateInstance();

	public static List<Adresse> adressen = new ArrayListModel<Adresse>();

	static List<Treffen> treffen = new ArrayListModel<Treffen>();

	private static Summaries summaries = new Summaries();

	private static AdresseFilter filter = ApplicationData.ALLE_FILTER;

	static {
		ApplicationData.backupDaten();
		ApplicationData.loadDaten();
	}

	public static void add(Adresse adresse) {
		if (adresse.isNeu()) {
			adresse.setId(ApplicationData.nextIdForAdressen());
		}
		ApplicationData.adressen.add(adresse);
	}

	public static void add(Treffen treffen) {
		if (treffen.isNeu()) {
			treffen.setId(ApplicationData.nextIdForTreffen());
		}
		ApplicationData.treffen.add(treffen);
	}

	private static void backupDaten() {
		try {
			String treffenPath = ApplicationData.TREFFEN_FILE.getCanonicalPath();
			ApplicationData.copyFile(ApplicationData.TREFFEN_FILE, new File(treffenPath + new Date().getTime()));
			String adressenPath = ApplicationData.ADRESSEN_FILE.getCanonicalPath();
			ApplicationData.copyFile(ApplicationData.ADRESSEN_FILE, new File(adressenPath + new Date().getTime()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void copyFile(File sourceFile, File destFile) throws IOException {
		if (!destFile.exists()) {
			destFile.createNewFile();
		}

		FileChannel source = null;
		FileChannel destination = null;
		try {
			source = new FileInputStream(sourceFile).getChannel();
			destination = new FileOutputStream(destFile).getChannel();
			destination.transferFrom(source, 0, source.size());
		} finally {
			if (source != null) {
				source.close();
			}
			if (destination != null) {
				destination.close();
			}
		}
	}

	public static String formatDate(Date date) {
		synchronized (ApplicationData.DATE_FORMAT) {
			return ApplicationData.DATE_FORMAT.format(date);
		}
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

	public static List<Treffen> getAlleTreffen() {
		return ApplicationData.treffen;
	}

	public static List<Adresse> getEmailAdressen() {
		List<Adresse> result = new ArrayList<Adresse>();
		List<Adresse> alleAdressen = ApplicationData.getFilteredAdressen();
		for (Adresse adresse : alleAdressen) {
			if (adresse.hatGueltigeEmail()) {
				result.add(adresse);
			}
		}
		return result;
	}

	public static List<Adresse> getFilteredAdressen() {
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

	private static void loadDaten() {
		ImAndExporter.importiereTreffen(ApplicationData.TREFFEN_FILE);
		ImAndExporter.importiereAdressen(ApplicationData.ADRESSEN_FILE);
		ApplicationData.summaries.initForBesuche();
	}

	public static void loescheModel(final McbModel model) {
		if (model instanceof Adresse) {
			ApplicationData.adressen.remove(model);
			ImAndExporter.exportAdressen(ApplicationData.ADRESSEN_FILE);
		}
		if (model instanceof Treffen) {
			ApplicationData.treffen.remove(model);
			ImAndExporter.exportTreffen(ApplicationData.TREFFEN_FILE);
		}

	}

	private static long nextIdFor(List<? extends McbModel> originals) {
		List<McbModel> models = new ArrayList<McbModel>(originals);
		if (models.isEmpty()) {
			return Long.valueOf(1);
		}
		Collections.sort(models, new McbModel.Comp());
		return models.get(0).getId() + 1;
	}

	private static Long nextIdForAdressen() {
		return ApplicationData.nextIdFor(ApplicationData.adressen);
	}

	private static Long nextIdForTreffen() {
		return ApplicationData.nextIdFor(ApplicationData.treffen);
	}

	public static Date parseDate(String string) throws ParseException {
		synchronized (ApplicationData.DATE_FORMAT) {
			return ApplicationData.DATE_FORMAT.parse(string);
		}
	}

	public static void saveAdresse(final Adresse adresse) {
		ImAndExporter.exportAdressen(ApplicationData.ADRESSEN_FILE);
	}

	public static void saveTreffen(final Treffen treffen) {
		ImAndExporter.exportTreffen(ApplicationData.TREFFEN_FILE);
	}

	public static void setFilter(AdresseFilter theFilter) {
		ApplicationData.filter = theFilter;
	}
}
