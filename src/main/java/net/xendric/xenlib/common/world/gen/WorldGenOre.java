package net.xendric.xenlib.common.world.gen;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

/**
 * "rarity" is veins in a chunk, "minSize" and "maxSize" is ore blocks in a vein
 */
public class WorldGenOre implements IWorldGenerator {
	static IBlockState ore;
	static int minY, maxY, maxSize, minSize, rarity;

	public static void addOre(IBlockState ore, int minY, int maxY, int maxSize, int minSize, int rarity) {
		WorldGenOre.ore = ore;
		WorldGenOre.minY = minY;
		WorldGenOre.maxY = maxY;
		WorldGenOre.maxSize = maxSize;
		WorldGenOre.minSize = minSize;
		WorldGenOre.rarity = rarity;
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		if (world.provider.getDimension() == 0)
			generateOverworld(random, chunkX, chunkZ, world);
	}

	public void generateOverworld(Random random, int chunkX, int chunkZ, World world) {
		generateOre(ore, world, random, chunkX * 16, chunkZ * 16, minY, maxY, random.nextInt(maxSize) + minSize,
				rarity);
	}

	private void generateOre(IBlockState ore, World world, Random rand, int x, int z, int minY, int maxY, int size,
			int rarity) {
		int deltaY = maxY - minY;

		for (int i = 0; i < rarity; ++i) {
			BlockPos pos = new BlockPos(x + rand.nextInt(16), minY + rand.nextInt(deltaY), z + rand.nextInt(16));

			WorldGenMinable generator = new WorldGenMinable(ore, size);
			generator.generate(world, rand, pos);
		}
	}
}
