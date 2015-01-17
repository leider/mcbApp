package mcb.persistenz.filter;

import mcb.model.Adresse;
import mcb.panel.McbAction;

public class SucheFilter implements AdresseFilter {

  private final static SucheFilter instance = new SucheFilter();

  public static SucheFilter getInstance() {
    return SucheFilter.instance;
  }

  private String sucheText = "";

  private MatchesSucheListener listener;

  private SucheFilter() {
    super();
  }

  public int getKeyMask() {
    return McbAction.SUCHE;
  }

  public String getLabel() {
    return "Suche";
  }

  public boolean matches(Adresse adresse) {
    if (this.listener != null) {
      this.listener.matchesSuchePerformed();
    }
    return this.matches(adresse.getName()) || this.matches(adresse.getVorname()) || this.matches(adresse.getEmail());
  }

  private boolean matches(String string) {
    if (string == null) {
      return false;
    }
    return string.toLowerCase().contains(this.sucheText.toLowerCase());
  }

  public void setMatchesListener(MatchesSucheListener listener) {
    this.listener = listener;
  }

  public void setSucheText(String sucheText) {
    this.sucheText = sucheText;
  }

}
