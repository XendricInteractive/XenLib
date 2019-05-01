package net.xendric.xenlib.common.core;

import static net.xendric.xenlib.common.XenLib.tab;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.xendric.xenlib.common.core.block.BlockBase;
import net.xendric.xenlib.common.core.block.OreBase;
import net.xendric.xenlib.common.core.item.ItemBase;
import net.xendric.xenlib.common.core.item.Wrench;

public class ObjectHandler {
	public static final List<Item> ITEMS = new ArrayList<Item>();
	public static final List<Block> BLOCKS = new ArrayList<Block>();

	//// BLOCKS
	// ORES
	public static final Block ORE_COPPER = new OreBase("ore_copper", BLOCKS, ITEMS, "pickaxe", 1).setCreativeTab(tab);

	// MINERAL BLOCKS
	public static final Block BLOCK_COPPER = new BlockBase("block_copper", Material.IRON, BLOCKS, ITEMS)
			.setCreativeTab(tab);
	public static final Block BLOCK_STEEL = new BlockBase("block_steel", Material.IRON, BLOCKS, ITEMS)
			.setCreativeTab(tab);

	//// ITEMS
	public static final Item WRENCH = new Wrench("xendric_wrench", ITEMS).setCreativeTab(tab);

	// INGOTS
	public static final Item INGOT_COPPER = new ItemBase("ingot_copper", ITEMS).setCreativeTab(tab);
	public static final Item INGOT_STEEL = new ItemBase("ingot_steel", ITEMS).setCreativeTab(tab);

	// NUGGETS
	public static final Item NUGGET_COPPER = new ItemBase("nugget_copper", ITEMS).setCreativeTab(tab);
	public static final Item NUGGET_STEEL = new ItemBase("nugget_steel", ITEMS).setCreativeTab(tab);

	// DUSTS
	public static final Item DUST_COPPER = new ItemBase("dust_copper", ITEMS).setCreativeTab(tab);
	public static final Item DUST_STEEL = new ItemBase("dust_steel", ITEMS).setCreativeTab(tab);
}
