package mcb.persistenz.filter;

import mcb.model.Adresse;
import mcb.panel.McbAction;

public class NichtGemeldeteFilter implements AdresseFilter {

	public int getKeyMask() {
		return McbAction.NICHTGEMELDET;
	}

	public String getLabel() {
		return "Nicht Gemeldete";
	}

	public boolean matches(Adresse adresse) {
		return adresse.getAktuellerBesuch() == null;
	}

}
