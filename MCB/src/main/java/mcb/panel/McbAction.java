package mcb.panel;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

public abstract class McbAction extends AbstractAction {

  private static boolean isRunningOnMac() {
    return System.getProperty("mrj.version") != null;
  }

  public static final int ALLE = KeyEvent.VK_A;
  public static final int BEARBEITEN = KeyEvent.VK_B;
  public static final int DEUTSCHLAND = KeyEvent.VK_D;
  public static final int SUCHE = KeyEvent.VK_F;
  public static final int GEMELDET = KeyEvent.VK_G;
  public static final int EMAILKAPUTT = KeyEvent.VK_K;
  public static final int LOESCHEN = KeyEvent.VK_L;
  public static final int AUSLAND = KeyEvent.VK_M;
  public static final int NEU = KeyEvent.VK_N;
  public static final int NICHTGEMELDET = KeyEvent.VK_U;
  public static final int MITGLIED = KeyEvent.VK_Z;
  public static final int EINLADUNGEN_EMAIL = 0;
  public static final int EINLADUNGEN_POST = 0;

  public static final int KEINE_EINLADUNG = 0;
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
