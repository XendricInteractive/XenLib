package net.xendric.xenlib.common.core.block;

import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.xendric.xenlib.client.core.proxy.ClientProxy;
import net.xendric.xenlib.client.core.render.IModelRegister;
import net.xendric.xenlib.common.core.ObjectHandler;

public class BlockDirectionalBase extends BlockDirectional implements IModelRegister {
	public BlockDirectionalBase(String name, Material mat) {
		super(mat);

		setUnlocalizedName(name);
		setRegistryName(name);

		ObjectHandler.BLOCKS.add(this);
		ObjectHandler.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	public void registerItemModels() {
		ClientProxy.registerItemModel(Item.getItemFromBlock(this), 0, "inventory");
	}
}
