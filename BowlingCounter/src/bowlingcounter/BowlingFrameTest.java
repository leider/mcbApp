package bowlingcounter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BowlingFrameTest {

	@Test
	public void frameCount() throws Exception {
		BowlingFrame frame = new BowlingFrame();
		frame.count(5);
		assertEquals(5, frame.getCount());
		frame.count(5);
		assertEquals(10, frame.getCount());
	}

	@Test(expected = IllegalArgumentException.class)
	public void limits() throws Exception {
		BowlingFrame frame = new BowlingFrame();
		frame.count(10);
		frame.count(5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void moreLimits() throws Exception {
		BowlingFrame frame = new BowlingFrame();
		frame.count(9);
		frame.count(5);
	}

	@Test
	public void isStrike() throws Exception {
		BowlingFrame frame = new BowlingFrame();
		frame.count(9);
		assertFalse(frame.isStrike());

		frame = new BowlingFrame();
		frame.count(10);
		assertTrue(frame.isStrike());
	}

	@Test
	public void isComplete() throws Exception {
		BowlingFrame frame = new BowlingFrame();
		frame.count(4);
		frame.count(5);
		assertFalse(frame.isTenButNotStrike());

		frame = new BowlingFrame();
		frame.count(4);
		frame.count(6);
		assertTrue(frame.isTenButNotStrike());
	}

	@Test
	public void isCompleteWithFollowerFrame() throws Exception {
		BowlingFrame frame = new BowlingFrame();
		frame.count(4);
		frame.count(6);

		BowlingFrame nextFrame = new BowlingFrame();
		frame.setFollower(nextFrame);
		nextFrame.count(3);
		nextFrame.count(5);
		assertEquals(13, frame.getCount());
	}

	@Test
	public void isStrikeWithFollowerFrame() throws Exception {
		BowlingFrame frame = new BowlingFrame();
		frame.count(10);

		BowlingFrame nextFrame = new BowlingFrame();
		frame.setFollower(nextFrame);
		nextFrame.count(3);
		nextFrame.count(5);
		assertEquals(18, frame.getCount());
	}

	@Test
	public void isStrikeWithFollowerFrameAlsoStrike() throws Exception {
		BowlingFrame frame = new BowlingFrame();
		frame.count(10);

		BowlingFrame nextFrame = new BowlingFrame();
		frame.setFollower(nextFrame);
		nextFrame.count(10);

		BowlingFrame nextNextFrame = new BowlingFrame();
		nextFrame.setFollower(nextNextFrame);
		nextNextFrame.count(4);
		assertEquals(24, frame.getCount());
	}

	@Test
	public void manyFrames() throws Exception {
		BowlingFrame frame = new BowlingFrame();
		frame.count(10);

		BowlingFrame nextFrame = new BowlingFrame();
		frame.setFollower(nextFrame);
		nextFrame.count(10);

		BowlingFrame nextNextFrame = new BowlingFrame();
		nextFrame.setFollower(nextNextFrame);
		nextNextFrame.count(4);
		assertEquals(24, frame.getCount());
		assertEquals(38, nextFrame.getCount());
	}

}
