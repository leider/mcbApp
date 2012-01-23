package gildedrose;

import static gildedrose.GildedRose.AGED_BRIE;
import static gildedrose.GildedRose.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT;
import static gildedrose.GildedRose.ELIXIR_OF_THE_MONGOOSE;
import static gildedrose.GildedRose.SULFURAS_HAND_OF_RAGNAROS;
import static gildedrose.GildedRose._5_DEXTERITY_VEST;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Assume;
import org.junit.Test;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class IntegrationTestGildedRose {

	@DataPoint
	public static Item dexterity = new Item(_5_DEXTERITY_VEST, 10, 20);
	@DataPoint
	public static Item elixir = new Item(ELIXIR_OF_THE_MONGOOSE, 5, 7);
	@DataPoint
	public static Item brie = new Item(AGED_BRIE, 2, 0);
	@DataPoint
	public static Item backstage = new Item(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, 15, 20);
	@DataPoint
	public static Item sulfuras = new Item(SULFURAS_HAND_OF_RAGNAROS, 0, 80);
	@DataPoint
	public static Item cake = new Item(GildedRose.CONJURED_MANA_CAKE, 3, 6);
	
	@After
	public void tearDown() {
		GildedRose.clearItems();
	}
	
	@Theory
	public void testThatStandardItemsQualityDecreasesEachDayAccordingToTheRules(Item item) throws Exception {
		Assume.assumeTrue(!item.equals(brie) && !item.equals(backstage) && !item.equals(sulfuras) && !item.equals(cake));
		int quality = item.getQuality();
		int sellIn = item.getSellIn();
		addItem(item);
		
		for (int i = 0; i < sellIn; i++) {
			GildedRose.updateQuality();
			quality = quality - 1;
			assertEquals(quality, item.getQuality());
		}
		while (quality > 0) { 
			GildedRose.updateQuality();
			quality = Math.max(quality - 2, 0);
			assertEquals(quality, item.getQuality());
		}
		GildedRose.updateQuality();
		assertEquals(0, item.getQuality());
	}

	@Theory
	public void testThatConjuredItemsQualityDecreasesEachDayAccordingToTheRules(Item item) throws Exception {
		Assume.assumeTrue(item.equals(cake));
		int quality = item.getQuality();
		int sellIn = item.getSellIn();
		addItem(item);
		
		for (int i = 0; i < sellIn; i++) {
			GildedRose.updateQuality();
			quality = quality - 2;
			assertEquals(quality, item.getQuality());
		}
		while (quality > 0) { 
			GildedRose.updateQuality();
			quality = Math.max(quality - 4, 0);
			assertEquals(quality, item.getQuality());
		}
		GildedRose.updateQuality();
		assertEquals(0, item.getQuality());
	}
	
	@Test
	public void testThatBrieQualityIncreasesEachDayAccordingToTheRules() throws Exception {
		int quality = brie.getQuality();
		addItem(brie);
		for (int i = 0; i < 100; i++) {
			GildedRose.updateQuality();
			int addend = brie.getSellIn() < 0 ? 2 : 1;
			quality = Math.min(quality + addend, 50);
			assertEquals(quality, brie.getQuality());
		}
	}
	
	@Test
	public void testThatbackstagePassesQualityIncreasesEachDayAccordingToTheRules() throws Exception {
		int quality = backstage.getQuality();
		int sellIn = backstage.getSellIn();
		addItem(backstage);
		for (int i = 0; i < sellIn; i++) {
			int addend = 0;
			if (backstage.getSellIn() < 6) {
				addend = 3;
			} else if (backstage.getSellIn() < 11) {
				addend = 2;
			} else {
				addend = 1;
			}
			GildedRose.updateQuality();
			quality = Math.min(quality + addend, 50);
			assertEquals(quality, backstage.getQuality());
		}
		GildedRose.updateQuality();
		assertEquals(0, backstage.getQuality());
	}
	
	@Test
	public void testSulfurasQualityAndAgeIsCOnstant() throws Exception {
		int quality = sulfuras.getQuality();
		int sellIn = sulfuras.getSellIn();
		addItem(sulfuras);
		for (int i = 0; i < 100; i++) {
			GildedRose.updateQuality();
			assertEquals(quality, sulfuras.getQuality());
			assertEquals(sellIn, sulfuras.getSellIn());
		}
	}
	
	@Theory
	public void testThatItemsAgeEachDay(Item item) throws Exception {
		Assume.assumeTrue(!item.equals(sulfuras));
		int sellIn = item.getSellIn();
		addItem(item);
		for (int i = 0; i < 30; i++) {
			GildedRose.updateQuality();
			sellIn = sellIn - 1;
			assertEquals(sellIn, item.getSellIn());
		}
	}

	private void addItem(Item item) {
		GildedRose.addItem(item);
	}

}
