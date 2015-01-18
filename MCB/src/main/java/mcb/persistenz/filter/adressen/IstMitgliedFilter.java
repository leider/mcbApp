package mcb.persistenz.filter.adressen;

import mcb.model.Adresse;
import mcb.persistenz.filter.AdresseFilter;

public class IstMitgliedFilter implements AdresseFilter {

  public int getKeyMask() {
    return 0;
  }

  public String getLabel() {
    return "Nur Mitglieder";
  }

  public boolean matches(Adresse adresse) {
    return adresse.isMitglied();
  }

}