package mcb.persistenz;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import org.junit.Test;

public class TreffenTest {

	@Test
	public void testIsGespannApril() {
		Treffen treffen = new Treffen();
		treffen.setErsterTagString("01.04.2009");
		assertTrue(treffen.isGespann());
	}

	@Test
	public void testIsGespannEmpty() {
		assertTrue(new Treffen().isGespann());
	}

	@Test
	public void testIsGespannOktober() {
		Treffen treffen = new Treffen();
		treffen.setErsterTagString("01.10.2009");
		assertFalse(treffen.isGespann());
	}

}
