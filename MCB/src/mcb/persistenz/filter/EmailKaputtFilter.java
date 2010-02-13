package mcb.persistenz.filter;

import mcb.panel.McbAction;
import mcb.persistenz.Adresse;

public class EmailKaputtFilter implements AdresseFilter {

	public int getKeyMask() {
		return McbAction.EMAILKAPUTT;
	}

	public String getLabel() {
		return "Email nicht korrekt";
	}

	public boolean matches(Adresse adresse) {
		return adresse.isEmailfehler();
	}

}
