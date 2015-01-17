package mcb.model;

import java.util.List;

import mcb.persistenz.Adressen;

public class Summaries extends McbModel {

	private static final long serialVersionUID = 2313140187842313961L;

	public static final String FRUEHSTUCK_SAMSTAG = "fruehstueckSamstag";
	public static final String FRUEHSTUCK_SONNTAG = "fruehstueckSonntag";
	public static final String ANZAHL_MELDUNGEN = "anzahlMeldungen";

	private static final Summaries instance = new Summaries();

	public static Summaries getInstance() {
		return Summaries.instance;
	}

	private Adressen adressen;

	private Summaries() {
		super();
	}

	private void firePropertyChanges() {
		this.firePropertyChange(Summaries.ANZAHL_MELDUNGEN, -1, this.getAnzahlMeldungen());
		this.firePropertyChange(Summaries.FRUEHSTUCK_SAMSTAG, -1, this.getFruehstueckSamstag());
		this.firePropertyChange(Summaries.FRUEHSTUCK_SONNTAG, -1, this.getFruehstueckSonntag());
	}

	private List<Besuch> getAktuelleBesuche() {
		return this.adressen.getAktuelleBesuche();
	}

	public int getAnzahlMeldungen() {
		return this.getAktuelleBesuche().size();
	}

	private int getFruehstueck(FruehstuecksTag tag) {
		int anzahl = 0;
		for (Besuch besuch : this.getAktuelleBesuche()) {
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

	public void initForBesuche(Adressen adressen) {
		this.adressen = adressen;
		this.firePropertyChanges();
	}

	public void update() {
		this.firePropertyChanges();
	}

}
