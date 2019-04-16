package net.xendric.xenlib.common.core.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBlockUsable extends ItemBlock {
	public ItemBlockUsable(Block block) {
		super(block);
		this.setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, @Nullable World world, List<String> list, ITooltipFlag flag) {
		switch (stack.getItemDamage()) {
		case 0:
			list.add("9 Uses Left");
			break;
		case 1:
			list.add("8 Uses Left");
			break;
		case 2:
			list.add("7 Uses Left");
			break;
		case 3:
			list.add("6 Uses Left");
			break;
		case 4:
			list.add("5 Uses Left");
			break;
		case 5:
			list.add("4 Uses Left");
			break;
		case 6:
			list.add("3 Uses Left");
			break;
		case 7:
			list.add("2 Uses Left");
			break;
		case 8:
			list.add("1 Use Left");
			break;
		}
	}
}
