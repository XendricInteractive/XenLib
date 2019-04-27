package net.xendric.xenlib.common.core.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockUsable extends BlockBase {
	public static final PropertyInteger AMOUNT = PropertyInteger.create("amount", 0, 8);
	private Potion potion;
	private int potionDuration;
	private int potionAmplifier;
	private float potionEffectProbability;

	public BlockUsable(String name, Material mat, List<Block> blockList, List<Item> itemList) {
		super(name, mat, blockList, itemList);
		this.setDefaultState(this.blockState.getBaseState().withProperty(AMOUNT, 0));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		int meta = getMetaFromState(state);
		double subtractY = (float) (meta * 2) / 18f;

		return new AxisAlignedBB(0d, 0d, 0d, 1d, 1d - subtractY, 1d);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!player.isSneaking()) {
			this.useBlock(world, pos, state, player);
			return true;
		} else
			return false;
	}

	private void useBlock(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		if (!world.isRemote && Potion.getIdFromPotion(potion) > 0
				&& world.rand.nextFloat() < this.potionEffectProbability)
			player.addPotionEffect(new PotionEffect(this.potion, this.potionDuration, this.potionAmplifier));

		int metaState = getMetaFromState(state);
		int meta = metaState += 1;

		if (meta >= 9)
			world.setBlockToAir(pos);
		else
			world.setBlockState(pos, getStateFromMeta(meta), 2);
	}

	public BlockUsable setPotionEffect(Potion potion, int dur, int amp, float prob) {
		this.potion = potion;
		this.potionDuration = dur;
		this.potionAmplifier = amp;
		this.potionEffectProbability = prob;
		return this;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(AMOUNT, meta);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(AMOUNT);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { AMOUNT });
	}

	@Override
	public int damageDropped(IBlockState state) {
		return getMetaFromState(state);
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,
			EntityPlayer player) {
		return new ItemStack(Item.getItemFromBlock(this), 1, 0);
	}
}
