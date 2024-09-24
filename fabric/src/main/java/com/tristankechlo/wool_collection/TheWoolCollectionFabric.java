package com.tristankechlo.wool_collection;

import com.tristankechlo.wool_collection.init.ModBlocks;
import com.tristankechlo.wool_collection.init.ModRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class TheWoolCollectionFabric implements ModInitializer {

    private static final CreativeModeTab GENERAL_TAB = FabricItemGroup.builder(new ResourceLocation(TheWoolCollection.MOD_ID, "general"))
            .icon(() -> ModRegistry.WEAVING_STATION_ITEM.get().getDefaultInstance()).build();

    @Override
    public void onInitialize() {
        BlockSetType.register(TheWoolCollection.BLOCK_SET_TYPE_WOOL);
        WoodType.register(TheWoolCollection.WOOD_TYPE_WOOL);
        ModRegistry.load();

        //register all blocks and mark as flammable
        ModBlocks.ALL_BLOCKS.forEach((id, block) -> {
            Registry.register(BuiltInRegistries.BLOCK, id, block);
            FlammableBlockRegistry.getDefaultInstance().add(block, 30, 60);
        });

        //register all items
        ModBlocks.ALL_ITEMS.forEach((id, item) -> {
            Registry.register(BuiltInRegistries.ITEM, id, item);
        });

        // add all items to creative tab
        ItemGroupEvents.modifyEntriesEvent(GENERAL_TAB).register((content) -> {
            content.accept(ModRegistry.WEAVING_STATION_ITEM.get());
            TheWoolCollection.sortedListByColor(ModBlocks.FENCES).forEach(content::accept);
            TheWoolCollection.sortedListByColor(ModBlocks.FENCE_GATES).forEach(content::accept);
            TheWoolCollection.sortedListByColor(ModBlocks.STAIRS).forEach(content::accept);
            TheWoolCollection.sortedListByColor(ModBlocks.SLABS).forEach(content::accept);
            TheWoolCollection.sortedListByColor(ModBlocks.WALLS).forEach(content::accept);
            TheWoolCollection.sortedListByColor(ModBlocks.PRESSURE_PLATES).forEach(content::accept);
            TheWoolCollection.sortedListByColor(ModBlocks.BUTTONS).forEach(content::accept);
        });
    }

}
