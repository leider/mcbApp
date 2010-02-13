package mcb.persistenz;

import com.jgoodies.binding.beans.Model;

public class Besuch extends Model implements Comparable<Besuch> {

	private static final long serialVersionUID = -941602649174788569L;

	private Long id;

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
		return getTreffen().compareTo(o.getTreffen());
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public int getFruehstueckSamstag() {
		return fruehstueckSamstag;
	}

	public int getFruehstueckSonntag() {
		return fruehstueckSonntag;
	}

	public Long getId() {
		return id;
	}

	public Treffen getTreffen() {
		return treffen;
	}

	public void setFruehstueckSamstag(int fruehstueckSamstag) {
		this.fruehstueckSamstag = fruehstueckSamstag;
	}

	public void setFruehstueckSonntag(int fruehstueckSonntag) {
		this.fruehstueckSonntag = fruehstueckSonntag;
	}

	@Override
	public String toString() {
		return getTreffen().toString();
	}
}
