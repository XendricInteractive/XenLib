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
import net.xendric.xenlib.common.core.ObjectHandler;

public class ItemBase extends Item implements IModelRegister {
	public String toolTip;
	boolean hasToolTip;

	public ItemBase(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		ObjectHandler.ITEMS.add(this);
	}

	@SideOnly(Side.CLIENT)
	public void registerItemModels() {
		ClientProxy.registerItemModel(this, 0, "inventory");
	}

	public Item setToolTip(String toolTip) {
		if (toolTip != null) {
			this.hasToolTip = true;
			this.toolTip = toolTip;
		}
		return this;
	}

	/**
	 * Here so yall can know how to add tooltips to items.
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> list, ITooltipFlag flag) {
		if (this.hasToolTip)
			list.add(toolTip.toString());
	}
}
