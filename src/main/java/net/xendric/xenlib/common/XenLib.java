package net.xendric.xenlib.common;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.xendric.xenlib.common.core.ObjectHandler;
import net.xendric.xenlib.common.core.proxy.CommonProxy;

@Mod(modid = References.MODID, name = References.NAME, version = References.VERSION, updateJSON = References.UPDATE)
public class XenLib {
	@Instance(References.MODID)
	public static XenLib instance;

	@SidedProxy(clientSide = References.CLIENT_PROXY_CLASS, serverSide = References.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;

	/** Registering a creative tab with this library mod */
	/**
	 * public static CreativeTabs tab = new CreativeTabsHelper("xenlib", false) {
	 * 
	 * @Override
	 * @SideOnly(Side.CLIENT) public ItemStack getTabIconItem() { return new
	 *                        ItemStack(ObjectHandler.WRENCH); }
	 *                        }.setLabelColor(EnumDyeColor.ORANGE);
	 */

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		proxy.preInit(e);
		OreDict.registerOreDict();

		ForgeRegistries.BLOCKS.registerAll(ObjectHandler.BLOCKS.toArray(new Block[0]));
		ForgeRegistries.ITEMS.registerAll(ObjectHandler.ITEMS.toArray(new Item[0]));
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		proxy.init(e);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
	}
}
