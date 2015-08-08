package mcb.persistenz.filter.adressen;

import mcb.model.Adresse;
import mcb.persistenz.PersistenceStore;

public class EinladungPostFilter extends AbstractEinladungsFilter {

  public EinladungPostFilter(PersistenceStore persistenceStore) {
    super(persistenceStore);
  }

  public String getLabel() {
    return "Einladungen Post";
  }

  public boolean matches(Adresse adresse) {
    return adresse.sollEinladungErhalten(this.getNeuestesTreffen()) && !adresse.hatGueltigeEmail();
  }
}
