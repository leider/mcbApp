package mcb.persistenz.filter;

import mcb.model.Treffen;

public abstract class AbstractEinladungsFilter implements AdresseFilter {

  protected final Treffen neuestesTreffen;

  public AbstractEinladungsFilter(Treffen neuestesTreffen) {
    super();
    this.neuestesTreffen = neuestesTreffen;
  }

}
