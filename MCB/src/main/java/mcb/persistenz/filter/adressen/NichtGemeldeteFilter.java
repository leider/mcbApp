package mcb.persistenz.filter.adressen;

import mcb.model.Adresse;
import mcb.persistenz.filter.AdresseFilter;

public class NichtGemeldeteFilter implements AdresseFilter {

  public int getKeyMask() {
    return FilterAction.NICHTGEMELDET;
  }

  public String getLabel() {
    return "Nicht Gemeldete";
  }

  public boolean matches(Adresse adresse) {
    return adresse.getAktuellerBesuch() == null;
  }

}
