package net.xendric.xenlib.client.core.proxy;

import com.google.common.collect.ImmutableList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.xendric.xenlib.common.core.proxy.CommonProxy;

/**
 * Here to properly render/model edible blocks so the mod works for server.
 * (Putting this in the ModelRegister class crashes the game)
 * 
 * @author MCE626
 */
public class ClientProxy extends CommonProxy {
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);
	}

	public void init(FMLInitializationEvent e) {
		super.init(e);
	}

	/**
	 * So we can render block models that are smaller than a normal block. Used by
	 * edible blocks to render in inventory/item on ground.
	 * 
	 * @param block
	 * @param item
	 * @param name
	 */
	public static void registerItemBlockModel(Block block, ItemBlock item, String name) {
		StateMapperBase mapper = new DefaultStateMapper();
		BlockStateContainer stateContainer = block.getBlockState();
		ImmutableList<IBlockState> values = stateContainer.getValidStates();

		for (IBlockState state : values) {
			String stringProperties = mapper.getPropertyString(state.getProperties());
			registerItemModel(Item.getItemFromBlock(block), block.getMetaFromState(state), stringProperties);
		}
	}

	/**
	 * Generic item rendering.
	 * 
	 * @param item
	 * @param meta
	 * @param name
	 */
	public static void registerItemModel(Item item, int meta, String name) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), name));
	}

	/**
	 * For fluids to move entities
	 * 
	 * @param entity
	 * @param mat
	 * @return
	 */
	@Override
	public boolean handleMaterialAcceleration(Entity entity, Material mat) {
		World world = entity.world;
		AxisAlignedBB AABB = entity.getEntityBoundingBox().grow(0.0D, -0.4000000059604645D, 0.0D).shrink(0.001D);

		int aaXMin = MathHelper.floor(AABB.minX);
		int aaXMax = MathHelper.ceil(AABB.maxX);
		int aaYMin = MathHelper.floor(AABB.minY);
		int aaYMax = MathHelper.ceil(AABB.maxY);
		int aaZMin = MathHelper.floor(AABB.minZ);
		int aaZMax = MathHelper.ceil(AABB.maxZ);

		boolean successful = false;
		Vec3d vec = Vec3d.ZERO;
		BlockPos.PooledMutableBlockPos poolPos = BlockPos.PooledMutableBlockPos.retain();

		for (int currentAAX = aaXMin; currentAAX < aaXMax; ++currentAAX) {
			for (int currentAAY = aaYMin; currentAAY < aaYMax; ++currentAAY) {
				for (int currentAAZ = aaZMin; currentAAZ < aaZMax; ++currentAAZ) {
					poolPos.setPos(currentAAX, currentAAY, currentAAZ);
					IBlockState state = world.getBlockState(poolPos);
					Block block = state.getBlock();

					Boolean result = block.isEntityInsideMaterial(world, poolPos, state, entity, aaYMax, mat, false);
					if (result != null && result == true) {
						successful = true;
						vec = block.modifyAcceleration(world, poolPos, entity, vec);
						continue;
					} else if (result != null && result == false)
						continue;

					if (state.getMaterial() == mat) {
						double newAAY = currentAAY + 1
								- BlockLiquid.getLiquidHeightPercent(state.getValue(BlockLiquid.LEVEL).intValue());

						if (aaYMax >= newAAY) {
							successful = true;
							vec = block.modifyAcceleration(world, poolPos, entity, vec);
						}
					}
				}
			}
		}

		poolPos.release();

		if (vec.lengthVector() > 0.0D && entity.isPushedByWater()) {
			vec = vec.normalize();
			double addMotion = 0.014D;
			entity.motionX += vec.x * addMotion;
			entity.motionY += vec.y * addMotion;
			entity.motionZ += vec.z * addMotion;
		}

		entity.fallDistance = 0.0F;
		return successful;
	}
}
