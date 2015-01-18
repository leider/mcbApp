package mcb.persistenz.filter;

import java.awt.event.KeyEvent;

import mcb.model.Adresse;

public class AlleFilter implements AdresseFilter {

  public static AlleFilter getInstance() {
    return AlleFilter.instance;
  }

  private static final AlleFilter instance = new AlleFilter();

  private MatchesAlleListener listener;

  private AlleFilter() {
    super();
  }

  public int getKeyMask() {
    return KeyEvent.VK_A;
  }

  public String getLabel() {
    return "Alle";
  }

  public boolean matches(Adresse adresse) {
    if (this.listener != null) {
      this.listener.matchesAllePerformed();
    }
    return true;
  }

  public void setMatchesListener(MatchesAlleListener listener) {
    this.listener = listener;
  }

}
