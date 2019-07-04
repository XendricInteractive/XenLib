package net.xendric.xenlib.common.util;

import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class EnumDyeColorHelper {
	/**
	 * Returns an EnumDyeColor based upon ore dictionary entries with
	 * <b>dye'Color'</b> (dyeBlack, dyeYellow, dyeCyan, etc.) For when you need to
	 * put in an itemstack (getting the held item from player) and need to get an
	 * EnumDyeColor in return.
	 * <p>
	 * {@code EnumDyeColor color = EnumDyeColorHelper.byOreDictDyeName(heldItem);}
	 * 
	 * @param stackIn
	 * @return EnumDyeColor
	 */
	public static EnumDyeColor byOreDictDyeName(ItemStack stackIn) {
		// Loop 0 to 15. 16 dye colors
		for (int i = 0; i < 16; i++) {
			String stringDye = "dye" + Util.dyes[i];
			String stringOreDyes = OreDictionary.getOreName(OreDictionary.getOreID(stringDye));

			// Loop through dictionary for stacks with tag "dye'Color'"
			for (ItemStack stack : OreDictionary.getOres(stringDye)) {
				if (stackIn.getItem() == stack.getItem() && stackIn.getMetadata() == stack.getMetadata()) {
					// If dictionary string tag is equal to "dye'Color'"
					// Return the correct EnumDyeColor
					if (stringOreDyes.equals(stringDye))
						return EnumDyeColor.byDyeDamage(i);
				}
			}
		}
		return null;
	}
}
