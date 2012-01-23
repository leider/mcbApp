package gildedrose;

public class ShopItem {

	protected final Item item;

	public ShopItem(Item item) {
		this.item = item;
	}

	public int getSellIn() {
		return item.getSellIn();
	}

	public void increaseAge() {
		updateQuality();
		item.setSellIn(getSellIn() - 1);
	}

	protected void updateQuality() {
		int addend = getSellIn() < 1 ? 2 : 1;
		item.setQuality(Math.max(item.getQuality() - addend, 0));
	}

}
