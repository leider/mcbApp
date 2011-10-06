package mcb.panel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import mcb.frame.actions.FilterAction;
import mcb.persistenz.McbException;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.beans.Model;

public abstract class ModelMitListePanel<T extends Model> extends JPanel {

	private ModelPanel<T> modelPanel;
	private SelectionInListPanel<T> listePanel;
	private PresentationModel<T> detailModel;
	private McbAction neuAction;
	private McbAction loeschenAction;
	private BearbeitenAction<T> bearbeitenAction;
	private List<FilterAction<T>> filterActions;

	private static final long serialVersionUID = -8126517029418193902L;

	public ModelMitListePanel() {
		super();
		this.initialize();
	}

	protected void abbrechen() {
		this.detailModel.triggerFlush();
		this.switchEnabledForPanels(true);
	}

	public void addFilterAction(FilterAction<T> filterAction) {
		this.filterActions.add(filterAction);
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

	protected List<McbAction> getAllActionsExceptBearbeiten() {
		List<McbAction> result = new ArrayList<McbAction>();
		result.add(this.loeschenAction);
		result.add(this.neuAction);
		result.addAll(this.filterActions);
		return result;
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

	void handleMcbException(McbException e) {
		this.showError(e.getMessage());
		ModelPanel.LOGGER.error(e.getMessage(), e.getCause());
	}

	private void initialize() {
		this.filterActions = new ArrayList<FilterAction<T>>();
		this.setLayout(new BorderLayout());
		this.listePanel = this.createListePanel();
		this.createCommonActions();
		this.createSplitPane();
		this.createToolBar();
	}

	protected void loeschen() {
		if (this.listePanel.hasSelection()) {
			int showConfirmDialog = JOptionPane.showConfirmDialog(this, this.loeschenMessage(this.detailModel.getBean()), "TERMINIEREN",
					JOptionPane.YES_NO_OPTION);
			if (showConfirmDialog == JOptionPane.YES_OPTION) {
				try {
					this.listePanel.loescheSelection();
				} catch (McbException e) {
					this.handleMcbException(e);
				}
			}
		}
	}

	protected abstract String loeschenMessage(T model);

	protected void neu() {
		try {
			this.listePanel.createNewAndAdd();
			this.bearbeitenAction.actionPerformed(null);
		} catch (McbException e) {
			this.handleMcbException(e);
		}
	}

	private void showError(String message) {
		JOptionPane.showMessageDialog(this, message, "Fehler", JOptionPane.ERROR_MESSAGE);
	}

	protected abstract void speichereModel(T model) throws McbException;

	protected void speichern() throws McbException {
		this.detailModel.triggerCommit();
		this.speichereModel(this.detailModel.getBean());
		this.switchEnabledForPanels(true);
		this.updateListe();
	}

	protected void switchEnabledForPanels(boolean listEnabled) {
		this.listePanel.setListEnabled(listEnabled);
		this.modelPanel.setEnabled(!listEnabled);
		for (McbAction mcbAction : this.getAllActionsExceptBearbeiten()) {
			mcbAction.setEnabled(listEnabled);
		}
	}

	public void updateListe() {
		this.listePanel.updateModelliste();
	}

}
