package net.xendric.xenlib.common.core.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.FarmlandWaterManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.xendric.xenlib.client.core.proxy.ClientProxy;
import net.xendric.xenlib.client.core.render.IModelRegister;

public class BlockFarmlandBase extends BlockFarmland implements IModelRegister {
	public BlockFarmlandBase(String name, List<Block> blockList, List<Item> itemList) {
		super();

		setUnlocalizedName(name);
		setRegistryName(name);

		blockList.add(this);
		itemList.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	public void registerItemModels() {
		ClientProxy.registerItemModel(Item.getItemFromBlock(this), 0, "inventory");
	}

	public static boolean hasWater(World world, BlockPos pos) {
		for (BlockPos.MutableBlockPos mutable : BlockPos.getAllInBoxMutable(pos.add(-4, 0, -4), pos.add(4, 1, 4)))
			if (world.getBlockState(mutable).getMaterial() == Material.WATER)
				return true;
		return FarmlandWaterManager.hasBlockWaterTicket(world, pos);
	}
}