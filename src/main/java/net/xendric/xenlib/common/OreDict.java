package net.xendric.xenlib.common;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Generic ore dictionary registry stuff
 */
public class OreDict {
	public static void registerOreDict() {
		OreDictionary.registerOre("stone", new ItemStack(Blocks.STONE, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("stone", new ItemStack(Blocks.STONEBRICK, 1, OreDictionary.WILDCARD_VALUE));
	}
}
