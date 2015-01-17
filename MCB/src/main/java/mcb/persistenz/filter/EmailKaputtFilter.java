package mcb.persistenz.filter;

import mcb.model.Adresse;
import mcb.panel.McbAction;

public class EmailKaputtFilter implements AdresseFilter {

  public int getKeyMask() {
    return McbAction.EMAILKAPUTT;
  }

  public String getLabel() {
    return "Email nicht korrekt";
  }

  public boolean matches(Adresse adresse) {
    return adresse.isEmailfehler();
  }

}
