package net.xendric.xenlib.common.config;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.xendric.xenlib.common.References;

@EventBusSubscriber
@Config(modid = References.MODID, type = Type.INSTANCE, name = References.NAME)
public class XenlibConfigManager {
	@Name("Ore Generation")
	public static OreGeneration oreGen = new OreGeneration();

	public static class OreGeneration {
		@Name("Copper Generation")
		@Comment("Should copper ore generate?")
		public boolean copperGeneration = true;
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
