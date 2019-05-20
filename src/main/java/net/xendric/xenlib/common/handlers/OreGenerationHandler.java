package net.xendric.xenlib.common.handlers;

import net.minecraft.block.state.IBlockState;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.xendric.xenlib.common.world.gen.WorldGenOre;

public class OreGenerationHandler {
	/**
	 * Ore Block, min and max y you can find the ore, min and max size of the ore
	 * vein, and about how many veins in a chunk, or rarity.
	 * 
	 * @param ore
	 * @param minY
	 * @param maxY
	 * @param minSize
	 * @param maxSize
	 * @param rarity
	 */
	public static void addOre(IBlockState ore, int minY, int maxY, int minSize, int maxSize, int rarity) {
		WorldGenOre.addOre(ore, minY, maxY, minSize, maxSize, rarity);
	}

	public static void generateOres() {
		GameRegistry.registerWorldGenerator(new WorldGenOre(), 0);
	}
}
