package net.xendric.xenlib.common.core.item;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBlockEdible extends ItemBlock {
	public ItemBlockEdible(Block block) {
		super(block);
		setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, @Nullable World world, List<String> list, ITooltipFlag flag) {
		Random rand = new Random();
		int i = rand.nextInt(150);
		list.add("Edible!");

		switch (stack.getItemDamage()) {
		case 0:
			list.add("9 Bites Left");
			break;
		case 1:
			list.add("8 Bites Left");
			break;
		case 2:
			list.add("7 Bites Left");
			break;
		case 3:
			list.add("6 Bites Left");
			break;
		case 4:
			list.add("5 Bites Left");
			break;
		case 5:
			list.add("4 Bites Left");
			break;
		case 6:
			list.add("3 Bites Left");
			break;
		case 7:
			list.add("2 Bites Left");
			break;
		case 8:
			if (i == 23) {
				list.add("1 Byte Left");
				break;
			} else {
				list.add("1 Bite Left");
				break;
			}
		}
	}
}
