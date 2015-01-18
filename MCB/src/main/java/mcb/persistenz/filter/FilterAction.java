package mcb.persistenz.filter;

import java.awt.event.ActionEvent;

public class FilterAction extends McbAction {

  private static final long serialVersionUID = -5779635312416205673L;
  private AdresseFilter filter;
  private ListUpdater frame;

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
