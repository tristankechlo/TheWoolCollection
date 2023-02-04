package com.tristankechlo.more_wool_blocks;

import com.tristankechlo.more_wool_blocks.init.ModBlocks;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTabs;

public class FabricMoreWoolBlocks implements ModInitializer {

    @Override
    public void onInitialize() {
        //register all blocks and mark as flammable
        ModBlocks.ALL_BLOCKS.forEach((id, block) -> {
            Registry.register(BuiltInRegistries.BLOCK, id, block);
            FlammableBlockRegistry.getDefaultInstance().add(block, 30, 60);
        });

        //register all items
        ModBlocks.ALL_ITEMS.forEach((id, item) -> {
            Registry.register(BuiltInRegistries.ITEM, id, item);
        });

        // add all items to creative tab for colored blocks
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COLORED_BLOCKS).register((content) -> {
            ModBlocks.FENCES.forEach((color, item) -> content.accept(item));
            ModBlocks.FENCE_GATES.forEach((color, item) -> content.accept(item));
            ModBlocks.STAIRS.forEach((color, item) -> content.accept(item));
            ModBlocks.SLABS.forEach((color, item) -> content.accept(item));
            ModBlocks.WALLS.forEach((color, item) -> content.accept(item));
        });
        // add all buttons to redstone creative tab
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.REDSTONE_BLOCKS).register((content) -> {
            ModBlocks.BUTTONS.forEach((color, item) -> content.accept(item));
        });
    }

}
