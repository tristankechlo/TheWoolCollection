package com.tristankechlo.wool_collection;

import com.tristankechlo.wool_collection.init.ModBlocks;
import com.tristankechlo.wool_collection.init.ModRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class TheWoolCollectionFabric implements ModInitializer {

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
    }

}
