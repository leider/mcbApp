package mcb.panel;

import java.util.List;

import mcb.model.Adresse;
import mcb.persistenz.ApplicationData;
import mcb.persistenz.McbException;

public class AdressenSelectionInListPanel extends SelectionInListPanel<Adresse> {

	private static final long serialVersionUID = -3850904257220547857L;

	public AdressenSelectionInListPanel() {
		super();
	}

	@Override
	protected Adresse createNewModel() throws McbException {
		Adresse neu = new Adresse();
		neu.setVorname("");
		neu.setName("");
		ApplicationData.saveAdresse(neu);
		return neu;
	}

	@Override
	protected List<Adresse> getContents() {
		return ApplicationData.getAlleAdressen();
	}

	@Override
	protected void loescheObjekt(Adresse adresse) throws McbException {
		ApplicationData.loescheModel(adresse);
	}
}
