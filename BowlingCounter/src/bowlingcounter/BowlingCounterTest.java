package bowlingcounter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class BowlingCounterTest {

	private BowlingGame game;

	@Before
	public void initGame() {
		game = new BowlingGame();
	}

	@Test
	public void simpleStuff() throws Exception {
		assertEquals(0, game.currentCount());

		game.roll(5);
		assertEquals(5, game.currentCount());

		game.roll(4);
		assertEquals(9, game.currentCount());
	}

	@Test
	public void extendedStuff() throws Exception {
		game.roll(5);
		game.roll(5);
		assertEquals(10, game.currentCount());
		game.roll(5);
		assertEquals(20, game.currentCount());
	}

	@Test
	public void fullScore() throws Exception {
		game.roll(10);
		assertEquals(10, game.currentCount());
		game.roll(10);
		assertEquals(30, game.currentCount());
		game.roll(10);
		assertEquals(60, game.currentCount());
		game.roll(10);
		assertEquals(90, game.currentCount());
		game.roll(10);
		assertEquals(120, game.currentCount());
		game.roll(10);
		assertEquals(150, game.currentCount());
		game.roll(10);
		assertEquals(180, game.currentCount());
		game.roll(10);
		assertEquals(210, game.currentCount());
		game.roll(10);
		assertEquals(240, game.currentCount());
		game.roll(10);
		assertEquals(270, game.currentCount());
		game.roll(10);
		assertEquals(290, game.currentCount());
		game.roll(10);
		assertEquals(300, game.currentCount());
	}
}
