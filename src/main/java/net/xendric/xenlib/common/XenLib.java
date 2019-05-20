package net.xendric.xenlib.common;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.xendric.xenlib.common.config.XenlibConfigManager;
import net.xendric.xenlib.common.core.ObjectHandler;
import net.xendric.xenlib.common.core.proxy.CommonProxy;
import net.xendric.xenlib.common.handlers.OreDictionaryHandler;
import net.xendric.xenlib.common.handlers.OreGenerationHandler;
import net.xendric.xenlib.common.util.CreativeTabsHelper;

@Mod(modid = References.MODID, name = References.NAME, version = References.VERSION, updateJSON = References.UPDATE)
public class XenLib {
	@Instance(References.MODID)
	public static XenLib instance;

	@SidedProxy(clientSide = References.CLIENT_PROXY_CLASS, serverSide = References.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;

	/** Registering a creative tab with this library mod */
	public static CreativeTabs tab = new CreativeTabsHelper("xenlib", false) {
		@Override
		@SideOnly(Side.CLIENT)
		public ItemStack getTabIconItem() {
			return new ItemStack(ObjectHandler.WRENCH);
		}
	}.setLabelColor(EnumDyeColor.ORANGE);

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		proxy.preInit(e);

		// Specifically for this mod
		ForgeRegistries.ITEMS.registerAll(ObjectHandler.ITEMS.toArray(new Item[0]));
		ForgeRegistries.BLOCKS.registerAll(ObjectHandler.BLOCKS.toArray(new Block[0]));

		if (XenlibConfigManager.oreGen.copperGeneration)
			OreGenerationHandler.addOre(ObjectHandler.ORE_COPPER.getDefaultState(), 15, 75, 2, 8, 10);

		OreGenerationHandler.generateOres();
		OreDictionaryHandler.registerOreDict();
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		proxy.init(e);
		ConfigManager.sync(References.MODID, Type.INSTANCE);
		FMLInterModComms.sendMessage(References.WAILA, "register", "mce.lu.api.waila.WailaDataProvider.register");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
	}
}
