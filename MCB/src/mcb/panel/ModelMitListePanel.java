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
		this.initialize();
	}

	protected void abbrechen() {
		this.detailModel.triggerFlush();
		this.switchEnabledForPanels(true);
	}

	/**
	 * @return if the action has been performed
	 */
	protected boolean bearbeiten() {
		if (this.listePanel.hasSelection()) {
			this.switchEnabledForPanels(false);
			return true;
		}
		return false;
	}

	protected void createCommonActions() {
		this.neuAction = new McbAction("Neu", McbAction.NEU) {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				ModelMitListePanel.this.neu();
			}

		};

		this.bearbeitenAction = new BearbeitenAction<T>(this);

		this.loeschenAction = new McbAction("Löschen", McbAction.LOESCHEN) {

			private static final long serialVersionUID = -769620071952192523L;

			public void actionPerformed(ActionEvent e) {
				ModelMitListePanel.this.loeschen();
			}

		};

	}

	protected abstract SelectionInListPanel<T> createListePanel();

	protected abstract ModelPanel<T> createModelPanel(PresentationModel<T> model, BearbeitenAction<T> action);

	private JPanel createPanel() {
		this.detailModel = this.listePanel.getDetailModel();
		this.modelPanel = this.createModelPanel(this.detailModel, this.bearbeitenAction);
		return this.modelPanel;
	}

	private void createSplitPane() {
		JSplitPane splitPane = new JSplitPane();
		splitPane.setLeftComponent(this.listePanel);
		splitPane.setRightComponent(this.createPanel());
		this.add(splitPane, BorderLayout.CENTER);
	}

	protected void createToolBar() {
		// don't needed
	}

	public McbAction getBearbeitenAction() {
		return this.bearbeitenAction;
	}

	public McbAction getLoeschenAction() {
		return this.loeschenAction;
	}

	public McbAction getNeuAction() {
		return this.neuAction;
	}

	private void initialize() {
		this.setLayout(new BorderLayout());
		this.listePanel = this.createListePanel();
		this.createCommonActions();
		this.createSplitPane();
		this.createToolBar();
	}

	protected void loeschen() {
		if (this.listePanel.hasSelection()) {
			int showConfirmDialog = JOptionPane.showConfirmDialog(this, this
					.loeschenMessage(this.detailModel.getBean()), "TERMINIEREN", JOptionPane.YES_NO_OPTION);
			if (showConfirmDialog == JOptionPane.YES_OPTION) {
				this.listePanel.loescheSelection();
			}
		}
	}

	protected abstract String loeschenMessage(T model);

	protected void neu() {
		this.listePanel.createNewAndAdd();
		this.bearbeitenAction.actionPerformed(null);
	}

	protected abstract void speichereModel(T model);

	protected void speichern() {
		this.detailModel.triggerCommit();
		this.speichereModel(this.detailModel.getBean());
		this.switchEnabledForPanels(true);
		this.updateListe();
	}

	private void switchEnabledForPanels(boolean listEnabled) {
		this.listePanel.setListEnabled(listEnabled);
		this.modelPanel.setEnabled(!listEnabled);
		this.neuAction.setEnabled(listEnabled);
	}

	public void updateListe() {
		this.listePanel.updateModelliste();
	}

}
