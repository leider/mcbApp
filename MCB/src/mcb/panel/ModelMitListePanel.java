package mcb.panel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.beans.Model;

public abstract class ModelMitListePanel<T extends Model> extends JPanel {

	private ModelPanel<T> modelPanel;
	private SelectionInListPanel<T> listePanel;
	private PresentationModel<T> detailModel;
	private McbAction neuAction;
	private McbAction loeschenAction;
	private BearbeitenAction<T> bearbeitenAction;

	private static final long serialVersionUID = -8126517029418193902L;

	public ModelMitListePanel() {
		super();
		initialize();
	}

	protected void abbrechen() {
		detailModel.triggerFlush();
		switchEnabledForPanels(true);
	}

	/**
	 * @return if the action has been performed
	 */
	protected boolean bearbeiten() {
		if (listePanel.hasSelection()) {
			switchEnabledForPanels(false);
			return true;
		}
		return false;
	}

	protected void createCommonActions() {
		neuAction = new McbAction("Neu", McbAction.NEU) {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				neu();
			}

		};

		bearbeitenAction = new BearbeitenAction<T>(this);

		loeschenAction = new McbAction("Löschen", McbAction.LOESCHEN) {

			private static final long serialVersionUID = -769620071952192523L;

			public void actionPerformed(ActionEvent e) {
				loeschen();
			}

		};

	}

	protected abstract SelectionInListPanel<T> createListePanel();

	protected abstract ModelPanel<T> createModelPanel(PresentationModel<T> model, BearbeitenAction<T> action);

	private JPanel createPanel() {
		detailModel = listePanel.getDetailModel();
		modelPanel = createModelPanel(detailModel, bearbeitenAction);
		return modelPanel;
	}

	private void createSplitPane() {
		JSplitPane splitPane = new JSplitPane();
		add(splitPane, BorderLayout.CENTER);
		splitPane.setLeftComponent(listePanel);
		splitPane.setRightComponent(createPanel());
	}

	protected void createToolBar() {
	}

	public McbAction getBearbeitenAction() {
		return bearbeitenAction;
	}

	public McbAction getLoeschenAction() {
		return loeschenAction;
	}

	public McbAction getNeuAction() {
		return neuAction;
	}

	private void initialize() {
		setLayout(new BorderLayout());
		listePanel = createListePanel();
		createCommonActions();
		createSplitPane();
		createToolBar();
	}

	protected void loeschen() {
		if (listePanel.hasSelection()) {
			int showConfirmDialog = JOptionPane.showConfirmDialog(this, loeschenMessage(detailModel.getBean()),
					"TERMINIEREN", JOptionPane.YES_NO_OPTION);
			if (showConfirmDialog == JOptionPane.YES_OPTION) {
				listePanel.loescheSelection();
			}
		}
	}

	protected abstract String loeschenMessage(T model);

	protected void neu() {
		listePanel.createNewAndAdd();
		bearbeitenAction.actionPerformed(null);
	}

	protected abstract void speichereModel(T model);

	protected void speichern() {
		detailModel.triggerCommit();
		speichereModel(detailModel.getBean());
		switchEnabledForPanels(true);
		updateListe();
	}

	private void switchEnabledForPanels(boolean listEnabled) {
		listePanel.setListEnabled(listEnabled);
		modelPanel.setEnabled(!listEnabled);
		neuAction.setEnabled(listEnabled);
	}

	public void updateListe() {
		listePanel.updateModelliste();
	}

}
