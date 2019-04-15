package net.xendric.xenlib.common.core.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.xendric.xenlib.client.core.proxy.ClientProxy;
import net.xendric.xenlib.client.core.render.IModelRegister;
import net.xendric.xenlib.common.core.block.ObjectHandler;

public class ItemBase extends Item implements IModelRegister {
	static String toolTip;

	public ItemBase(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		ObjectHandler.ITEMS.add(this);
		setCreativeTab(ObjectHandler.getCreativeTab());
	}

	@SideOnly(Side.CLIENT)
	public void registerItemModels() {
		ClientProxy.registerItemModel(this, 0, "inventory");
	}

	public static String toolTip(String toolTip) {
		return ItemBase.toolTip = toolTip;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, @Nullable World world, List<String> list, ITooltipFlag flag) {
		list.add(toolTip(toolTip));
	}
}
