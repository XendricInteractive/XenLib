package net.xendric.xenlib.common;

import static net.xendric.xenlib.common.config.XenLibConfigManager.oreGen;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.xendric.xenlib.common.config.XenLibConfigManager;
import net.xendric.xenlib.common.core.ObjectHandler;
import net.xendric.xenlib.common.core.proxy.CommonProxy;
import net.xendric.xenlib.common.handlers.OreDictionaryHandler;
import net.xendric.xenlib.common.handlers.OreGenerationHandler;

@Mod(modid = References.MODID, name = References.NAME, version = References.VERSION, updateJSON = References.UPDATE)
public class XenLib {
	@Instance(References.MODID)
	public static XenLib instance;

	@SidedProxy(clientSide = References.CLIENT_PROXY_CLASS, serverSide = References.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		proxy.preInit(e);

		/**
		 * Specifically for disabling common metal stuff, but still allowing the wrench
		 * to be registered.
		 */
		for (Item item : ObjectHandler.ITEMS) {
			if (item != ObjectHandler.WRENCH) {
				if (!XenLibConfigManager.disableCommonMetals)
					ForgeRegistries.ITEMS.register(item);
			} else
				ForgeRegistries.ITEMS.register(item);
		}

		for (Block block : ObjectHandler.BLOCKS)
			if (!XenLibConfigManager.disableCommonMetals)
				ForgeRegistries.BLOCKS.register(block);

		if (!oreGen.disableCopperGeneration || !XenLibConfigManager.disableCommonMetals)
			OreGenerationHandler.addOre(ObjectHandler.ORE_COPPER.getDefaultState(), oreGen.copperGen.copperMinY,
					oreGen.copperGen.copperMaxY, oreGen.copperGen.copperMinSize, oreGen.copperGen.copperMaxSize,
					oreGen.copperGen.copperRarity);

		OreGenerationHandler.generateOres();
		OreDictionaryHandler.registerOreDict();

		if(!XenLibConfigManager.disableCommonMetals)
			GameRegistry.addSmelting(ObjectHandler.BLOCK_COPPER, new ItemStack(ObjectHandler.INGOT_COPPER), 0f);
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		proxy.init(e);
		ConfigManager.sync(References.MODID, Type.INSTANCE);
		FMLInterModComms.sendMessage(References.WAILA, "register",
				"net.xendric.xenlib.api.waila.WailaDataProvider.register");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
	}
}
