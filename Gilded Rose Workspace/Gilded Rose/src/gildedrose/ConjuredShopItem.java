package gildedrose;

public class ConjuredShopItem extends ShopItem {

	public ConjuredShopItem(Item item) {
		super(item);
	}

	@Override
	protected void updateQuality() {
		super.updateQuality();
		super.updateQuality();
	}
}
