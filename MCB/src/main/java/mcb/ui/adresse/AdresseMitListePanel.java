package mcb.ui.adresse;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import mcb.model.Adresse;
import mcb.model.McbException;
import mcb.persistenz.PersistenceStore;
import mcb.persistenz.Summaries;
import mcb.persistenz.filter.AlleFilter;
import mcb.persistenz.filter.McbAction;
import mcb.persistenz.filter.SelectedFilter;
import mcb.persistenz.filter.adressen.FilterAction;
import mcb.persistenz.filter.adressen.SucheFilter;
import mcb.ui.base.BearbeitenAction;
import mcb.ui.base.ComponentFactory;
import mcb.ui.base.ModelMitListePanel;
import mcb.ui.base.ModelPanel;
import mcb.ui.base.SelectionInListPanel;

import com.jgoodies.binding.PresentationModel;

public class AdresseMitListePanel extends ModelMitListePanel<Adresse> {

  private static final long serialVersionUID = 444596326461558352L;
  private JTextField suchText;
  private List<FilterAction> filterActions = new ArrayList<FilterAction>();

  public AdresseMitListePanel(PersistenceStore persistenceStore) {
    super(persistenceStore);
  }

  public void addFilterAction(FilterAction filterAction) {
    this.filterActions.add(filterAction);
  }

  @Override
  protected SelectionInListPanel<Adresse> createListePanel() {
    return new AdressenSelectionInListPanel(this.persistenceStore);
  }

  @Override
  protected ModelPanel<Adresse> createModelPanel(PresentationModel<Adresse> model, BearbeitenAction<Adresse> action) {
    return new AdressePanel(model, action, this.persistenceStore);
  }

  @Override
  protected void createToolBar() {
    JToolBar toolBar = new JToolBar();
    toolBar.add(new JLabel("Suche nach Vorname oder Name: "));
    this.suchText = new JTextField();
    toolBar.add(this.suchText);
    this.suchText.setPreferredSize(new Dimension(300, this.suchText.getPreferredSize().height));
    this.suchText.addKeyListener(new KeyListener() {

      public void keyPressed(KeyEvent e) {
        // don't needed
      }

      public void keyReleased(KeyEvent e) {
        String text = AdresseMitListePanel.this.suchText.getText();
        if ("".equals(text)) {
          SelectedFilter.set(AlleFilter.getInstance());
        } else {
          SucheFilter.getInstance().setSucheText(text);
          SelectedFilter.set(SucheFilter.getInstance());
        }
        AdresseMitListePanel.this.updateListe();
      }

      public void keyTyped(KeyEvent e) {
        // don't needed
      }
    });
    toolBar.addSeparator();

    PresentationModel<Summaries> presentationModel = new PresentationModel<Summaries>(Summaries.getInstance());
    toolBar.add(new JLabel("Samstag:"));
    JFormattedTextField samstag = ComponentFactory.createIntegerField(presentationModel.getModel(Summaries.FRUEHSTUCK_SAMSTAG));
    toolBar.add(samstag);
    int kantenlaenge = samstag.getPreferredSize().height;
    samstag.setPreferredSize(new Dimension(3 * kantenlaenge, kantenlaenge));
    samstag.setEditable(false);
    toolBar.add(new JLabel("Sonntag:"));
    JFormattedTextField sonntag = ComponentFactory.createIntegerField(presentationModel.getModel(Summaries.FRUEHSTUCK_SONNTAG));
    toolBar.add(sonntag);
    sonntag.setPreferredSize(new Dimension(3 * kantenlaenge, kantenlaenge));
    sonntag.setEditable(false);
    toolBar.add(new JLabel("Meldungen:"));
    JFormattedTextField meldungen = ComponentFactory.createIntegerField(presentationModel.getModel(Summaries.ANZAHL_MELDUNGEN));
    toolBar.add(meldungen);
    meldungen.setPreferredSize(new Dimension(3 * kantenlaenge, kantenlaenge));
    meldungen.setEditable(false);
    this.add(toolBar, BorderLayout.NORTH);
  }

  @Override
  protected List<McbAction> getAllActionsExceptBearbeiten() {
    List<McbAction> result = super.getAllActionsExceptBearbeiten();
    result.addAll(this.filterActions);
    return result;
  }

  @Override
  protected String loeschenMessage(Adresse model) {
    return "Willst Du die Adresse von " + model.toString() + " wirklich terminieren?";
  }

  @Override
  protected void speichereModel(Adresse model) throws McbException {
    this.persistenceStore.saveAll();
  }

  @Override
  protected void switchEnabledForPanels(boolean listEnabled) {
    super.switchEnabledForPanels(listEnabled);
    this.suchText.setEditable(listEnabled);
  }
}
