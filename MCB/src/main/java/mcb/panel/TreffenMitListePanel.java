package mcb.panel;

import mcb.model.Treffen;
import mcb.persistenz.McbException;
import mcb.persistenz.PersistenceStore;

import com.jgoodies.binding.PresentationModel;

public class TreffenMitListePanel extends ModelMitListePanel<Treffen> {

	private static final long serialVersionUID = 444596326461558352L;

	public TreffenMitListePanel(PersistenceStore persistenceStore) {
		super(persistenceStore);
	}

	@Override
	protected SelectionInListPanel<Treffen> createListePanel() {
		return new TreffenSelectionInListPanel(this.persistenceStore);
	}

	@Override
	protected ModelPanel<Treffen> createModelPanel(PresentationModel<Treffen> model, BearbeitenAction<Treffen> action) {
		return new TreffenPanel(model, action, this.persistenceStore);
	}

	@Override
	protected String loeschenMessage(Treffen model) {
		return "Willst Du das Treffen \"" + model.toString() + "\" wirklich terminieren?";
	}

	@Override
	protected void speichereModel(Treffen model) throws McbException {
		this.persistenceStore.saveAll();
	}

}
