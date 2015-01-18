package mcb.persistenz.filter.adressen;

import mcb.model.Treffen;
import mcb.persistenz.filter.AdresseFilter;

public abstract class AbstractEinladungsFilter implements AdresseFilter {

  protected final Treffen neuestesTreffen;

  public AbstractEinladungsFilter(Treffen neuestesTreffen) {
    super();
    this.neuestesTreffen = neuestesTreffen;
  }

  public int getKeyMask() {
    return 0;
  }

}
