package gildedrose;

import java.util.ArrayList;
import java.util.List;

public class GildedRose {

	public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
	public static final String BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
	public static final String AGED_BRIE = "Aged Brie";
	public static final String CONJURED_MANA_CAKE = "Conjured Mana Cake";
	public static final String ELIXIR_OF_THE_MONGOOSE = "Elixir of the Mongoose";
	public static final String _5_DEXTERITY_VEST = "+5 Dexterity Vest";
	private static List<Item> items = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("OMGHAI!");

		items.add(new Item(_5_DEXTERITY_VEST, 10, 20));
		items.add(new Item(AGED_BRIE, 2, 0));
		items.add(new Item(ELIXIR_OF_THE_MONGOOSE, 5, 7));
		items.add(new Item(SULFURAS_HAND_OF_RAGNAROS, 0, 80));
		items.add(new Item(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, 15, 20));
		items.add(new Item(CONJURED_MANA_CAKE, 3, 6));

		updateQuality();
	}

	public static void updateQuality() {
		for (Item item : items) {
			ShopItemFactory.createFor(item).increaseAge();
		}
	}

	public static void addItem(Item item) {
		if (items == null) {
			items = new ArrayList<Item>();
		}
		items.add(item);
	}

	public static void clearItems() {
		items = new ArrayList<Item>();
	}

}