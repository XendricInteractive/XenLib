package net.xendric.xenlib.common.core;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.xendric.xenlib.common.core.item.Wrench;

public class ObjectHandler {
	public static final List<Item> ITEMS = new ArrayList<Item>();

	public static final Item WRENCH = new Wrench("xendric_wrench", ITEMS).setCreativeTab(CreativeTabs.TOOLS);
}
