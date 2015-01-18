package mcb.model;

import org.junit.Assert;
import org.junit.Test;

public class LandTest {

  @Test
  public void deutschlandIstDefault() {
    Assert.assertEquals("", Land.landFuerKuerzel("D"));
  }

  @Test
  public void nachbarlaenderSindKorrekt() {
    Assert.assertEquals("FRANKREICH", Land.landFuerKuerzel("F"));
    Assert.assertEquals("NIEDERLANDE", Land.landFuerKuerzel("NL"));
    Assert.assertEquals("ÖSTERREICH", Land.landFuerKuerzel("A"));
    Assert.assertEquals("SCHWEIZ", Land.landFuerKuerzel("CH"));
  }

}
