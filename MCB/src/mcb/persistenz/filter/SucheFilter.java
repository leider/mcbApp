package mcb.persistenz.filter;

import mcb.panel.McbAction;
import mcb.persistenz.Adresse;

public class SucheFilter implements AdresseFilter {

	private String sucheText = "";
	private MatchesSucheListener listener;

	public int getKeyMask() {
		return McbAction.SUCHE;
	}

	public String getLabel() {
		return "Suche";
	}

	public boolean matches(Adresse adresse) {
		if (this.listener != null) {
			this.listener.matchesSuchePerformed();
		}
		return this.matches(adresse.getName()) || this.matches(adresse.getVorname());
	}

	private boolean matches(String string) {
		return string.toLowerCase().contains(this.sucheText.toLowerCase());
	}

	public void setMatchesListener(MatchesSucheListener listener) {
		this.listener = listener;
	}

	public void setSucheText(String sucheText) {
		this.sucheText = sucheText;
	}

}
