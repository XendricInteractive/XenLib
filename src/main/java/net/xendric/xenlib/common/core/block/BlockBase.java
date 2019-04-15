package net.xendric.xenlib.common.core.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.xendric.xenlib.client.core.proxy.ClientProxy;
import net.xendric.xenlib.client.core.render.IModelRegister;

@EventBusSubscriber
public class BlockBase extends Block implements IModelRegister {
	public static ItemBlock itemBlockClass;

	public BlockBase(String name, Material mat) {
		super(mat);

		setUnlocalizedName(name);
		setRegistryName(name);
		ObjectHandler.BLOCKS.add(this);
		setCreativeTab(ObjectHandler.getCreativeTab());

		if (!(this instanceof Block))
			ObjectHandler.ITEMS.add(getItemBlockClass(itemBlockClass).setRegistryName(this.getRegistryName()));
		else
			ObjectHandler.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));

	}

	public static ItemBlock getItemBlockClass(ItemBlock itemBlock) {
		return itemBlockClass = itemBlock;
	}

	public BlockBase(String name, Material mat, SoundType sound) {
		this(name, mat);
		this.blockSoundType = sound;
	}

	@SubscribeEvent
	public static void registerBlock(RegistryEvent.Register<Block> e) {
		e.getRegistry().registerAll(ObjectHandler.BLOCKS.toArray(new Block[0]));
	}

	@SideOnly(Side.CLIENT)
	public void registerItemModels() {
		if (this instanceof Block)
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
