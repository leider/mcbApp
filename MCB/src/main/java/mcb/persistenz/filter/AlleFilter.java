package mcb.persistenz.filter;

import mcb.model.Adresse;
import mcb.panel.McbAction;

public class AlleFilter implements AdresseFilter {

  private static final AlleFilter instance = new AlleFilter();

  public static AlleFilter getInstance() {
    return AlleFilter.instance;
  }

  private MatchesAlleListener listener;

  private AlleFilter() {
    super();
  }

  public int getKeyMask() {
    return McbAction.ALLE;
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
