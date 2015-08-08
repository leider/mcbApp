package mcb.persistenz.filter.adressen;

import mcb.model.Adresse;
import mcb.persistenz.PersistenceStore;

public class EinladungEmailFilter extends AbstractEinladungsFilter {

  public EinladungEmailFilter(PersistenceStore persistenceStore) {
    super(persistenceStore);
  }

  public String getLabel() {
    return "Einladungen Email";
  }

  public boolean matches(Adresse adresse) {
    return adresse.sollEinladungErhalten(this.getNeuestesTreffen()) && adresse.hatGueltigeEmail();
  }
}
