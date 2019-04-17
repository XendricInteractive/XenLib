package net.xendric.xenlib.api.waila;

import java.util.List;
import java.util.Random;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional.Interface;
import net.minecraftforge.fml.common.Optional.Method;
import net.xendric.xenlib.common.References;
import net.xendric.xenlib.common.core.block.BlockBase;
import net.xendric.xenlib.common.core.block.BlockEdible;
import net.xendric.xenlib.common.core.block.BlockUsable;

@Interface(iface = "mcp.modius.waila.api.IWailaDataProvider", modid = References.WAILA)
public class WailaDataProvider implements IWailaDataProvider {
	@Method(modid = References.WAILA)
	public static void register(IWailaRegistrar reg) {
		WailaDataProvider provider = new WailaDataProvider();

		reg.registerBodyProvider(provider, BlockBase.class);
	}

	@Override
	@Method(modid = References.WAILA)
	public List<String> getWailaHead(ItemStack stack, List<String> list, IWailaDataAccessor data,
			IWailaConfigHandler config) {
		return list;
	}

	@Override
	@Method(modid = References.WAILA)
	public List<String> getWailaBody(ItemStack stack, List<String> list, IWailaDataAccessor data,
			IWailaConfigHandler config) {
		Random rand = new Random();
		int i = rand.nextInt(150);
		Block block = data.getBlock();

		// Edible and Usable Blocks
		if (block instanceof BlockEdible) {
			int meta = 9 - data.getMetadata();

			if (meta > 1)
				list.add(meta + " " + I18n.format("waila.lu.edible_block.bites"));

			else if (meta == 1) {
				if (i == 23)
					list.add(meta + " " + I18n.format("waila.lu.edible_block.bites.last.ee"));
				else
					list.add(meta + " " + I18n.format("waila.lu.edible_block.bites.last"));
			}
		}

		if (block instanceof BlockUsable) {
			int meta = 9 - data.getMetadata();

			if (meta > 1)
				list.add(meta + " " + I18n.format("waila.lu.usable_block.uses"));

			else if (meta == 1)
				list.add(meta + " " + I18n.format("waila.lu.usable_block.uses.last"));
		}

		return list;
	}

	@Override
	@Method(modid = References.WAILA)
	public List<String> getWailaTail(ItemStack stack, List<String> list, IWailaDataAccessor data,
			IWailaConfigHandler config) {
		return list;
	}

	@Override
	@Method(modid = References.MODID)
	public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world,
			BlockPos pos) {
		return tag;
	}
}
