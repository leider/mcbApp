package mcb.persistenz.filter.adressen;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import mcb.persistenz.filter.AdresseFilter;
import mcb.persistenz.filter.ListUpdater;
import mcb.persistenz.filter.McbAction;
import mcb.persistenz.filter.SelectedFilter;

public class FilterAction extends McbAction {

  private static final long serialVersionUID = -5779635312416205673L;
  private AdresseFilter filter;
  private ListUpdater frame;
  public static final int SUCHE = KeyEvent.VK_F;
  public static final int NICHTGEMELDET = KeyEvent.VK_U;
  public static final int EMAILKAPUTT = KeyEvent.VK_K;
  public static final int GEMELDET = KeyEvent.VK_G;
  public static final int DEUTSCHLAND = KeyEvent.VK_D;
  public static final int AUSLAND = KeyEvent.VK_M;

  public FilterAction(AdresseFilter filter, ListUpdater simpleFrame) {
    super(filter.getLabel(), filter.getKeyMask());
    this.filter = filter;
    this.frame = simpleFrame;
  }

  public void actionPerformed(ActionEvent e) {
    SelectedFilter.set(this.filter);
    this.frame.updateListe();
  }

}
