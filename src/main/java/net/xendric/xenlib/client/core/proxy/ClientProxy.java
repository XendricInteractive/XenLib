package net.xendric.xenlib.client.core.proxy;

import com.google.common.collect.ImmutableList;

import net.minecraft.block.Block;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.xendric.xenlib.common.core.proxy.CommonProxy;

/**
 * Here to properly render/model edible blocks so the mod works for server.
 * (Putting this in the ModelRegister class crashes the game)
 * 
 * @author MCE626
 */
public class ClientProxy extends CommonProxy {
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);
	}

	public void init(FMLInitializationEvent e) {
		super.init(e);
	}

	/**
	 * So we can render block models that are smaller than a normal block. Used
	 * by edible blocks to render in inventory/item on ground.
	 * 
	 * @param block
	 * @param item
	 * @param name
	 */
	public static void registerItemBlockModel(Block block, ItemBlock item, String name) {
		StateMapperBase mapper = new DefaultStateMapper();
		BlockStateContainer stateContainer = block.getBlockState();
		ImmutableList<IBlockState> values = stateContainer.getValidStates();

		for (IBlockState state : values) {
			String stringProperties = mapper.getPropertyString(state.getProperties());
			registerItemModel(Item.getItemFromBlock(block), block.getMetaFromState(state), stringProperties);
		}
	}

	/**
	 * Generic item rendering.
	 * 
	 * @param item
	 * @param meta
	 * @param name
	 */
	public static void registerItemModel(Item item, int meta, String name) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), name));
	}
}
