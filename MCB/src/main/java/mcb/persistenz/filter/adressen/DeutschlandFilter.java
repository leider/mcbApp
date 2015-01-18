package mcb.persistenz.filter.adressen;

import mcb.model.Adresse;
import mcb.persistenz.filter.AdresseFilter;

public class DeutschlandFilter implements AdresseFilter {

  public int getKeyMask() {
    return FilterAction.DEUTSCHLAND;
  }

  public String getLabel() {
    return "Nur Deutsche";
  }

  public boolean matches(Adresse adresse) {
    return adresse.getLand().equals("D");
  }

}
