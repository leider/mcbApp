package mcb.ui.adresse;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import mcb.mail.SendCompleteListener;
import mcb.model.Adresse;
import mcb.persistenz.PersistenceStore;
import mcb.persistenz.filter.AdresseFilter;
import mcb.persistenz.filter.AlleFilter;
import mcb.persistenz.filter.MatchesAlleListener;
import mcb.persistenz.filter.MatchesSucheListener;
import mcb.persistenz.filter.adressen.AuslandFilter;
import mcb.persistenz.filter.adressen.DeutschlandFilter;
import mcb.persistenz.filter.adressen.EinladungEmailFilter;
import mcb.persistenz.filter.adressen.EinladungPostFilter;
import mcb.persistenz.filter.adressen.EmailKaputtFilter;
import mcb.persistenz.filter.adressen.FilterAction;
import mcb.persistenz.filter.adressen.GemeldeteFilter;
import mcb.persistenz.filter.adressen.IstMitgliedFilter;
import mcb.persistenz.filter.adressen.KeineEinladungFilter;
import mcb.persistenz.filter.adressen.NichtGemeldeteFilter;
import mcb.persistenz.filter.adressen.SucheFilter;
import mcb.ui.base.SimpleFrame;

public class AdresseFrame extends SimpleFrame<Adresse> implements MatchesAlleListener, MatchesSucheListener, SendCompleteListener {

  private static final long serialVersionUID = -4920258782523646842L;
  private JCheckBoxMenuItem alle;
  private JCheckBoxMenuItem suche;
  private JLabel infoLabel;

  public AdresseFrame(PersistenceStore persistenceStore) {
    super("MCB", persistenceStore);
  }

  @Override
  protected void addExtraMenu(JMenuBar bar) {
    bar.add(this.createAdminMenu());
    bar.add(this.createFilterMenu());
  }

  private JMenu createAdminMenu() {
    JMenu admin = new JMenu("Administration");
    admin.add(new ExcelExportAction(this, "Export Auswahl nach Excel", this.persistenceStore));
    admin.add(new EmailAction(this, "Verschicke Emails..."));
    admin.addSeparator();
    admin.add(new OpenTreffenAction("Treffen bearbeiten...", this.persistenceStore));
    return admin;
  }

  private JMenu createFilterMenu() {
    JMenu filter = new JMenu("Filter");
    this.alle = this.radioForFilter(AlleFilter.getInstance());
    filter.add(this.alle);
    this.suche = this.radioForFilter(SucheFilter.getInstance());
    filter.add(this.suche);
    filter.addSeparator();
    filter.add(this.radioForFilter(new DeutschlandFilter()));
    filter.add(this.radioForFilter(new AuslandFilter()));
    filter.addSeparator();
    filter.add(this.radioForFilter(new EmailKaputtFilter()));
    filter.addSeparator();
    filter.add(this.radioForFilter(new GemeldeteFilter()));
    filter.add(this.radioForFilter(new NichtGemeldeteFilter()));
    filter.addSeparator();
    filter.add(this.radioForFilter(new EinladungEmailFilter(this.persistenceStore)));
    filter.add(this.radioForFilter(new EinladungPostFilter(this.persistenceStore)));
    filter.add(this.radioForFilter(new KeineEinladungFilter(this.persistenceStore)));
    filter.addSeparator();
    filter.add(this.radioForFilter(new IstMitgliedFilter()));

    AlleFilter.getInstance().setMatchesListener(this);
    SucheFilter.getInstance().setMatchesListener(this);

    this.alle.setSelected(true);

    this.panel.getNeuAction().addPropertyChangeListener(new PropertyChangeListener() {

      @Override
      public void propertyChange(PropertyChangeEvent arg0) {
        if (arg0.getPropertyName().equals("enabled") && arg0.getNewValue().equals(Boolean.TRUE)) {
          AdresseFrame.this.alle.setSelected(true);
        }
      }
    });

    return filter;
  }

  @Override
  protected AdresseMitListePanel createPanel() {
    this.infoLabel = new JLabel("Informationen...");
    this.getContentPane().add(this.infoLabel, BorderLayout.SOUTH);
    return new AdresseMitListePanel(this.persistenceStore);
  }

  @Override
  public void currentlySending(String infoText) {
    this.infoLabel.setText(infoText);
  }

  public List<Adresse> getEmailAdressen() {
    return this.persistenceStore.getAdressen().getEmailAdressen();
  }

  @Override
  public PersistenceStore getPersistenceStore() {
    return this.persistenceStore;
  }

  public void matchesAllePerformed() {
    this.alle.setSelected(true);
  }

  public void matchesSuchePerformed() {
    this.suche.setSelected(true);
  }

  @Override
  public void messagesNotSent() {
    JOptionPane.showMessageDialog(this, "Nicht alle Mails wurden gesendet. Bitte das Log überprüfen.");
  }

  @Override
  public void messagesSent() {
    JOptionPane.showMessageDialog(this, "Mails wurden gesendet.");
  }

  private JCheckBoxMenuItem radioForFilter(AdresseFilter filter) {
    FilterAction filterAction = new FilterAction(filter, this);
    ((AdresseMitListePanel) this.panel).addFilterAction(filterAction);
    JCheckBoxMenuItem radioButtonMenuItem = new JCheckBoxMenuItem(filterAction);
    this.group.add(radioButtonMenuItem);
    return radioButtonMenuItem;
  }

}
