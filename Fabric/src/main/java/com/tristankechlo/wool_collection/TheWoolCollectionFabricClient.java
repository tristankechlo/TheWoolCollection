package com.tristankechlo.wool_collection;

import com.tristankechlo.wool_collection.client.WeavingStationScreen;
import com.tristankechlo.wool_collection.init.ModRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.RenderType;

public class TheWoolCollectionFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        MenuScreens.register(ModRegistry.WEAVING_STATION_CONTAINER.get(), WeavingStationScreen::new);
        BlockRenderLayerMap.INSTANCE.putBlock(ModRegistry.WEAVING_STATION_BLOCK.get(), RenderType.cutout());
    }

}
