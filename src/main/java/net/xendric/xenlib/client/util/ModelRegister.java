package net.xendric.xenlib.client.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.xendric.xenlib.client.core.render.IModelRegister;
import net.xendric.xenlib.common.core.block.ObjectHandler;

@EventBusSubscriber
public class ModelRegister {
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent e) {
		for (Item item : ObjectHandler.ITEMS) {
			if (item instanceof IModelRegister) {
				((IModelRegister) item).registerItemModels();
			}
		}

		for (Block block : ObjectHandler.BLOCKS) {
			if (block instanceof IModelRegister)
				((IModelRegister) block).registerItemModels();
		}
	}
}
