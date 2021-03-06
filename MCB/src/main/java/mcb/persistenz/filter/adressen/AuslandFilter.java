package mcb.persistenz.filter.adressen;

import mcb.model.Adresse;
import mcb.persistenz.filter.AdresseFilter;

public class AuslandFilter implements AdresseFilter {

  public int getKeyMask() {
    return FilterAction.AUSLAND;
  }

  public String getLabel() {
    return "Nur Auslšndische";
  }

  public boolean matches(Adresse adresse) {
    return !adresse.getLand().equals("D");
  }

}
