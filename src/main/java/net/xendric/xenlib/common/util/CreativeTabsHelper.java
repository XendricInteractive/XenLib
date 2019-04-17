package net.xendric.xenlib.common.util;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CreativeTabsHelper extends CreativeTabs {
	boolean searchBar;
	int searchBarWidth, labelColor;
	EnumDyeColor enumLabelColor;

	/**
	 * @param String
	 *            - Unlocalized Tab Name
	 * @param Boolean
	 *            - Search Bar
	 */
	public CreativeTabsHelper(String name, boolean hasSearchBar) {
		super(name);
		this.searchBar = hasSearchBar;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack getTabIconItem() {
		return ItemStack.EMPTY;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasSearchBar() {
		return searchBar;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getSearchbarWidth() {
		return searchBarWidth;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getLabelColor() {
		return labelColor;
	}

	@SideOnly(Side.CLIENT)
	public CreativeTabs setLabelColor(int labelColor) {
		this.labelColor = labelColor;
		return this;
	}

	@SideOnly(Side.CLIENT)
	public CreativeTabs setLabelColor(EnumDyeColor labelColor) {
		this.labelColor = labelColor.getColorValue();
		return this;
	}

	@SideOnly(Side.CLIENT)
	public CreativeTabs setSearchbarWidth(int searchBarWidth) {
		if (this.searchBar)
			this.searchBarWidth = searchBarWidth;
		return this;
	}
}
