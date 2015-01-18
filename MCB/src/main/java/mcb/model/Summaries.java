package mcb.model;

import java.util.List;

import mcb.persistenz.Adressen;

public class Summaries extends McbModel {

  public static Summaries getInstance() {
    return Summaries.instance;
  }

  private static final long serialVersionUID = 2313140187842313961L;
  public static final String FRUEHSTUCK_SAMSTAG = "fruehstueckSamstag";
  public static final String FRUEHSTUCK_SONNTAG = "fruehstueckSonntag";

  public static final String ANZAHL_MELDUNGEN = "anzahlMeldungen";

  private static final Summaries instance = new Summaries();

  private Adressen adressen;

  private Summaries() {
    super();
  }

  private List<Besuch> aktuelleBesuche() {
    return this.adressen.getAktuelleBesuche();
  }

  private int anzahlFruehstueckAm(FruehstuecksTag tag) {
    int anzahl = 0;
    for (Besuch besuch : this.aktuelleBesuche()) {
      anzahl = anzahl + tag.getAnzahlFruehstueckAm(besuch);
    }
    return anzahl;
  }

  public int getAnzahlMeldungen() {
    return this.aktuelleBesuche().size();
  }

  public int getFruehstueckSamstag() {
    return this.anzahlFruehstueckAm(FruehstuecksTag.Samstag);
  }

  public int getFruehstueckSonntag() {
    return this.anzahlFruehstueckAm(FruehstuecksTag.Sonntag);
  }

  public void initForBesuche(Adressen adressen) {
    this.adressen = adressen;
    this.update();
  }

  public void update() {
    this.firePropertyChange(Summaries.ANZAHL_MELDUNGEN, -1, this.getAnzahlMeldungen());
    this.firePropertyChange(Summaries.FRUEHSTUCK_SAMSTAG, -1, this.getFruehstueckSamstag());
    this.firePropertyChange(Summaries.FRUEHSTUCK_SONNTAG, -1, this.getFruehstueckSonntag());
  }

}
