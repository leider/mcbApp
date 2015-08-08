package mcb.persistenz;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Locale;

import mcb.model.Treffen;

import org.junit.BeforeClass;
import org.junit.Test;

public class TreffenTest {

  @BeforeClass
  public static void setLocale() {
    Locale.setDefault(Locale.GERMAN);
  }

  @Test
  public void einNeuesTreffenIstGespann() {
    assertTrue(new Treffen().isGespann());
  }

  @Test
  public void einTreffenImAprilIstGespann() {
    Treffen treffen = new Treffen();
    treffen.setErsterTagString("01.04.2009");
    assertTrue(treffen.isGespann());
  }

  @Test
  public void einTreffenImOktoberIstSolo() {
    Treffen treffen = new Treffen();
    treffen.setErsterTagString("01.10.2009");
    assertFalse(treffen.isGespann());
  }

}
