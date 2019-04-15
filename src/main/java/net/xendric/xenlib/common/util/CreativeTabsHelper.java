package net.xendric.xenlib.common.util;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabsHelper extends CreativeTabs {
	static boolean searchBar;
	static int searchBarWidth;
	static int labelColor;
	static EnumDyeColor enumLabelColor;
	static Item itemIcon;

	public CreativeTabsHelper(String name, Item icon, boolean searchBar) {
		super(name);
		CreativeTabsHelper.itemIcon = icon;
		CreativeTabsHelper.searchBar = searchBar;
	}

	public CreativeTabsHelper(String name, Block icon, boolean searchBar) {
		this(name, Item.getItemFromBlock(icon), searchBar);
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(itemIcon);
	}

	@Override
	public boolean hasSearchBar() {
		return searchBar;
	}

	@Override
	public int getSearchbarWidth() {
		return searchBarWidth;
	}

	@Override
	public int getLabelColor() {
		return labelColor;
	}

	/**
	 * Static accessors below
	 */
	public static void getLabelColor(int labelColor) {
		CreativeTabsHelper.labelColor = labelColor;
	}

	public static void getLabelColor(EnumDyeColor labelColor) {
		CreativeTabsHelper.labelColor = labelColor.getColorValue();
	}

	public static void getSearchbarWidth(int searchBarWidth) {
		CreativeTabsHelper.searchBarWidth = searchBarWidth;
	}
}
