package net.xendric.xenlib.common.core;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.xendric.xenlib.common.core.block.BlockBase;
import net.xendric.xenlib.common.core.block.BlockOreBase;
import net.xendric.xenlib.common.core.item.ItemBase;
import net.xendric.xenlib.common.core.item.Wrench;

public class ObjectHandler {
	public static final List<Item> ITEMS = new ArrayList<Item>();
	public static final List<Block> BLOCKS = new ArrayList<Block>();

	//// BLOCKS
	// ORES
	public static final Block ORE_COPPER = new BlockOreBase("ore_copper", BLOCKS, ITEMS, "pickaxe", 1)
			.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);

	// MINERAL BLOCKS
	public static final Block BLOCK_COPPER = new BlockBase("block_copper", Material.IRON, BLOCKS, ITEMS)
			.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	public static final Block BLOCK_STEEL = new BlockBase("block_steel", Material.IRON, BLOCKS, ITEMS)
			.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);

	//// ITEMS
	public static final Item WRENCH = new Wrench("xendric_wrench", ITEMS).setCreativeTab(CreativeTabs.TOOLS);

	// INGOTS
	public static final Item INGOT_COPPER = new ItemBase("ingot_copper", ITEMS).setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item INGOT_STEEL = new ItemBase("ingot_steel", ITEMS).setCreativeTab(CreativeTabs.MATERIALS);

	// NUGGETS
	public static final Item NUGGET_COPPER = new ItemBase("nugget_copper", ITEMS)
			.setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item NUGGET_STEEL = new ItemBase("nugget_steel", ITEMS).setCreativeTab(CreativeTabs.MATERIALS);

	// DUSTS
	public static final Item DUST_COPPER = new ItemBase("dust_copper", ITEMS).setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item DUST_STEEL = new ItemBase("dust_steel", ITEMS).setCreativeTab(CreativeTabs.MATERIALS);
}
