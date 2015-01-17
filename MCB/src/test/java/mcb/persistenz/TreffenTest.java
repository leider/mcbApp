package mcb.persistenz;

import mcb.model.Treffen;

import org.junit.Assert;
import org.junit.Test;

public class TreffenTest {

	@Test
	public void testIsGespannApril() {
		Treffen treffen = new Treffen();
		treffen.setErsterTagString("01.04.2009");
		Assert.assertTrue(treffen.isGespann());
	}

	@Test
	public void testIsGespannEmpty() {
		Assert.assertTrue(new Treffen().isGespann());
	}

	@Test
	public void testIsGespannOktober() {
		Treffen treffen = new Treffen();
		treffen.setErsterTagString("01.10.2009");
		Assert.assertFalse(treffen.isGespann());
	}

}
