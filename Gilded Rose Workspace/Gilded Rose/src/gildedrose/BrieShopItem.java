package gildedrose;

public class BrieShopItem extends ShopItem {

	public BrieShopItem(Item item) {
		super(item);
	}

	@Override
	protected void updateQuality() {
		int addend = item.getSellIn() < 1 ? 2 : 1;
		item.setQuality(Math.min(item.getQuality() + addend, 50));

	}

}
