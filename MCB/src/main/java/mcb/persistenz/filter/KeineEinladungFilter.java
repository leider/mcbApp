package mcb.persistenz.filter;

import mcb.model.Adresse;
import mcb.model.Treffen;
import mcb.panel.McbAction;

public class KeineEinladungFilter extends AbstractEinladungsFilter {

  public KeineEinladungFilter(Treffen neuestesTreffen) {
    super(neuestesTreffen);
  }

  public int getKeyMask() {
    return McbAction.KEINE_EINLADUNG;
  }

  public String getLabel() {
    return "Keine Einladungen";
  }

  public boolean matches(Adresse adresse) {
    return !adresse.sollEinladungErhalten(this.neuestesTreffen);
  }
}
