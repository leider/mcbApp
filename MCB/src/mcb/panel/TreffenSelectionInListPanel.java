package mcb.panel;

import java.util.List;

import mcb.model.Treffen;
import mcb.persistenz.ApplicationData;

public class TreffenSelectionInListPanel extends SelectionInListPanel<Treffen> {

	private static final long serialVersionUID = -3850904257220547857L;

	public TreffenSelectionInListPanel() {
		super();
	}

	@Override
	protected Treffen createNewModel() {
		Treffen neu = new Treffen();
		neu.setName("");
		ApplicationData.saveTreffen(neu);
		return neu;
	}

	@Override
	protected List<Treffen> getContents() {
		return ApplicationData.getAlleTreffen();
	}

	@Override
	protected void loescheObjekt(Treffen objekt) {
		ApplicationData.loescheModel(objekt);
	}

}
