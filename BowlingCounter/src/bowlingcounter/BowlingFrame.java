package bowlingcounter;

import java.util.Iterator;

public class BowlingFrame {

	private int first;
	protected boolean firstCounted;
	protected int second;
	protected boolean secondCounted;
	BowlingFrame nextFrame;
	private BowlingFrame previousFrame;

	public void count(int value) {
		if (firstCounted) {
			secondRoll(value);
			return;
		}
		firstRoll(value);
	}

	protected int extras() {
		if (nextFrame != null) {
			if (isStrike()) {
				return nextTwoThrows();
			}
			if (isTenButNotStrike()) {
				return firstOfFollower();
			}
		}
		return 0;
	}

	protected int firstOfFollower() {
		return nextFrame != null ? nextFrame.first : 0;
	}

	protected void firstRoll(int value) {
		this.first = value;
		firstCounted = true;
	}

	public int getCount() {
		return getPreviousFrameCount() + getOwnCount() + extras();
	}

	protected int getPreviousFrameCount() {
		return (previousFrame != null ? previousFrame.getCount() : 0);
	}

	protected int getOwnCount() {
		return first + second;
	}

	public boolean isCompleted() {
		return isStrike() || secondCounted;
	}

	public boolean isStrike() {
		return first == 10;
	}

	public boolean isTenButNotStrike() {
		return getOwnCount() == 10 && !isStrike();
	}

	public Iterator<BowlingFrame> iterator() {
		Iterator<BowlingFrame> result = new Iterator<BowlingFrame>() {

			BowlingFrame currentFrame = BowlingFrame.this;

			@Override
			public boolean hasNext() {
				return currentFrame.nextFrame != null;
			}

			@Override
			public BowlingFrame next() {
				currentFrame = currentFrame.nextFrame;
				return currentFrame;
			}

			@Override
			public void remove() {
				// TODO Auto-generated method stub

			}

		};
		return result;
	}

	public BowlingFrame lastFrame() {
		BowlingFrame result = this;
		Iterator<BowlingFrame> iterator = this.iterator();
		while (iterator.hasNext()) {
			BowlingFrame bowlingFrame = iterator.next();
			result = bowlingFrame;
		}
		return result;
	}

	private int nextTwoThrows() {
		return nextFrame != null ? nextFrame.getOwnCount()
				+ (nextFrame.isStrike() ? nextFrame.firstOfFollower() : 0) : 0;
	}

	protected void secondRoll(int value) {
		if (isStrike()) {
			throw new IllegalArgumentException("you already have a strike");
		}
		if (first + value > 10) {
			throw new IllegalArgumentException(
					"frame cannot exceed 10 by throws");
		}
		second = value;
		secondCounted = true;
	}

	public void setFollower(BowlingFrame nextFrame) {
		this.nextFrame = nextFrame;
		nextFrame.previousFrame = this;
	}
}
