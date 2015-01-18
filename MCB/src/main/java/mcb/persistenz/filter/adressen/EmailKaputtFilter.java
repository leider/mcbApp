package mcb.persistenz.filter.adressen;

import mcb.model.Adresse;
import mcb.persistenz.filter.AdresseFilter;

public class EmailKaputtFilter implements AdresseFilter {

  public int getKeyMask() {
    return FilterAction.EMAILKAPUTT;
  }

  public String getLabel() {
    return "Email nicht korrekt";
  }

  public boolean matches(Adresse adresse) {
    return adresse.isEmailfehler();
  }

}
