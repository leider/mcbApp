package mcb.persistenz.filter;

import mcb.panel.McbAction;
import mcb.persistenz.Adresse;

public class EinladungEmailFilter implements AdresseFilter {

	public int getKeyMask() {
		return McbAction.EINLADUNGEN_EMAIL;
	}

	public String getLabel() {
		return "Einladungen Email";
	}

	public boolean matches(Adresse adresse) {
		return adresse.sollEinladungErhalten() && adresse.hatGueltigeEmail();
	}
}
