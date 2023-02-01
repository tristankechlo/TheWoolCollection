package com.tristankechlo.more_wool_blocks;

import com.tristankechlo.more_wool_blocks.init.ModRegistry;
import net.fabricmc.api.ModInitializer;

public class FabricMoreWoolBlocks implements ModInitializer {

    @Override
    public void onInitialize() {
        ModRegistry.loadClass(); // load ModRegistry to register everything
    }

}
