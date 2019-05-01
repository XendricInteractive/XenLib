package net.xendric.xenlib.common.handlers;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.xendric.xenlib.common.core.ObjectHandler;

/**
 * Generic ore dictionary registry stuff
 */
public class OreDictionaryHandler {
	public static void registerOreDict() {
		//// BLOCKS
		// ORES
		OreDictionary.registerOre("oreCopper", new ItemStack(ObjectHandler.ORE_COPPER));

		// MINERAL BLOCKS
		OreDictionary.registerOre("blockCopper", new ItemStack(ObjectHandler.BLOCK_COPPER));
		OreDictionary.registerOre("blockSteel", new ItemStack(ObjectHandler.BLOCK_STEEL));

		//// ITEMS
		// INGOTS
		OreDictionary.registerOre("ingotCopper", new ItemStack(ObjectHandler.INGOT_COPPER));
		OreDictionary.registerOre("ingotSteel", new ItemStack(ObjectHandler.INGOT_STEEL));

		// NUGGETS
		OreDictionary.registerOre("nuggetCopper", new ItemStack(ObjectHandler.NUGGET_COPPER));
		OreDictionary.registerOre("nuggetSteel", new ItemStack(ObjectHandler.NUGGET_STEEL));

		// DUSTS
		OreDictionary.registerOre("dustCopper", new ItemStack(ObjectHandler.DUST_COPPER));
		OreDictionary.registerOre("dustSteel", new ItemStack(ObjectHandler.DUST_STEEL));

		//// VANILLA
		OreDictionary.registerOre("stone", new ItemStack(Blocks.STONE, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("stone", new ItemStack(Blocks.STONEBRICK, 1, OreDictionary.WILDCARD_VALUE));
	}
}
