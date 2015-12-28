package mcb.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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
    return new EqualsBuilder().append(this.adresse, other.adresse).append(this.treffen, other.treffen).isEquals();
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
    return new HashCodeBuilder(17, 31).append(this.adresse).append(this.treffen).toHashCode();
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
