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

	public int getFruehstueckSamstag() {
		return this.fruehstueckSamstag;
	}

	public int getFruehstueckSonntag() {
		return this.fruehstueckSonntag;
	}

	public Treffen getTreffen() {
		return this.treffen;
	}

	public void setFruehstueckSamstag(int fruehstueckSamstag) {
		this.fruehstueckSamstag = fruehstueckSamstag;
	}

	public void setFruehstueckSonntag(int fruehstueckSonntag) {
		this.fruehstueckSonntag = fruehstueckSonntag;
	}

	@Override
	public String toString() {
		return this.getTreffen().toString();
	}
}
