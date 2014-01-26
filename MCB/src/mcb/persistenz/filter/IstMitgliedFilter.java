package mcb.persistenz.filter;

import mcb.model.Adresse;
import mcb.panel.McbAction;

public class IstMitgliedFilter implements AdresseFilter {

	public int getKeyMask() {
		return McbAction.MITGLIED;
	}

	public String getLabel() {
		return "Nur Mitglieder";
	}

	public boolean matches(Adresse adresse) {
		return adresse.isMitglied();
	}

}