package mcb.persistenz.filter.adressen;

import mcb.model.Adresse;
import mcb.model.Treffen;

public class EinladungEmailFilter extends AbstractEinladungsFilter {

  public EinladungEmailFilter(Treffen neuestesTreffen) {
    super(neuestesTreffen);
  }

  public String getLabel() {
    return "Einladungen Email";
  }

  public boolean matches(Adresse adresse) {
    return adresse.sollEinladungErhalten(this.neuestesTreffen) && adresse.hatGueltigeEmail();
  }
}
