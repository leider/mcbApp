package mcb.persistenz.filter;

import mcb.model.Adresse;

public class NichtGemeldeteFilter implements AdresseFilter {

  public int getKeyMask() {
    return McbAction.NICHTGEMELDET;
  }

  public String getLabel() {
    return "Nicht Gemeldete";
  }

  public boolean matches(Adresse adresse) {
    return adresse.getAktuellerBesuch() == null;
  }

}
