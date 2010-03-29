package mcb.panel;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import mcb.persistenz.McbException;

import org.apache.log4j.Logger;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.beans.Model;

public abstract class ModelPanel<T extends Model> extends JPanel {

	private static final long serialVersionUID = -7811326380823888222L;
	protected BearbeitenAction<T> bearbeitenAction;
	protected PresentationModel<T> presentationModel;
	protected static final Logger LOGGER = Logger.getLogger(ModelPanel.class.getName());

	public ModelPanel(PresentationModel<T> presentationModel, BearbeitenAction<T> bearbeitenAction) {
		super();
		this.presentationModel = presentationModel;
		this.bearbeitenAction = bearbeitenAction;
	}

	public void handleMcbException(McbException e) {
		this.showError(e.getMessage());
		ModelPanel.LOGGER.error(e.getMessage(), e.getCause());
	}

	private void showError(String message) {
		JOptionPane.showMessageDialog(this, message, "Fehler", JOptionPane.ERROR_MESSAGE);
	}

}
