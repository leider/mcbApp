package mcb.frame;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import mcb.persistenz.PersistenceStore;

public class OpenTreffenAction extends AbstractAction {
  private static final long serialVersionUID = 8568897588247326614L;
  private final PersistenceStore persistenceStore;

  public OpenTreffenAction(String name, PersistenceStore persistenceStore) {
    super(name);
    this.persistenceStore = persistenceStore;
  }

  @SuppressWarnings("unused")
  public void actionPerformed(ActionEvent e) {
    new TreffenFrame(this.persistenceStore);
  }
}