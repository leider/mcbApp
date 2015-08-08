package mcb.persistenz.filter.adressen;

import mcb.model.Treffen;
import mcb.persistenz.PersistenceStore;
import mcb.persistenz.filter.AdresseFilter;

public abstract class AbstractEinladungsFilter implements AdresseFilter {

  private final PersistenceStore persistenceStore;

  public AbstractEinladungsFilter(PersistenceStore persistenceStore) {
    super();
    this.persistenceStore = persistenceStore;
  }

  public int getKeyMask() {
    return 0;
  }

  protected Treffen getNeuestesTreffen() {
    return this.persistenceStore.getTreffens().getNeuestesTreffen();
  }

}
