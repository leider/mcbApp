package mcb.persistenz.filter;

import mcb.panel.McbAction;
import mcb.persistenz.Adresse;

public class EinladungPostFilter implements AdresseFilter {

	public int getKeyMask() {
		return McbAction.EINLADUNGEN_POST;
	}

	public String getLabel() {
		return "Einladungen Post";
	}

	public boolean matches(Adresse adresse) {
		return adresse.sollEinladungErhalten() && !adresse.hatGueltigeEmail();
	}
}
