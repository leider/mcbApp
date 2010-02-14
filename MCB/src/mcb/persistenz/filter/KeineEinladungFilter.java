package mcb.persistenz.filter;

import mcb.model.Adresse;
import mcb.panel.McbAction;

public class KeineEinladungFilter implements AdresseFilter {

	public int getKeyMask() {
		return McbAction.KEINE_EINLADUNG;
	}

	public String getLabel() {
		return "Keine Einladungen";
	}

	public boolean matches(Adresse adresse) {
		return !adresse.sollEinladungErhalten();
	}
}
