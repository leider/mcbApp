package mcb.persistenz.filter;

import mcb.model.Adresse;
import mcb.panel.McbAction;

public class AlleFilter implements AdresseFilter {

	private MatchesAlleListener listener;

	public int getKeyMask() {
		return McbAction.ALLE;
	}

	public String getLabel() {
		return "Alle";
	}

	public boolean matches(Adresse adresse) {
		if (this.listener != null) {
			this.listener.matchesAllePerformed();
		}
		return true;
	}

	public void setMatchesListener(MatchesAlleListener listener) {
		this.listener = listener;
	}

}
