package com.clarkster.originsorbrework.item;

import com.clarkster.originsorbrework.OriginsOrbRework;
import com.clarkster.originsorbrework.item.custom.OrbOfRebirth;
import com.clarkster.originsorbrework.item.custom.OrbOfVocation;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;

public class ModItems {

    public static final Item ORB_OF_REBIRTH = registerItem("orb_of_rebirth",
            new OrbOfRebirth(new FabricItemSettings().maxCount(1)));

    public static final Item ORB_OF_VOCATION = registerItem("orb_of_vocation",
            new OrbOfVocation(new FabricItemSettings().maxCount(1)));

    // Adds items to the "Tools & Utilities" item group.
    private static void addItemsToToolsItemGroup(FabricItemGroupEntries entries){
        entries.add(ORB_OF_REBIRTH);
        entries.add(ORB_OF_VOCATION);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(OriginsOrbRework.MOD_ID, name), item);
    }

    public static void registerModItems() {
        OriginsOrbRework.LOGGER.info("Registering Mod Items for " + OriginsOrbRework.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(ModItems::addItemsToToolsItemGroup);
    }
}
