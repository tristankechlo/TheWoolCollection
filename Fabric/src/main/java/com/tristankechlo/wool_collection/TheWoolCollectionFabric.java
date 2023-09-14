package com.tristankechlo.wool_collection;

import com.tristankechlo.wool_collection.init.ModBlocks;
import com.tristankechlo.wool_collection.init.ModRegistry;
import com.tristankechlo.wool_collection.platform.FabricItemGroup;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.core.Registry;

public class TheWoolCollectionFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        FabricItemGroup.register();
        ModRegistry.load();

        //register all blocks and mark as flammable
        ModBlocks.ALL_BLOCKS.forEach((id, block) -> {
            Registry.register(Registry.BLOCK, id, block);
            FlammableBlockRegistry.getDefaultInstance().add(block, 30, 60);
        });

        //register all items
        ModBlocks.ALL_ITEMS.forEach((id, item) -> {
            Registry.register(Registry.ITEM, id, item);
        });
    }

}
