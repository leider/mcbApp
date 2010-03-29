package mcb.model;

import java.util.List;

import mcb.persistenz.ApplicationData;

public class Summaries extends McbModel {

	private static final long serialVersionUID = 2313140187842313961L;

	public static final String FRUEHSTUCK_SAMSTAG = "fruehstueckSamstag";
	public static final String FRUEHSTUCK_SONNTAG = "fruehstueckSonntag";
	public static final String ANZAHL_MELDUNGEN = "anzahlMeldungen";

	private List<Besuch> aktuelleBesuche;

	public Summaries() {
		super();
	}

	private void firePropertyChanges() {
		this.firePropertyChange(Summaries.ANZAHL_MELDUNGEN, -1, this.getAnzahlMeldungen());
		this.firePropertyChange(Summaries.FRUEHSTUCK_SAMSTAG, -1, this.getFruehstueckSamstag());
		this.firePropertyChange(Summaries.FRUEHSTUCK_SONNTAG, -1, this.getFruehstueckSonntag());
	}

	public int getAnzahlMeldungen() {
		return this.aktuelleBesuche.size();
	}

	private int getFruehstueck(FruehstuecksTag tag) {
		int anzahl = 0;
		for (Besuch besuch : this.aktuelleBesuche) {
			anzahl = anzahl + besuch.getFruehstueckFuer(tag);
		}
		return anzahl;
	}

	public int getFruehstueckSamstag() {
		return this.getFruehstueck(FruehstuecksTag.Samstag);
	}

	public int getFruehstueckSonntag() {
		return this.getFruehstueck(FruehstuecksTag.Sonntag);
	}

	public void initForBesuche() {
		this.aktuelleBesuche = ApplicationData.getAktuelleBesuche();
		this.firePropertyChanges();
	}

}
