package net.xendric.xenlib.common.handlers;

import net.minecraft.block.state.IBlockState;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.xendric.xenlib.common.world.gen.WorldGenOre;

public class OreGenerationHandler {
	public static void addOre(IBlockState ore, int minY, int maxY, int minSize, int maxSize, int rarity) {
		WorldGenOre.addOre(ore, minY, maxY, minSize, maxSize, rarity);
	}

	public static void generateOres() {
		GameRegistry.registerWorldGenerator(new WorldGenOre(), 0);
	}
}
