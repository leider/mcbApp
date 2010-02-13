package mcb.persistenz.filter;

import mcb.panel.McbAction;
import mcb.persistenz.Adresse;

public class AuslandFilter implements AdresseFilter {

	public int getKeyMask() {
		return McbAction.AUSLAND;
	}

	public String getLabel() {
		return "Nur Ausländische";
	}

	public boolean matches(Adresse adresse) {
		return !adresse.getLand().equals("D");
	}

}
