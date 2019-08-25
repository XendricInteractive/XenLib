package net.xendric.xenlib.common.core.block;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockShulkerBox;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class FluidBase extends Fluid {
	protected static int mapColor = 0xFFFFFF;
	protected static float alpha = 0.2f;
	protected static SoundEvent emptySound = SoundEvents.ITEM_BUCKET_EMPTY;
	protected static SoundEvent fillSound = SoundEvents.ITEM_BUCKET_FILL;
	protected static Material mat = Material.WATER;
	protected static boolean pushEntity;

	public FluidBase(String fluidName, ResourceLocation still, ResourceLocation flowing,
			@Nullable ResourceLocation overlay) {
		super(fluidName, still, flowing, overlay);
	}

	public FluidBase(String fluidName, ResourceLocation still, ResourceLocation flowing,
			@Nullable ResourceLocation overlay, int mapColor) {
		super(fluidName, still, flowing, overlay);
		setColor(mapColor);
	}

	public FluidBase(String fluidName, ResourceLocation still, ResourceLocation flowing,
			@Nullable ResourceLocation overlay, int mapColor, float overlayAlpha) {
		super(fluidName, still, flowing, overlay, mapColor);
		setAlpha(overlayAlpha);
	}

	@Override
	public int getColor() {
		return mapColor;
	}

	public float getAlpha() {
		return alpha;
	}

	public FluidBase setAlpha(float alphaIn) {
		alpha = alphaIn;
		return this;
	}

	@Override
	public FluidBase setEmptySound(SoundEvent soundIn) {
		emptySound = soundIn;
		return this;
	}

	@Override
	public SoundEvent getEmptySound() {
		return emptySound;
	}

	@Override
	public FluidBase setFillSound(SoundEvent soundIn) {
		fillSound = soundIn;
		return this;
	}

	@Override
	public SoundEvent getFillSound() {
		return fillSound;
	}

	public FluidBase setMaterial(Material matIn) {
		mat = matIn;
		return this;
	}

	public Material getMaterial() {
		return mat;
	}

	@Override
	public boolean doesVaporize(FluidStack stack) {
		if (block == null)
			return false;
		return block.getDefaultState().getMaterial() == getMaterial();
	}

	public static boolean getPushesEntity() {
		return pushEntity;
	}

	public static void setPushesEntity(boolean canPushEntities) {
		pushEntity = canPushEntities;
	}

	public Vec3d modifyAcceleration(World world, BlockPos pos, Entity entity, Vec3d motion) {
		if (getPushesEntity()) {
			Vec3d flowAdder = getFlow(world, pos, world.getBlockState(pos));
			return motion.add(flowAdder);
		} else {
			return motion;
		}
	}

	protected Vec3d getFlow(IBlockAccess world, BlockPos pos, IBlockState state) {
		double d0 = 0.0D;
		double d1 = 0.0D;
		double d2 = 0.0D;
		int i = this.getRenderedDepth(state);
		BlockPos.PooledMutableBlockPos poolPos = BlockPos.PooledMutableBlockPos.retain();

		for (EnumFacing facing : EnumFacing.Plane.HORIZONTAL) {
			poolPos.setPos(pos).move(facing);
			int j = this.getRenderedDepth(world.getBlockState(poolPos));

			if (j < 0) {
				if (!world.getBlockState(poolPos).getMaterial().blocksMovement()) {
					j = this.getRenderedDepth(world.getBlockState(poolPos.down()));

					if (j >= 0) {
						int k = j - (i - 8);
						d0 += facing.getFrontOffsetX() * k;
						d1 += facing.getFrontOffsetY() * k;
						d2 += facing.getFrontOffsetZ() * k;
					}
				}
			} else if (j >= 0) {
				int l = j - i;
				d0 += facing.getFrontOffsetX() * l;
				d1 += facing.getFrontOffsetY() * l;
				d2 += facing.getFrontOffsetZ() * l;
			}
		}

		Vec3d vec3d = new Vec3d(d0, d1, d2);
		if (state.getValue(BlockLiquid.LEVEL).intValue() >= 8) {
			for (EnumFacing enumfacing1 : EnumFacing.Plane.HORIZONTAL) {
				poolPos.setPos(pos).move(enumfacing1);

				if (this.causesDownwardCurrent(world, poolPos, enumfacing1)
						|| this.causesDownwardCurrent(world, poolPos.up(), enumfacing1)) {
					vec3d = vec3d.normalize().addVector(0.0D, -6.0D, 0.0D);
					break;
				}
			}
		}
		poolPos.release();
		return vec3d.normalize();
	}

	protected int getDepth(IBlockState state) {
		return state.getMaterial() == this.getMaterial() ? state.getValue(BlockLiquid.LEVEL).intValue() : -1;
	}

	protected int getRenderedDepth(IBlockState state) {
		int i = this.getDepth(state);
		return i >= 8 ? 0 : i;
	}

	/**
	 * Checks if an additional {@code -6} vertical drag should be applied to the
	 * entity. See {#link net.minecraft.block.BlockLiquid#getFlow()}
	 */
	private boolean causesDownwardCurrent(IBlockAccess world, BlockPos pos, EnumFacing side) {
		IBlockState state = world.getBlockState(pos);
		Block block = state.getBlock();
		Material material = state.getMaterial();

		if (material == this.getMaterial()) {
			return false;
		} else if (side == EnumFacing.UP) {
			return true;
		} else if (material == Material.ICE) {
			return false;
		} else {
			boolean flag = isExceptBlockForAttachWithPiston(block) || block instanceof BlockStairs;
			return !flag && state.getBlockFaceShape(world, pos, side) == BlockFaceShape.SOLID;
		}
	}

	protected static boolean isExceptionBlockForAttaching(Block attachBlock) {
		return attachBlock instanceof BlockShulkerBox || attachBlock instanceof BlockLeaves
				|| attachBlock instanceof BlockTrapDoor || attachBlock == Blocks.BEACON
				|| attachBlock == Blocks.CAULDRON || attachBlock == Blocks.GLASS || attachBlock == Blocks.GLOWSTONE
				|| attachBlock == Blocks.ICE || attachBlock == Blocks.SEA_LANTERN
				|| attachBlock == Blocks.STAINED_GLASS;
	}

	protected static boolean isExceptBlockForAttachWithPiston(Block attachBlock) {
		return isExceptionBlockForAttaching(attachBlock) || attachBlock == Blocks.PISTON
				|| attachBlock == Blocks.STICKY_PISTON || attachBlock == Blocks.PISTON_HEAD;
	}
}
