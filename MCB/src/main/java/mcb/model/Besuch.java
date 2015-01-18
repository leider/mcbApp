package mcb.model;

public class Besuch implements Comparable<Besuch> {

  private McbModel adresse;

  private Treffen treffen;

  private int fruehstueckSamstag;

  private int fruehstueckSonntag;

  public Besuch() {
    super();
  }

  public Besuch(McbModel adresse, Treffen treffen) {
    super();
    this.adresse = adresse;
    this.treffen = treffen;
  }

  public int compareTo(Besuch o) {
    return this.getTreffen().compareTo(o.getTreffen());
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!super.equals(obj)) {
      return false;
    }
    if (this.getClass() != obj.getClass()) {
      return false;
    }
    Besuch other = (Besuch) obj;
    if (this.adresse == null) {
      if (other.adresse != null) {
        return false;
      }
    } else if (!this.adresse.equals(other.adresse)) {
      return false;
    }
    if (this.treffen == null) {
      if (other.treffen != null) {
        return false;
      }
    } else if (!this.treffen.equals(other.treffen)) {
      return false;
    }
    return true;
  }

  public McbModel getAdresse() {
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + (this.adresse == null ? 0 : this.adresse.hashCode());
    result = prime * result + (this.treffen == null ? 0 : this.treffen.hashCode());
    return result;
  }

  /**
   * for JSON
   *
   * @param adresse
   */
  public void setAdresse(McbModel adresse) {
    this.adresse = adresse;
  }

  protected void setFruehstueckSamstag(int anzahl) {
    this.fruehstueckSamstag = anzahl;
  }

  protected void setFruehstueckSonntag(int anzahl) {
    this.fruehstueckSonntag = anzahl;
  }

  /**
   * For JSON
   *
   * @param treffen
   */
  public void setTreffen(Treffen treffen) {
    this.treffen = treffen;
  }

  @Override
  public String toString() {
    return this.getTreffen().toString();
  }

}
