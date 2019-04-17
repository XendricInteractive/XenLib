package net.xendric.xenlib.client.util;

import java.util.stream.Collectors;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.xendric.xenlib.client.core.render.IModelRegister;
import net.xendric.xenlib.common.core.ObjectHandler;
import net.xendric.xenlib.common.util.Util;

@EventBusSubscriber
public class ModelRegister {
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent e) {
		for (Item item : ForgeRegistries.ITEMS.getValues().stream()
				.filter(item -> item.getRegistryName().getResourceDomain().equals(Util.getModDependents()))
				.collect(Collectors.toList()))
			if (item instanceof IModelRegister)
				((IModelRegister) item).registerItemModels();

		for (Block block : ForgeRegistries.BLOCKS.getValues().stream()
				.filter(item -> item.getRegistryName().getResourceDomain().equals(Util.getModDependents()))
				.collect(Collectors.toList()))
			if (block instanceof IModelRegister)
				((IModelRegister) block).registerItemModels();

		for (Item item : ObjectHandler.ITEMS)
			if (item instanceof IModelRegister)
				((IModelRegister) item).registerItemModels();
	}
}
