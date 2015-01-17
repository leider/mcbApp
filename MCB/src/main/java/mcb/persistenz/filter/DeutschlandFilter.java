package mcb.persistenz.filter;

import mcb.model.Adresse;
import mcb.panel.McbAction;

public class DeutschlandFilter implements AdresseFilter {

	public int getKeyMask() {
		return McbAction.DEUTSCHLAND;
	}

	public String getLabel() {
		return "Nur Deutsche";
	}

	public boolean matches(Adresse adresse) {
		return adresse.getLand().equals("D");
	}

}
