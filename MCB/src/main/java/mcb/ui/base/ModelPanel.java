package mcb.ui.base;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import mcb.model.McbException;
import mcb.persistenz.PersistenceStore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.beans.Model;

public abstract class ModelPanel<T extends Model> extends JPanel {

  private static final long serialVersionUID = -7811326380823888222L;
  protected BearbeitenAction<T> bearbeitenAction;
  protected PresentationModel<T> presentationModel;
  protected static final Logger LOGGER = LogManager.getLogger();
  protected final PersistenceStore persistenceStore;

  public ModelPanel(PresentationModel<T> presentationModel, BearbeitenAction<T> bearbeitenAction, PersistenceStore persistenceStore) {
    super();
    this.presentationModel = presentationModel;
    this.bearbeitenAction = bearbeitenAction;
    this.persistenceStore = persistenceStore;
  }

  public void handleMcbException(McbException e) {
    this.showError(e.getMessage());
    ModelPanel.LOGGER.error(e.getMessage(), e.getCause());
  }

  private void showError(String message) {
    JOptionPane.showMessageDialog(this, message, "Fehler", JOptionPane.ERROR_MESSAGE);
  }

}
