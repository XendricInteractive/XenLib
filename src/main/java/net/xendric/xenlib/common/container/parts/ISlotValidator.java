package net.xendric.xenlib.common.container.parts;

import net.minecraft.item.ItemStack;

public interface ISlotValidator {
	boolean isItemValid(ItemStack stack);
}
