package net.xendric.xenlib.common.core.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.xendric.xenlib.client.core.proxy.ClientProxy;
import net.xendric.xenlib.client.core.render.IModelRegister;

public class BlockFluidBase extends BlockFluidClassic implements IModelRegister {
	public BlockFluidBase(String name, Fluid fluid, Material material, List<Block> blockList, List<Item> itemList) {
		super(fluid, material);
		setUnlocalizedName(name);
		setRegistryName(name);

		blockList.add(this);
		itemList.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	public BlockFluidBase(String name, Fluid fluid, Material material, MapColor mapColor, List<Block> blockList,
			List<Item> itemList) {
		super(fluid, material, mapColor);
		setUnlocalizedName(name);
		setRegistryName(name);

		blockList.add(this);
		itemList.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@SideOnly(Side.CLIENT)
	public void registerItemModels() {
		ClientProxy.registerItemModel(Item.getItemFromBlock(this), 0, "inventory");
	}
}
