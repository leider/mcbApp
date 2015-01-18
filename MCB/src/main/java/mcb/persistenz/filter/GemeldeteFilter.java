package mcb.persistenz.filter;

import mcb.model.Adresse;

public class GemeldeteFilter implements AdresseFilter {

  public int getKeyMask() {
    return McbAction.GEMELDET;
  }

  public String getLabel() {
    return "Gemeldete";
  }

  public boolean matches(Adresse adresse) {
    return adresse.getAktuellerBesuch() != null;
  }

}
