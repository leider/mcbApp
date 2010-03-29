package mcb.model;

public class Besuch extends McbModel implements Comparable<Besuch> {

	private static final long serialVersionUID = -941602649174788569L;

	private Adresse adresse;

	private Treffen treffen;

	private int fruehstueckSamstag;

	private int fruehstueckSonntag;

	public Besuch() {
		super();
	}

	public Besuch(Adresse adresse, Treffen treffen) {
		super();
		this.adresse = adresse;
		this.treffen = treffen;
	}

	public int compareTo(Besuch o) {
		return this.getTreffen().compareTo(o.getTreffen());
	}

	public Adresse getAdresse() {
		return this.adresse;
	}

	public int getFruehstueckFuer(FruehstuecksTag tag) {
		return tag.getFruehstueck(this);
	}

	public int getFruehstueckSamstag() {
		return this.fruehstueckSamstag;
	}

	public int getFruehstueckSonntag() {
		return this.fruehstueckSonntag;
	}

	public Treffen getTreffen() {
		return this.treffen;
	}

	public void setFruehstueckFuer(int anzahl, FruehstuecksTag tag) {
		tag.setFruehstueck(this, anzahl);
	}

	protected void setFruehstueckSamstag(int anzahl) {
		this.fruehstueckSamstag = anzahl;
	}

	protected void setFruehstueckSonntag(int anzahl) {
		this.fruehstueckSonntag = anzahl;
	}

	@Override
	public String toString() {
		return this.getTreffen().toString();
	}
}
