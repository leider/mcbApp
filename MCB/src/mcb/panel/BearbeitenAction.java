package mcb.panel;

import java.awt.event.ActionEvent;

import com.jgoodies.binding.beans.Model;

public class BearbeitenAction<T extends Model> extends McbAction {

	private static final long serialVersionUID = 5891819050902136782L;

	private boolean isSpeichern;

	private ModelMitListePanel<T> panel;

	public BearbeitenAction(ModelMitListePanel<T> owner) {
		super("Bearbeiten", McbAction.BEARBEITEN);
		panel = owner;
		updateName();
	}

	public void actionPerformed(ActionEvent e) {
		if (!isSpeichern) {
			if (panel.bearbeiten()) {
				switchMode();
			}
		} else {
			panel.speichern();
			switchMode();
		}
	}

	private void switchMode() {
		isSpeichern = !isSpeichern;
		updateName();
	}

	private void updateName() {
		putValue(NAME, (isSpeichern ? "Speichern" : "Bearbeiten"));
	}
}
