package gildedrose;

import static gildedrose.GildedRose.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT;
import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class TestBackstagePassShopItem {

	@Test
	public void testQualityWhileAging() throws Exception {
		Item item = new Item(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, 15, 20);
		ShopItem shopItem = ShopItemFactory.createFor(item);
		int quality = item.getQuality();
		int sellIn = item.getSellIn();
		for (int i = 0; i < sellIn; i++) {
			int addend = 0;
			if (item.getSellIn() < 6) {
				addend = 3;
			} else if (item.getSellIn() < 11) {
				addend = 2;
			} else {
				addend = 1;
			}
			quality = Math.min(quality + addend, 50);
			shopItem.increaseAge();
			assertEquals(quality, item.getQuality());
		}
		shopItem.increaseAge();
		assertEquals(0, item.getQuality());
	}


}
