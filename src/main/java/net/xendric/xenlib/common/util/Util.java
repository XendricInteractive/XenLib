package net.xendric.xenlib.common.util;

import java.lang.reflect.Method;
import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Generic useful types n' stuff to use anywhere, because they're a pain to type
 * out each time! Also useful methods.
 */
public class Util {
	public static ArrayList<String> dependantMods = new ArrayList<String>();
	public static String[] dyes = { "Black", "Red", "Green", "Brown", "Blue", "Purple", "Cyan", "LightGray", "Gray",
			"Pink", "Lime", "Yellow", "LightBlue", "Magenta", "Orange", "White" };

	public static EnumDyeColor[] enumDyes = { EnumDyeColor.BLACK, EnumDyeColor.RED, EnumDyeColor.GREEN,
			EnumDyeColor.BROWN, EnumDyeColor.BLUE, EnumDyeColor.PURPLE, EnumDyeColor.CYAN, EnumDyeColor.SILVER,
			EnumDyeColor.GRAY, EnumDyeColor.PINK, EnumDyeColor.LIME, EnumDyeColor.YELLOW, EnumDyeColor.LIGHT_BLUE,
			EnumDyeColor.MAGENTA, EnumDyeColor.ORANGE, EnumDyeColor.WHITE };

	/**
	 * Compare two itemstacks, wildcard doesn't matter.
	 * 
	 * @return
	 */
	public static boolean itemStacksEqualWithWildcard(ItemStack stack1, ItemStack stack2) {
		return !(stack1.isEmpty() || stack2.isEmpty()) && stack1.getItem() == stack2.getItem()
				&& (stack1.getItemDamage() == stack2.getItemDamage()
						|| stack1.getItemDamage() == OreDictionary.WILDCARD_VALUE
						|| stack2.getItemDamage() == OreDictionary.WILDCARD_VALUE);
	}

	/**
	 * Takes in a class, and a method name. Checks to see if supplied method name
	 * exists in that class and returns a boolean. Could be useful someday?
	 * 
	 * @param clazz
	 * @param methodName
	 * @return boolean
	 */
	public static boolean doesMethodExist(Class<?> clazz, String methodName) {
		boolean result = false;
		for (Method method : clazz.getDeclaredMethods()) {
			if (method.getName().equals(methodName))
				return true;
		}
		return result;
	}

	public Block getBlockFromMod(String mod, String blockName) {
		Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(mod + ":" + blockName));
		return block == null ? Blocks.AIR : block;
	}

	public Item getItemFromMod(String mod, String itemName) {
		Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(mod + ":" + itemName));
		return item == null ? Items.AIR : item;
	}

	public static void setDependants(String dependantMod) {
		dependantMods.add(dependantMod);
	}

	public static String getDependants() {
		if (dependantMods != null)
			for (String s : dependantMods)
				return s;
		return null;
	}
}
