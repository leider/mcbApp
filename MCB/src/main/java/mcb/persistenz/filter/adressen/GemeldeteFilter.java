package mcb.persistenz.filter.adressen;

import mcb.model.Adresse;
import mcb.persistenz.filter.AdresseFilter;

public class GemeldeteFilter implements AdresseFilter {

  public int getKeyMask() {
    return FilterAction.GEMELDET;
  }

  public String getLabel() {
    return "Gemeldete";
  }

  public boolean matches(Adresse adresse) {
    return adresse.getAktuellerBesuch() != null;
  }

}
