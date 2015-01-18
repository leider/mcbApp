package mcb.persistenz.filter.adressen;

import mcb.model.Adresse;
import mcb.persistenz.filter.AdresseFilter;

public class IstMitgliedFilter implements AdresseFilter {

  public int getKeyMask() {
    return FilterAction.MITGLIED;
  }

  public String getLabel() {
    return "Nur Mitglieder";
  }

  public boolean matches(Adresse adresse) {
    return adresse.isMitglied();
  }

}