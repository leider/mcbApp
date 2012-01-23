package gildedrose;

import static gildedrose.GildedRose.ELIXIR_OF_THE_MONGOOSE;
import static gildedrose.GildedRose._5_DEXTERITY_VEST;
import static org.junit.Assert.assertEquals;

import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;


@RunWith(Theories.class)
public class TestShopItem {

	@DataPoint
	public static Item dexterity = new Item(_5_DEXTERITY_VEST, 10, 20);
	@DataPoint
	public static Item elixir = new Item(ELIXIR_OF_THE_MONGOOSE, 5, 7);
	
	@Theory
	public void testQualityWhileAging(Item item) throws Exception {
		ShopItem shopItem = ShopItemFactory.createFor(item);
		int quality = item.getQuality();
		int sellIn = item.getSellIn();
		
		for (int i = 0; i < sellIn; i++) {
			shopItem.increaseAge();
			quality = quality - 1;
			assertEquals(quality, item.getQuality());
		}
		while (quality > 0) { 
			shopItem.increaseAge();
			quality = Math.max(quality - 2, 0);
			assertEquals(quality, item.getQuality());
		}
		shopItem.increaseAge();
		assertEquals(0, item.getQuality());
	}


}
