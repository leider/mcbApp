package mcb.persistenz.filter.adressen;

import mcb.model.Adresse;
import mcb.model.Treffen;

public class EinladungPostFilter extends AbstractEinladungsFilter {

  public EinladungPostFilter(Treffen neuestesTreffen) {
    super(neuestesTreffen);
  }

  public String getLabel() {
    return "Einladungen Post";
  }

  public boolean matches(Adresse adresse) {
    return adresse.sollEinladungErhalten(this.neuestesTreffen) && !adresse.hatGueltigeEmail();
  }
}
