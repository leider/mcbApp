package mcb.panel.widgets;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import mcb.model.Adresse;
import mcb.model.FruehstuecksTag;
import mcb.panel.AdressePanel;

public class FruehstuecksSpinner extends JPanel {

  private static final long serialVersionUID = 1L;

  private JSpinner spinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));

  private final FruehstuecksTag tag;

  private final AdressePanel adressePanel;

  public FruehstuecksSpinner(FruehstuecksTag tag, AdressePanel adressePanel) {
    super(new BorderLayout());
    this.tag = tag;
    this.adressePanel = adressePanel;
    this.init();
  }

  private void fruehstueckChanged() {
    Adresse adresse = this.adressePanel.getAdresse();
    if (adresse == null || adresse.getAktuellerBesuch() == null) {
      this.setValue(0);
      return;
    }
    adresse.getAktuellerBesuch().setFruehstueckFuer(this.getValue(), this.tag);
    this.adressePanel.saveAdresse();
  }

  public int getValue() {
    return ((Integer) this.spinner.getValue()).intValue();
  }

  private void init() {
    this.add(this.spinner, BorderLayout.CENTER);
    ((SpinnerNumberModel) this.spinner.getModel()).setMinimum(new Integer(0));
    this.spinner.addChangeListener(new ChangeListener() {

      public void stateChanged(ChangeEvent e) {
        FruehstuecksSpinner.this.fruehstueckChanged();
      }

    });
  }

  public void setValue(int anzahl) {
    this.spinner.setValue(new Integer(anzahl));
  }

}
