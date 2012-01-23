package gildedrose;

import static gildedrose.GildedRose.SULFURAS_HAND_OF_RAGNAROS;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestSulfurasShopItem {

	@Test
	public void testQualityWhileAging() throws Exception {
		Item item = new Item(SULFURAS_HAND_OF_RAGNAROS, 0, 80);
		ShopItem shopItem = ShopItemFactory.createFor(item);
		int quality = item.getQuality();
		int sellIn = item.getSellIn();
		for (int i = 0; i < 100; i++) {
			shopItem.increaseAge();
			assertEquals(quality, item.getQuality());
			assertEquals(sellIn, item.getSellIn());
		}
	}

}
