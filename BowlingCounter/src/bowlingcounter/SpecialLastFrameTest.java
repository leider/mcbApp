package bowlingcounter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SpecialLastFrameTest {

	@Test
	public void hasThreeWhenStrike() throws Exception {
		SpecialLastFrame frame = new SpecialLastFrame();
		frame.count(10);
		frame.count(10);
		frame.count(10);
		assertEquals(30, frame.getCount());
	}

}
