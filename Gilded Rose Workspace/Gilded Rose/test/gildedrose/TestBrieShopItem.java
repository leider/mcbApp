package gildedrose;

import static gildedrose.GildedRose.AGED_BRIE;
import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class TestBrieShopItem {

	
	@Test
	public void testQualityWhileAging() throws Exception {
		Item item = new Item(AGED_BRIE, 2, 0);
		ShopItem shopItem = ShopItemFactory.createFor(item);
		int quality = item.getQuality();
		
		for (int i = 0; i < 100; i++) {
			shopItem.increaseAge();
			int addend = item.getSellIn() < 0 ? 2 : 1;
			quality = Math.min(quality + addend, 50);
			assertEquals(quality, item.getQuality());
		}
	}


}
