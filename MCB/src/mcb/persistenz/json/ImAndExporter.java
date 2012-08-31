package mcb.persistenz.json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import mcb.model.Adresse;
import mcb.model.Besuch;
import mcb.model.Treffen;
import mcb.persistenz.Adressen;
import mcb.persistenz.Treffens;

import org.apache.log4j.Logger;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class ImAndExporter {

	public static final Logger LOGGER = Logger.getLogger(ImAndExporter.class.getName());

	public static void exportAdressen(File file, Adressen adressen) {
		try {
			if (!file.exists()) {
				file.getParentFile().mkdir();
				file.createNewFile();
			}
			FileOutputStream stream = new FileOutputStream(file);
			OutputStreamWriter writer = new OutputStreamWriter(stream, "UTF-8");
			JSONSerializer jsonSerializer = new JSONSerializer();
			jsonSerializer.include("besuchteTreffen");
			jsonSerializer.include("besuchteTreffen.treffen.id");
			jsonSerializer.exclude("besuchteTreffen.treffen.*");
			for (Adresse adresse : adressen.alle()) {
				writer.write(jsonSerializer.serialize(adresse));
				writer.write("\n");
			}
			writer.flush();
		} catch (IOException e) {
			ImAndExporter.LOGGER.fatal(e.getMessage(), e);
			e.printStackTrace();
		}
	}

	public static void exportTreffen(File file, Treffens treffens) {
		try {
			if (!file.exists()) {
				file.getParentFile().mkdir();
				file.createNewFile();
			}
			FileOutputStream stream = new FileOutputStream(file);
			OutputStreamWriter writer = new OutputStreamWriter(stream, "UTF-8");
			JSONSerializer jsonSerializer = new JSONSerializer();
			for (Treffen treffen : treffens.getAlleTreffen()) {
				writer.write(jsonSerializer.serialize(treffen));
				writer.write("\n");
			}
			writer.flush();
		} catch (IOException e) {
			ImAndExporter.LOGGER.fatal(e.getMessage(), e);
			e.printStackTrace();
		}
	}

	public static void importiereAdressen(File file, Adressen adressen, Treffens treffens) {
		try {
			if (file.exists()) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
				String line = reader.readLine();
				JSONDeserializer<Adresse> jsonDeserializer = new JSONDeserializer<Adresse>();
				jsonDeserializer.use(Adresse.class, new AdresseFactory());
				jsonDeserializer.use(Besuch.class, new BesuchFactory(treffens));
				while (line != null) {
					adressen.add(jsonDeserializer.deserialize(line));
					line = reader.readLine();
				}
			}
		} catch (IOException e) {
			ImAndExporter.LOGGER.fatal(e.getMessage(), e);
		}
	}

	public static void importiereTreffen(File file, Treffens treffens) {
		try {
			if (file.exists()) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
				String line = reader.readLine();
				JSONDeserializer<Treffen> jsonDeserializer = new JSONDeserializer<Treffen>();
				while (line != null) {
					treffens.add(jsonDeserializer.deserialize(line));
					line = reader.readLine();
				}
			}
		} catch (IOException e) {
			ImAndExporter.LOGGER.fatal(e.getMessage(), e);
		}

	}

	private ImAndExporter() {
		super();
	}

}
