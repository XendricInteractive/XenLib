package net.xendric.xenlib.common.container.parts;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotValid extends Slot {
	ISlotValidator validator;

	public SlotValid(ISlotValidator validSlot, IInventory inventory, int index, int x, int y) {
		super(inventory, index, x, y);
		this.validator = validSlot;
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		return validator.isItemValid(stack);
	}
}
