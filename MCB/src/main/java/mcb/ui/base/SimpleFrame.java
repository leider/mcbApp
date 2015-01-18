package mcb.ui.base;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.WindowConstants;

import mcb.persistenz.PersistenceStore;
import mcb.persistenz.filter.ListUpdater;

import com.jgoodies.binding.beans.Model;

public abstract class SimpleFrame<T extends Model> extends JFrame implements ListUpdater {

  private static final long serialVersionUID = -3301128307489915655L;

  protected ModelMitListePanel<T> panel;

  protected ButtonGroup group = new ButtonGroup();

  protected final PersistenceStore persistenceStore;

  public SimpleFrame(String string, PersistenceStore persistenceStore) {
    super(string);
    this.persistenceStore = persistenceStore;
    this.initialize();
    this.packCenterAndShow();
  }

  protected abstract void addExtraMenu(JMenuBar bar);

  protected void addMenu() {
    JMenuBar bar = new JMenuBar();
    JMenu aktionen = new JMenu("Aktionen");
    bar.add(aktionen);
    aktionen.add(this.panel.getNeuAction());
    aktionen.add(this.panel.getLoeschenAction());
    aktionen.add(this.panel.getBearbeitenAction());
    this.addExtraMenu(bar);
    this.setJMenuBar(bar);
  }

  protected abstract ModelMitListePanel<T> createPanel();

  private void initialize() {
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    this.panel = this.createPanel();
    this.getContentPane().add(this.panel, BorderLayout.CENTER);
    this.addMenu();
  }

  private void packCenterAndShow() {
    this.pack();
    Dimension paneSize = this.getSize();
    Dimension screenSize = this.getToolkit().getScreenSize();
    this.setLocation((screenSize.width - paneSize.width) / 2, (int) ((screenSize.height - paneSize.height) * 0.45));
    this.setVisible(true);
  }

  public void updateListe() {
    this.panel.updateListe();
  }

}
