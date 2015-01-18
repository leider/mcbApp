package mcb.persistenz.filter;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

public abstract class McbAction extends AbstractAction {

  private static boolean isRunningOnMac() {
    return System.getProperty("mrj.version") != null;
  }

  public static final int BEARBEITEN = KeyEvent.VK_B;
  public static final int LOESCHEN = KeyEvent.VK_L;
  public static final int NEU = KeyEvent.VK_N;
  private static final long serialVersionUID = 4150636331971514776L;

  private KeyStroke accelerator;

  public McbAction(String name, int aKeyEventConstant) {
    super(name);
    this.setAccelerator(aKeyEventConstant);
  }

  public KeyStroke getAccelerator() {
    return this.accelerator;
  }

  protected void setAccelerator(int aKeyEventConstant) {
    if (aKeyEventConstant != 0) {
      this.accelerator = KeyStroke.getKeyStroke(aKeyEventConstant, McbAction.isRunningOnMac() ? ActionEvent.META_MASK
          : ActionEvent.CTRL_MASK);
      this.putValue(Action.ACCELERATOR_KEY, this.accelerator);
    }
  }

}
