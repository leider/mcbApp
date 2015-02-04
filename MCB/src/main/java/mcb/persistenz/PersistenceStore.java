package mcb.persistenz;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Date;

public class PersistenceStore {

  private static void backupDaten() {
    try {
      String treffenPath = PersistenceStore.TREFFEN_FILE.getCanonicalPath();
      PersistenceStore.copyFile(PersistenceStore.TREFFEN_FILE, new File(treffenPath + new Date().getTime()));
      String adressenPath = PersistenceStore.ADRESSEN_FILE.getCanonicalPath();
      PersistenceStore.copyFile(PersistenceStore.ADRESSEN_FILE, new File(adressenPath + new Date().getTime()));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void copyFile(File sourceFile, File destFile) throws IOException {
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

  private static final File TREFFEN_FILE = new File("./data/treffen.json");

  private static final File ADRESSEN_FILE = new File("./data/adressen.json");

  private final Adressen adressen = new Adressen();

  private final Treffens treffens = new Treffens();

  public PersistenceStore() {
    super();
    this.startup();
  }

  public Adressen getAdressen() {
    return this.adressen;
  }

  public Treffens getTreffens() {
    return this.treffens;
  }

  private void loadDaten() {
    ImAndExporter.importiereTreffen(PersistenceStore.TREFFEN_FILE, this.treffens);
    ImAndExporter.importiereAdressen(PersistenceStore.ADRESSEN_FILE, this.adressen, this.treffens);
    Summaries.getInstance().initForBesuche(this.adressen);
  }

  public void saveAll() {
    Summaries.getInstance().update();
    ImAndExporter.exportTreffen(PersistenceStore.TREFFEN_FILE, this.treffens);
    ImAndExporter.exportAdressen(PersistenceStore.ADRESSEN_FILE, this.adressen);
  }

  private void startup() {
    PersistenceStore.backupDaten();
    this.loadDaten();
  }

}
