package gildedrose;

public class BackstagePassShopItem extends ShopItem {

	public BackstagePassShopItem(Item item) {
		super(item);
	}

	@Override
	protected void updateQuality() {
		if (getSellIn() < 1) {
			item.setQuality(0);
			return;
		}
		int addend = 0;
		if (getSellIn() < 6) {
			addend = 3;
		} else if (getSellIn() < 11) {
			addend = 2;
		} else {
			addend = 1;
		}
		item.setQuality(Math.min(item.getQuality() + addend, 50));
	}

}
