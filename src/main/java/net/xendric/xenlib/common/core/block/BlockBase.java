package net.xendric.xenlib.common.core.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.xendric.xenlib.client.core.proxy.ClientProxy;
import net.xendric.xenlib.client.core.render.IModelRegister;
import net.xendric.xenlib.common.core.item.ItemBlockEdible;
import net.xendric.xenlib.common.core.item.ItemBlockUsable;

public class BlockBase extends Block implements IModelRegister {
	public BlockBase(String name, Material mat, List<Block> blockList, List<Item> itemList) {
		super(mat);

		setUnlocalizedName(name);
		setRegistryName(name);

		blockList.add(this);
		if (this instanceof BlockEdible)
			itemList.add(new ItemBlockEdible(this).setRegistryName(this.getRegistryName()));
		else if (this instanceof BlockUsable)
			itemList.add(new ItemBlockUsable(this).setRegistryName(this.getRegistryName()));
		else
			itemList.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	public BlockBase(String name, Material mat, List<Block> blockList, List<Item> itemList, SoundType sound) {
		this(name, mat, blockList, itemList);
		this.blockSoundType = sound;
	}

	@SideOnly(Side.CLIENT)
	public void registerItemModels() {
		if (this instanceof BlockEdible)
			ClientProxy.registerItemBlockModel(this, new ItemBlockEdible(this), "inventory");
		else if (this instanceof BlockUsable)
			ClientProxy.registerItemBlockModel(this, new ItemBlockUsable(this), "inventory");
		else
			ClientProxy.registerItemModel(Item.getItemFromBlock(this), 0, "inventory");
	}

	public static void turnIntoWater(World world, BlockPos pos) {
		if (world.provider.doesWaterVaporize())
			world.setBlockToAir(pos);
		else {
			world.getBlockState(pos).getBlock().dropBlockAsItem(world, pos, world.getBlockState(pos), 0);
			world.setBlockState(pos, Blocks.WATER.getDefaultState());
			world.neighborChanged(pos, Blocks.WATER, pos);
		}
	}
}
