package mcb.persistenz.filter;

import mcb.panel.McbAction;
import mcb.persistenz.Adresse;

public class NichtGemeldeteFilter implements AdresseFilter {

	public int getKeyMask() {
		return McbAction.NICHTGEMELDET;
	}

	public String getLabel() {
		return "Nicht Gemeldete";
	}

	public boolean matches(Adresse adresse) {
		return adresse.getAktuellesTreffen() == null;
	}

}
