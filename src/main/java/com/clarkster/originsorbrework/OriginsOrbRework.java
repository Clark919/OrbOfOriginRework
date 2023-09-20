package com.clarkster.originsorbrework;
import com.clarkster.originsorbrework.item.ModItems;
import com.clarkster.originsorbrework.util.ModLootTableModifiers;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class OriginsOrbRework implements ModInitializer {
	public static final String MOD_ID = "originsorbrework";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModLootTableModifiers.modifyLootTables();
	}
}
