package mcb.persistenz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import mcb.model.Adresse;
import mcb.model.Treffen;

import org.apache.log4j.Logger;

public class ImAndExporter {

	static final Logger LOGGER = Logger.getLogger(ImAndExporter.class.getName());

	private static Adresse createAdresse(String line) {
		Adresse result = new Adresse();
		result.parse(line);
		if (result.getName().equals("")) {
			return null;
		}
		return result;
	}

	private static Treffen createOrGetTreffen(int i) throws McbException {
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
			ImAndExporter.LOGGER.fatal(e.getMessage(), e);
			e.printStackTrace();
		}
	}

	private static List<Treffen> findAlleTreffen(String line) throws McbException {
		List<Treffen> result = new ArrayList<Treffen>();
		String[] tokens = line.split(";");
		if (ImAndExporter.istWahr(tokens[12])) {
			result.add(ImAndExporter.createOrGetTreffen(2004));
		}
		if (ImAndExporter.istWahr(tokens[13])) {
			result.add(ImAndExporter.createOrGetTreffen(2005));
		}
		if (ImAndExporter.istWahr(tokens[14])) {
			result.add(ImAndExporter.createOrGetTreffen(2006));
		}
		if (ImAndExporter.istWahr(tokens[15])) {
			result.add(ImAndExporter.createOrGetTreffen(2007));
		}
		if (ImAndExporter.istWahr(tokens[16])) {
			result.add(ImAndExporter.createOrGetTreffen(2008));
		}
		if (ImAndExporter.istWahr(tokens[17])) {
			result.add(ImAndExporter.createOrGetTreffen(2009));
		}
		return result;
	}

	public static void importiere(File file) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			if (line.startsWith("id")) {
				line = reader.readLine();
			}
			while (line != null) {
				Adresse adresse = ImAndExporter.createAdresse(line);
				if (adresse != null) {
					try {
						ApplicationData.saveAdresse(adresse);
						List<Treffen> findTreffen = ImAndExporter.findAlleTreffen(line);
						for (Treffen theTreffen : findTreffen) {
							adresse.addTreffen(theTreffen);
						}
						ApplicationData.saveAdresse(adresse);
					} catch (McbException e) {
						ImAndExporter.LOGGER.fatal(e.getMessage(), e);
					}
				}
				line = reader.readLine();
			}
		} catch (IOException e) {
			ImAndExporter.LOGGER.fatal(e.getMessage(), e);
		}
	}

	private static boolean istWahr(String zwoelf) {
		return !zwoelf.trim().equals("-");
	}

	private ImAndExporter() {
		super();
	}

}
