package net.xendric.xenlib.common.config;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.RequiresMcRestart;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.xendric.xenlib.common.References;

@EventBusSubscriber
@Config(modid = References.MODID, type = Type.INSTANCE, name = References.NAME)
public class XenLibConfigManager {
	@Name("Ore Generation")
	public static OreGeneration oreGen = new OreGeneration();

	@Name("Disable Common Metals")
	@Comment({ "Disable common metal stuff? (Includes ore generation)", "Copper, Steel" })
	@RequiresMcRestart
	public static boolean disableCommonMetals = false;

	public static class OreGeneration {
		@Name("Disable Copper Generation")
		@Comment("Disable copper ore generation?")
		@RequiresMcRestart
		public boolean disableCopperGeneration = false;

		@Name("Copper Generation")
		public CopperGeneration copperGen = new CopperGeneration();

		public static class CopperGeneration {
			@Name("Copper Min Y Generation")
			@Comment("Minimum Y level ore will spawn")
			@RequiresMcRestart
			public int copperMinY = 15;

			@Name("Copper Max Y Generation")
			@Comment("Max Y level ore will spawn")
			@RequiresMcRestart
			public int copperMaxY = 75;

			@Name("Copper Min Vein Size")
			@Comment("Minimum ore blocks in a vein")
			@RequiresMcRestart
			public int copperMinSize = 2;

			@Name("Copper Max Vein Size")
			@Comment("Max ore blocks in a vein")
			@RequiresMcRestart
			public int copperMaxSize = 8;

			@Name("Copper Vein Rarity")
			@Comment("About how many veins in a chunk will spawn")
			@RequiresMcRestart
			public int copperRarity = 10;
		}
	}

	@EventBusSubscriber
	public static class Handler {
		@SubscribeEvent
		public void onConfigChangedEvent(OnConfigChangedEvent e) {
			if (e.getModID().contentEquals(References.MODID))
				ConfigManager.sync(References.MODID, Type.INSTANCE);
		}
	}
}
