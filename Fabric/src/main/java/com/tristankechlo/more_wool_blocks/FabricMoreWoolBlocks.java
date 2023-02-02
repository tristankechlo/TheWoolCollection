package com.tristankechlo.more_wool_blocks;

import com.tristankechlo.more_wool_blocks.init.ModBlocks;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.world.item.CreativeModeTabs;

public class FabricMoreWoolBlocks implements ModInitializer {

    @Override
    public void onInitialize() {
        MoreWoolBlocks.init();

        // add all blocks to creative tab
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COLORED_BLOCKS).register((content) -> {
            ModBlocks.FENCES.forEach((color, item) -> content.accept(item.get()));
            ModBlocks.FENCE_GATES.forEach((color, item) -> content.accept(item.get()));
            ModBlocks.STAIRS.forEach((color, item) -> content.accept(item.get()));
        });

        // make all blocks flammable
        ModBlocks.ALL_ITEMS.forEach(item -> {
            FlammableBlockRegistry.getDefaultInstance().add(item.get().getBlock(), 30, 60);
        });
    }

}
