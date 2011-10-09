package mcb.panel;

import java.util.List;

import mcb.model.Treffen;
import mcb.persistenz.ApplicationData;
import mcb.persistenz.McbException;
import mcb.persistenz.PersistenceStore;

public class TreffenSelectionInListPanel extends SelectionInListPanel<Treffen> {

	private static final long serialVersionUID = -3850904257220547857L;

	public TreffenSelectionInListPanel(PersistenceStore persistenceStore) {
		super(persistenceStore);
	}

	@Override
	protected Treffen createNewModel() throws McbException {
		Treffen neu = new Treffen();
		neu.setName("");
		ApplicationData.add(neu);
		return neu;
	}

	@Override
	protected List<Treffen> getContents() {
		return ApplicationData.getAlleTreffen();
	}

	@Override
	protected void loescheObjekt(Treffen objekt) throws McbException {
		this.persistenceStore.loescheModel(objekt);
	}

}
