package mcb.persistenz.filter.adressen;

import mcb.model.Adresse;
import mcb.model.Treffen;

public class KeineEinladungFilter extends AbstractEinladungsFilter {

  public KeineEinladungFilter(Treffen neuestesTreffen) {
    super(neuestesTreffen);
  }

  public String getLabel() {
    return "Keine Einladungen";
  }

  public boolean matches(Adresse adresse) {
    return !adresse.sollEinladungErhalten(this.neuestesTreffen);
  }
}
