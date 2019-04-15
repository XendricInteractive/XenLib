package net.xendric.xenlib.common.core.block;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.versioning.DependencyParser.DependencyInfo;
import net.xendric.xenlib.common.References;

public class ObjectHandler {
	static DependencyInfo info = new DependencyInfo();
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	public static final List<Item> ITEMS = new ArrayList<Item>();

	public static CreativeTabs getCreativeTab() {
		if (info.dependants != null && info.dependants.equals(References.MODID)) {
			for (CreativeTabs tabs : CreativeTabs.CREATIVE_TAB_ARRAY) {
				return CreativeTabs.CREATIVE_TAB_ARRAY[tabs.getTabIndex()];
			}
		}
		return null;
	}
}
