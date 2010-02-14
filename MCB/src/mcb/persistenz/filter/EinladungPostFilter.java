package mcb.persistenz.filter;

import mcb.model.Adresse;
import mcb.panel.McbAction;

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
