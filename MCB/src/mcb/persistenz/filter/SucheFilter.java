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
		if (listener != null) {
			listener.matchesSuchePerformed();
		}
		return matches(adresse.getName()) || matches(adresse.getVorname());
	}

	private boolean matches(String string) {
		return string.toLowerCase().contains(sucheText.toLowerCase());
	}

	public void setMatchesListener(MatchesSucheListener listener) {
		this.listener = listener;
	}

	public void setSucheText(String sucheText) {
		this.sucheText = sucheText;
	}

}
