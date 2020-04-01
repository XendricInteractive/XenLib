package net.xendric.xenlib.common.core.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.xendric.xenlib.client.core.proxy.ClientProxy;
import net.xendric.xenlib.client.core.render.IModelRegister;

public class BlockBushBase extends BlockBush implements IModelRegister {
	public BlockBushBase(String name, Material mat, List<Block> blockList, List<Item> itemList) {
		super(mat);

		setUnlocalizedName(name);
		setRegistryName(name);

		blockList.add(this);
		// itemList.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	public BlockBushBase(String name, Material mat, List<Block> blockList, List<Item> itemList, SoundType sound) {
		this(name, mat, blockList, itemList);
		this.blockSoundType = sound;
	}

	@SideOnly(Side.CLIENT)
	public void registerItemModels() {
		ClientProxy.registerItemModel(Item.getItemFromBlock(this), 0, "inventory");
	}

	@Override
	public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction,
			IPlantable plantable) {
		// IBlockState plant = plantable.getPlant(world, pos.offset(direction));
		EnumPlantType plantType = plantable.getPlantType(world, pos.offset(direction));

		if (plantType == EnumPlantType.getPlantType("Lava"))
			return state.getMaterial() == Material.LAVA && state.getValue(BlockLiquid.LEVEL) == 0;
		return false;
	}
}