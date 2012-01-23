package gildedrose;

public class ShopItemFactory {

	public static ShopItem createFor(Item item) {
		if (item.getName().equals(GildedRose.AGED_BRIE)) {
			return new BrieShopItem(item);
		}
		if (item.getName().equals(GildedRose.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT)) {
			return new BackstagePassShopItem(item);
		}
		if (item.getName().equals(GildedRose.SULFURAS_HAND_OF_RAGNAROS)) {
			return new SulfurasShopItem(item);
		}
		if (item.getName().startsWith("Conjured")) {
			return new ConjuredShopItem(item);
		}
		return new ShopItem(item);
	}
	
}
