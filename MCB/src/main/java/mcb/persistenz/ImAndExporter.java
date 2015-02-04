package mcb.persistenz;

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
import mcb.persistenz.json.AdresseFactory;
import mcb.persistenz.json.BesuchFactory;
import mcb.persistenz.json.DoWithReader;
import mcb.persistenz.json.DoWithWriter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class ImAndExporter {

  public static void exportAdressen(File file, final Adressen adressen) {
    DoWithWriter performer = new DoWithWriter() {
      @Override
      public void statement(OutputStreamWriter writer) throws IOException {
        JSONSerializer jsonSerializer = new JSONSerializer();
        jsonSerializer.include("besuchteTreffen");
        jsonSerializer.include("besuchteTreffen.treffen.id");
        jsonSerializer.exclude("besuchteTreffen.treffen.*");
        for (Adresse adresse : adressen.alle()) {
          writer.write(jsonSerializer.serialize(adresse));
          writer.write("\n");
        }
      }
    };
    ImAndExporter.exportiere(file, performer);
  }

  private static void exportiere(File file, DoWithWriter performer) {
    OutputStreamWriter writer = null;
    try {
      if (!file.exists()) {
        file.getParentFile().mkdir();
        file.createNewFile();
      }
      FileOutputStream stream = new FileOutputStream(file);
      writer = new OutputStreamWriter(stream, "UTF-8");
      performer.statement(writer);
      writer.flush();
    } catch (IOException e) {
      ImAndExporter.LOGGER.fatal(e.getMessage(), e);
    } finally {
      try {
        writer.close();
      } catch (IOException e) {
        ImAndExporter.LOGGER.fatal(e.getMessage(), e);
      }
    }
  }

  public static void exportTreffen(File file, final Treffens treffens) {
    DoWithWriter performer = new DoWithWriter() {
      @Override
      public void statement(OutputStreamWriter writer) throws IOException {
        JSONSerializer jsonSerializer = new JSONSerializer();
        for (Treffen treffen : treffens.getAlleTreffen()) {
          writer.write(jsonSerializer.serialize(treffen));
          writer.write("\n");
        }
      }
    };
    ImAndExporter.exportiere(file, performer);
  }

  private static void importiere(File file, DoWithReader performer) {
    BufferedReader reader = null;
    try {
      if (file.exists()) {
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
        performer.statement(reader);
      }
    } catch (IOException e) {
      ImAndExporter.LOGGER.fatal(e.getMessage(), e);
    } finally {
      try {
        reader.close();
      } catch (IOException e) {
        ImAndExporter.LOGGER.fatal(e.getMessage(), e);
      }
    }
  }

  public static void importiereAdressen(File file, final Adressen adressen, final Treffens treffens) {
    DoWithReader performer = new DoWithReader() {
      @Override
      public void statement(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        JSONDeserializer<Adresse> jsonDeserializer = new JSONDeserializer<Adresse>();
        jsonDeserializer.use(Adresse.class, new AdresseFactory());
        jsonDeserializer.use(Besuch.class, new BesuchFactory(treffens.getAlleTreffen()));
        while (line != null) {
          adressen.add(jsonDeserializer.deserialize(line));
          line = reader.readLine();
        }
      }
    };
    ImAndExporter.importiere(file, performer);
  }

  public static void importiereTreffen(File file, final Treffens treffens) {
    DoWithReader performer = new DoWithReader() {
      @Override
      public void statement(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        JSONDeserializer<Treffen> jsonDeserializer = new JSONDeserializer<Treffen>();
        while (line != null) {
          treffens.add(jsonDeserializer.deserialize(line));
          line = reader.readLine();
        }
      }
    };
    ImAndExporter.importiere(file, performer);
  }

  public static final Logger LOGGER = LogManager.getLogger();

  private ImAndExporter() {
    super();
  }

}
