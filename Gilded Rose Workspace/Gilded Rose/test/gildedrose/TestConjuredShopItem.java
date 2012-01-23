package gildedrose;

import static gildedrose.GildedRose.CONJURED_MANA_CAKE;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestConjuredShopItem {

	@Test
	public void testQualityWhileAging() throws Exception {
		Item item = new Item(CONJURED_MANA_CAKE, 3, 6);
		ShopItem shopItem = ShopItemFactory.createFor(item);
		int quality = item.getQuality();
		int sellIn = item.getSellIn();

		for (int i = 0; i < sellIn; i++) {
			shopItem.increaseAge();
			quality = quality - 2;
			assertEquals(quality, item.getQuality());
		}
		while (quality > 0) {
			shopItem.increaseAge();
			quality = Math.max(quality - 4, 0);
			assertEquals(quality, item.getQuality());
		}
		shopItem.increaseAge();
		assertEquals(0, item.getQuality());
	}

}
