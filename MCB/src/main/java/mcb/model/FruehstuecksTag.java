package mcb.model;

public enum FruehstuecksTag {
  Samstag {
    @Override
    public int getAnzahlFruehstueckAm(Besuch besuch) {
      return besuch.getFruehstueckSamstag();
    }

    @Override
    public void setFruehstueck(Besuch besuch, int anzahl) {
      besuch.setFruehstueckSamstag(anzahl);
    }
  },
  Sonntag {
    @Override
    public int getAnzahlFruehstueckAm(Besuch besuch) {
      return besuch.getFruehstueckSonntag();
    }

    @Override
    public void setFruehstueck(Besuch besuch, int anzahl) {
      besuch.setFruehstueckSonntag(anzahl);
    }
  };

  public abstract int getAnzahlFruehstueckAm(Besuch besuch);

  public abstract void setFruehstueck(Besuch besuch, int anzahl);
}