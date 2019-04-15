package net.xendric.xenlib.common.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;

/**
 * Firmly grasp it. You're handling that wrench pretty well. :smirk:
 */
public class WrenchHandler {
	private static ResourceLocation registryName;
	private static String[] wrenches = { "wrench", "hammer", "screwdriver", "configurator" };

	/**
	 * Return a boolean if you're holding a wrench or not. Detecting if held
	 * item is a wrench is done by the method below; "isWrench".
	 * 
	 * @param
	 * @return boolean
	 */
	public static boolean holdingWrench(EntityPlayer player) {
		EnumHand hand = EnumHand.MAIN_HAND;
		ItemStack stack = player.getHeldItem(hand);

		if (stack.isEmpty()) {
			hand = EnumHand.OFF_HAND;
			stack = player.getHeldItem(hand);
		}

		if (stack.isEmpty())
			return false;

		if (isWrench(stack))
			return true;
		return false;
	}

	/**
	 * Looks at the registry name for the item and sees if it has the common
	 * string of "Wrench", "Hammer", "Screwdriver" or "Configurator" in it's
	 * name. If it does, it's most likely a wrench and returns true.
	 * 
	 * @param
	 * @return boolean
	 */
	public static boolean isWrench(ItemStack stack) {
		registryName = stack.getItem().getRegistryName();
		String stringName = registryName.getResourcePath().toLowerCase();

		for (String names : wrenches) {
			if (stringName.contains(names.toLowerCase()))
				return true;
		}
		return false;
	}
}
