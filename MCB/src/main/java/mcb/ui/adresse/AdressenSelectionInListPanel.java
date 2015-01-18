package mcb.ui.adresse;

import java.util.List;

import mcb.model.Adresse;
import mcb.model.McbException;
import mcb.persistenz.PersistenceStore;
import mcb.ui.base.SelectionInListPanel;

public class AdressenSelectionInListPanel extends SelectionInListPanel<Adresse> {

  private static final long serialVersionUID = -3850904257220547857L;

  public AdressenSelectionInListPanel(PersistenceStore persistenceStore) {
    super(persistenceStore);
  }

  @Override
  protected Adresse createNewModel() throws McbException {
    Adresse neu = new Adresse();
    neu.setVorname("");
    neu.setName("");
    this.persistenceStore.getAdressen().add(neu);
    return neu;
  }

  @Override
  protected List<Adresse> getContents() {
    return this.persistenceStore.getAdressen().getFilteredAdressen();
  }

  @Override
  protected void loescheObjekt(Adresse adresse) throws McbException {
    this.persistenceStore.getAdressen().remove(adresse);
    this.persistenceStore.saveAll();
  }
}
