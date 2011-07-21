package bowlingcounter;

public class BowlingGame {

	private BowlingFrame firstFrame = new BowlingFrame();
	private static final int MAX_FRAMES = 10;

	private int numberOfFrames = 1;

	public int currentCount() {
		return currentFrame().getCount();
	}

	private BowlingFrame currentFrame() {
		return firstFrame.lastFrame();
	}

	public void roll(int value) {
		getFrameForRoll().count(value);
	}

	private BowlingFrame getFrameForRoll() {
		if (currentFrame().isCompleted()) {
			BowlingFrame newLast = createNextFrame();
			currentFrame().setFollower(newLast);
		}
		return currentFrame();
	}

	private BowlingFrame createNextFrame() {
		numberOfFrames++;
		return (numberOfFrames == MAX_FRAMES) ? new SpecialLastFrame()
				: new BowlingFrame();
	}
}
