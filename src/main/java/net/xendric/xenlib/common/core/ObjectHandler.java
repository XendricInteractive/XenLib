package net.xendric.xenlib.common.core;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.xendric.xenlib.common.XenLib;
import net.xendric.xenlib.common.core.item.ItemBase;
import net.xendric.xenlib.common.core.item.Wrench;

@EventBusSubscriber
public class ObjectHandler {
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	public static final List<Item> ITEMS = new ArrayList<Item>();

	public static final Item WRENCH = ((ItemBase) new Wrench("xendric_wrench").setCreativeTab(XenLib.tab)).setToolTip("");

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> e) {
		e.getRegistry().registerAll(ObjectHandler.BLOCKS.toArray(new Block[0]));
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> e) {
		e.getRegistry().registerAll(ObjectHandler.ITEMS.toArray(new Item[0]));
	}
}
