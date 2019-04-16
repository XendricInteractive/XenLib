package net.xendric.xenlib.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.xendric.xenlib.common.core.ObjectHandler;
import net.xendric.xenlib.common.core.proxy.CommonProxy;
import net.xendric.xenlib.common.util.CreativeTabsHelper;

@Mod(modid = References.MODID, name = References.NAME, version = References.VERSION, updateJSON = References.UPDATE)
public class XenLib {
	@Instance(References.MODID)
	public static XenLib instance;

	@SidedProxy(clientSide = References.CLIENT_PROXY_CLASS, serverSide = References.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;

	public static CreativeTabs tab = ((CreativeTabsHelper) new CreativeTabsHelper("xenlib", ObjectHandler.WRENCH, false))
			.setLabelColor(EnumDyeColor.ORANGE);

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		proxy.preInit(e);
		OreDict.registerOreDict();
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		proxy.init(e);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
	}
}