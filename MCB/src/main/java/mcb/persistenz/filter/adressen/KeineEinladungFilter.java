package mcb.persistenz.filter.adressen;

import mcb.model.Adresse;
import mcb.persistenz.PersistenceStore;

public class KeineEinladungFilter extends AbstractEinladungsFilter {

  public KeineEinladungFilter(PersistenceStore persistenceStore) {
    super(persistenceStore);
  }

  public String getLabel() {
    return "Keine Einladungen";
  }

  public boolean matches(Adresse adresse) {
    return !adresse.sollEinladungErhalten(this.getNeuestesTreffen());
  }
}
