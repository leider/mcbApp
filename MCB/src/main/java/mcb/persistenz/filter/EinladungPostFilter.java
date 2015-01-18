package mcb.persistenz.filter;

import mcb.model.Adresse;
import mcb.model.Treffen;

public class EinladungPostFilter extends AbstractEinladungsFilter {

  public EinladungPostFilter(Treffen neuestesTreffen) {
    super(neuestesTreffen);
  }

  public int getKeyMask() {
    return McbAction.EINLADUNGEN_POST;
  }

  public String getLabel() {
    return "Einladungen Post";
  }

  public boolean matches(Adresse adresse) {
    return adresse.sollEinladungErhalten(this.neuestesTreffen) && !adresse.hatGueltigeEmail();
  }
}
