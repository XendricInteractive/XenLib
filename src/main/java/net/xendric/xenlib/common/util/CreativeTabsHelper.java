package net.xendric.xenlib.common.util;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabsHelper extends CreativeTabs {
	boolean searchBar;
	int searchBarWidth;
	int labelColor;
	EnumDyeColor enumLabelColor;
	Item itemIcon;

	/**
	 * @param String
	 *            - Unlocalized Name
	 * @param Item
	 *            - Tab Icon
	 * @param Boolean
	 *            - Search Bar
	 */
	public CreativeTabsHelper(String name, Item icon, boolean searchBar) {
		super(name);
		this.itemIcon = icon;
		this.searchBar = searchBar;
	}

	/**
	 * @param String
	 *            - Unlocalized Name
	 * @param Block
	 *            - Tab Icon
	 * @param Boolean
	 *            - Search Bar
	 */
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

	public CreativeTabs setLabelColor(int labelColor) {
		this.labelColor = labelColor;
		return this;
	}

	public CreativeTabs setLabelColor(EnumDyeColor labelColor) {
		this.labelColor = labelColor.getColorValue();
		return this;
	}

	public CreativeTabs setSearchbarWidth(int searchBarWidth) {
		if (this.searchBar)
			this.searchBarWidth = searchBarWidth;
		return this;
	}
}
