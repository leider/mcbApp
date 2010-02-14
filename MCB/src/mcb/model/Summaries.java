package mcb.model;


public class Summaries extends McbModel {

	private static final long serialVersionUID = 2313140187842313961L;

	public static final String FRUEHSTUCK_SAMSTAG = "fruehstueckSamstag";
	public static final String FRUEHSTUCK_SONNTAG = "fruehstueckSonntag";
	public static final String ANZAHL_MELDUNGEN = "anzahlMeldungen";

	private int fruehstueckSamstag;
	private int fruehstueckSonntag;
	private int anzahlMeldungen;

	public Summaries() {
		super();
	}

	public int getAnzahlMeldungen() {
		return this.anzahlMeldungen;
	}

	public int getFruehstueckSamstag() {
		return this.fruehstueckSamstag;
	}

	public int getFruehstueckSonntag() {
		return this.fruehstueckSonntag;
	}

	public void setAnzahlMeldungen(int anzMeldungen) {
		int oldValue = this.getAnzahlMeldungen();
		this.anzahlMeldungen = anzMeldungen;
		this.firePropertyChange(Summaries.ANZAHL_MELDUNGEN, oldValue, anzMeldungen);
	}

	public void setFruehstueckSamstag(int fruehstuckSamstag) {
		int oldValue = this.getFruehstueckSamstag();
		this.fruehstueckSamstag = fruehstuckSamstag;
		this.firePropertyChange(Summaries.FRUEHSTUCK_SAMSTAG, oldValue, fruehstuckSamstag);
	}

	public void setFruehstueckSonntag(int fruehstuckSonntag) {
		int oldValue = this.getFruehstueckSonntag();
		this.fruehstueckSonntag = fruehstuckSonntag;
		this.firePropertyChange(Summaries.FRUEHSTUCK_SONNTAG, oldValue, fruehstuckSonntag);
	}

}
