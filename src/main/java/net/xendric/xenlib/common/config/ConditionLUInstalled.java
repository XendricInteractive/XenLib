package net.xendric.xenlib.common.config;

import java.util.function.BooleanSupplier;

import com.google.gson.JsonObject;

import net.minecraftforge.common.crafting.IConditionFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.fml.common.Loader;

public class ConditionLUInstalled implements IConditionFactory {
	public BooleanSupplier parse(JsonContext context, JsonObject json) {
		return () -> Loader.isModLoaded("littleutilities");
	}
}
