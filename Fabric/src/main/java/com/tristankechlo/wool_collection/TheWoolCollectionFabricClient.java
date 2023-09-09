package com.tristankechlo.wool_collection;

import com.tristankechlo.wool_collection.client.WoolProcessorScreen;
import com.tristankechlo.wool_collection.init.ModRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.RenderType;

public class TheWoolCollectionFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        MenuScreens.register(ModRegistry.WOOL_PROCESSOR_CONTAINER.get(), WoolProcessorScreen::new);
        BlockRenderLayerMap.INSTANCE.putBlock(ModRegistry.WOOL_PROCESSOR_BLOCK.get(), RenderType.cutout());
    }

}
