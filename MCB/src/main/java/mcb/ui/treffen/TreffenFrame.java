package mcb.ui.treffen;

import javax.swing.JMenuBar;

import mcb.model.Treffen;
import mcb.persistenz.PersistenceStore;
import mcb.ui.base.SimpleFrame;

public class TreffenFrame extends SimpleFrame<Treffen> {

  private static final long serialVersionUID = -6092233954906826211L;

  public TreffenFrame(PersistenceStore persistenceStore) {
    super("MCB Treffen", persistenceStore);
  }

  @Override
  protected void addExtraMenu(JMenuBar bar) {
    // don't needed here
  }

  @Override
  protected TreffenMitListePanel createPanel() {
    return new TreffenMitListePanel(this.persistenceStore);
  }

}
