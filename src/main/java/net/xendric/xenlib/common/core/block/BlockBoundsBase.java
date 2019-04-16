package net.xendric.xenlib.common.core.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockBoundsBase extends BlockBase {
	double minX, minY, minZ, maxX, maxY, maxZ;

	/**
	 * @param name
	 * @param mat
	 * @param minX
	 * @param minY
	 * @param minZ
	 * @param maxX
	 * @param maxY
	 * @param maxZ
	 */
	public BlockBoundsBase(String name, Material mat, double minX, double minY, double minZ, double maxX, double maxY,
			double maxZ) {
		super(name, mat);
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
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(minX, minY, minZ, 1d - maxX, 1d - maxY, 1d - maxZ);
	}
}
