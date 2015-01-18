package mcb.model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import flexjson.JSON;

public class Adresse extends McbModel {

  private static final long serialVersionUID = -6192828255610806898L;

  public static final String NAME = "name";
  public static final String VORNAME = "vorname";
  public static final String ORT = "ort";
  public static final String STRASSE = "strasse";
  public static final String PLZ = "plz";
  public static final String LAND = "land";
  public static final String GEBURTSTAG = "geburtstagString";
  public static final String GESPANN = "gespann";
  public static final String SOLO = "solo";
  public static final String EMAIL = "email";
  public static final String FEHLERGRUND = "fehlergrund";
  public static final String MCB_MITGLIED = "mitglied";

  public static final String VERGANGENE_TREFFEN = "vergangeneTreffen";

  private String name;
  private String vorname;
  private String strasse;
  private String ort;
  private String plz;
  private String land = "D";
  private Date geburtstag;
  private boolean gespann;
  private boolean solo;
  private String email;
  private String fehlergrund;
  private boolean mitglied;
  private List<Besuch> besuchteTreffen = new ArrayList<Besuch>();

  public Adresse() {
    super();
  }

  public void addTreffen(Treffen treffen) {
    if (treffen == null) {
      return;
    }
    for (Besuch besuch : this.besuchteTreffen) {
      if (besuch.getTreffen().equals(treffen)) {
        return;
      }
    }
    if (!this.besuchteTreffen.contains(treffen)) {
      this.besuchteTreffen.add(new Besuch(this, treffen));
    }
  }

  @JSON(include = false)
  public Besuch getAktuellerBesuch() {
    for (Besuch besuch : this.getBesuchteTreffen()) {
      if (besuch.getTreffen().isAktuell()) {
        return besuch;
      }
    }
    return null;
  }

  public List<Besuch> getBesuchteTreffen() {
    return this.besuchteTreffen;
  }

  public String getEmail() {
    return this.email;
  }

  public String getFehlergrund() {
    return this.fehlergrund;
  }

  public Date getGeburtstag() {
    return this.geburtstag;
  }

  @JSON(include = false)
  public String getGeburtstagString() {
    if (this.geburtstag != null) {
      return DateFormatter.formatDate(this.geburtstag);
    }
    return "";
  }

  public String getLand() {
    return this.land;
  }

  @JSON(include = false)
  public String getLandAusgeschrieben() {
    return Land.landFuerKuerzel(this.land);
  }

  public String getName() {
    return this.name;
  }

  public String getOrt() {
    return this.ort;
  }

  public String getPlz() {
    return this.plz;
  }

  public String getStrasse() {
    return this.strasse;
  }

  @JSON(include = false)
  public List<Besuch> getVergangeneTreffen() {
    List<Besuch> result = new ArrayList<Besuch>();
    for (Besuch besuch : this.getBesuchteTreffen()) {
      if (!besuch.getTreffen().isAktuell()) {
        result.add(besuch);
      }
    }
    Collections.sort(result);
    return result;
  }

  public String getVorname() {
    return this.vorname;
  }

  public boolean hatGueltigeEmail() {
    return this.email != null && this.email.trim().length() > 0 && !this.isEmailfehler();
  }

  @JSON(include = false)
  public boolean isEmailfehler() {
    return this.getFehlergrund() != null && !this.getFehlergrund().equals("");
  }

  public boolean isGespann() {
    return this.gespann;
  }

  public boolean isMitglied() {
    return this.mitglied;
  }

  public boolean isSolo() {
    return this.solo;
  }

  public void removeAktuellesTreffen() {
    this.besuchteTreffen.remove(this.getAktuellerBesuch());
  }

  public void setBesuchteTreffen(List<Besuch> besuchteTreffen) {
    this.besuchteTreffen = besuchteTreffen;
  }

  public void setEmail(String email) {
    String oldValue = this.getEmail();
    this.email = email != null ? email.trim() : null;
    this.firePropertyChange(Adresse.EMAIL, oldValue, this.email);
  }

  public void setFehlergrund(String fehlergrund) {
    this.fehlergrund = fehlergrund;
  }

  public void setGeburtstag(Date geburtstag) {
    this.geburtstag = geburtstag;
  }

  public void setGeburtstagString(String geburtstag) {
    try {
      String oldValue = this.getGeburtstagString();
      this.geburtstag = DateFormatter.parseDate(geburtstag);
      this.firePropertyChange(Adresse.GEBURTSTAG, oldValue, this.getGeburtstagString());
    } catch (ParseException e) {
      this.firePropertyChange(Adresse.GEBURTSTAG, null, this.getGeburtstagString());
    }
  }

  public void setGespann(boolean gespann) {
    boolean oldValue = this.isGespann();
    this.gespann = gespann;
    this.firePropertyChange(Adresse.GESPANN, oldValue, this.gespann);
  }

  public void setLand(String land) {
    String oldValue = this.getLand();
    this.land = land;
    this.firePropertyChange(Adresse.LAND, oldValue, this.land);
  }

  public void setMitglied(boolean mitglied) {
    boolean oldValue = this.isMitglied();
    this.mitglied = mitglied;
    this.firePropertyChange(Adresse.MCB_MITGLIED, oldValue, this.mitglied);
  }

  public void setName(String name) {
    String oldValue = this.getName();
    this.name = name;
    this.firePropertyChange(Adresse.NAME, oldValue, this.name);
  }

  public void setOrt(String ort) {
    String oldValue = this.getOrt();
    this.ort = ort;
    this.firePropertyChange(Adresse.ORT, oldValue, this.ort);
  }

  public void setPlz(String plz) {
    String oldValue = this.getPlz();
    this.plz = plz;
    this.firePropertyChange(Adresse.PLZ, oldValue, this.plz);
  }

  public void setSolo(boolean solo) {
    boolean oldValue = this.isSolo();
    this.solo = solo;
    this.firePropertyChange(Adresse.SOLO, oldValue, this.solo);
  }

  public void setStrasse(String strasse) {
    String oldValue = this.getStrasse();
    this.strasse = strasse;
    this.firePropertyChange(Adresse.STRASSE, oldValue, this.strasse);
  }

  public void setVorname(String vorname) {
    String oldValue = this.getVorname();
    this.vorname = vorname;
    this.firePropertyChange(Adresse.VORNAME, oldValue, this.vorname);
  }

  public boolean sollEinladungErhalten(Treffen neuestesTreffen) {
    if (neuestesTreffen.isGespann() && !this.isGespann()) {
      return false;
    }
    for (Besuch besuch : this.getBesuchteTreffen()) {
      if (neuestesTreffen.getJahr() - besuch.getTreffen().getJahr() < 5) {
        return true;
      }
    }
    return false;
  }

  @Override
  public String toString() {
    return (this.vorname != null ? this.vorname : "") + " " + (this.name != null ? this.name : "");
  }
}
