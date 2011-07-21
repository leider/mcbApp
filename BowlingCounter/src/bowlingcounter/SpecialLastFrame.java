package bowlingcounter;

public class SpecialLastFrame extends BowlingFrame {

	private int third;

	@Override
	public void count(int value) {
		if (secondCounted) {
			third = value;
			return;
		}
		if (firstCounted) {
			second = value;
			secondCounted = true;
			return;
		}
		firstRoll(value);
	}

	@Override
	public void setFollower(BowlingFrame nextFrame) {
		// nothing to do here
	}

	@Override
	public boolean isStrike() {
		return false;
	}

	@Override
	public int getCount() {
		return getPreviousFrameCount() + super.getOwnCount() + third;
	}

}
