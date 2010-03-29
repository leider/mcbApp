package mcb.persistenz.filter;

import mcb.model.Adresse;
import mcb.panel.McbAction;

public class GemeldeteFilter implements AdresseFilter {

	public int getKeyMask() {
		return McbAction.GEMELDET;
	}

	public String getLabel() {
		return "Gemeldete";
	}

	public boolean matches(Adresse adresse) {
		return adresse.getAktuellenBesuch() != null;
	}

}
