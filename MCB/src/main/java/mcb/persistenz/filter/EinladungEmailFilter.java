package mcb.persistenz.filter;

import mcb.model.Adresse;
import mcb.model.Treffen;
import mcb.panel.McbAction;

public class EinladungEmailFilter extends AbstractEinladungsFilter {

  public EinladungEmailFilter(Treffen neuestesTreffen) {
    super(neuestesTreffen);
  }

  public int getKeyMask() {
    return McbAction.EINLADUNGEN_EMAIL;
  }

  public String getLabel() {
    return "Einladungen Email";
  }

  public boolean matches(Adresse adresse) {
    return adresse.sollEinladungErhalten(this.neuestesTreffen) && adresse.hatGueltigeEmail();
  }
}
