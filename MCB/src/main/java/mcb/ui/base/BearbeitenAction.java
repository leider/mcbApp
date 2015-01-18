package mcb.ui.base;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import mcb.model.McbException;
import mcb.persistenz.filter.McbAction;

import com.jgoodies.binding.beans.Model;

public class BearbeitenAction<T extends Model> extends McbAction {

  private static final long serialVersionUID = 5891819050902136782L;

  private boolean isSpeichern;

  private ModelMitListePanel<T> panel;

  public BearbeitenAction(ModelMitListePanel<T> owner) {
    super("Bearbeiten", McbAction.BEARBEITEN);
    this.panel = owner;
    this.updateName();
  }

  public void actionPerformed(ActionEvent e) {
    if (!this.isSpeichern) {
      if (this.panel.bearbeiten()) {
        this.switchMode();
      }
    } else {
      try {
        this.panel.speichern();
        this.switchMode();
      } catch (McbException mcbE) {
        this.panel.handleMcbException(mcbE);
      }
    }
  }

  private void switchMode() {
    this.isSpeichern = !this.isSpeichern;
    this.updateName();
  }

  private void updateName() {
    this.putValue(Action.NAME, (this.isSpeichern ? "Speichern" : "Bearbeiten"));
  }
}
