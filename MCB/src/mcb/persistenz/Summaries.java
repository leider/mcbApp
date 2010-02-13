package mcb.persistenz;

import com.jgoodies.binding.beans.Model;

public class Summaries extends Model {

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
		return anzahlMeldungen;
	}

	public int getFruehstueckSamstag() {
		return fruehstueckSamstag;
	}

	public int getFruehstueckSonntag() {
		return fruehstueckSonntag;
	}

	public void setAnzahlMeldungen(int anzMeldungen) {
		int oldValue = getAnzahlMeldungen();
		anzahlMeldungen = anzMeldungen;
		firePropertyChange(ANZAHL_MELDUNGEN, oldValue, anzMeldungen);
	}

	public void setFruehstueckSamstag(int fruehstuckSamstag) {
		int oldValue = getFruehstueckSamstag();
		fruehstueckSamstag = fruehstuckSamstag;
		firePropertyChange(FRUEHSTUCK_SAMSTAG, oldValue, fruehstuckSamstag);
	}

	public void setFruehstueckSonntag(int fruehstuckSonntag) {
		int oldValue = getFruehstueckSonntag();
		fruehstueckSonntag = fruehstuckSonntag;
		firePropertyChange(FRUEHSTUCK_SONNTAG, oldValue, fruehstuckSonntag);
	}

}
