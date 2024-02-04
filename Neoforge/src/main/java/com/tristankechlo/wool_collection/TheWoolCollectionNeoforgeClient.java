package com.tristankechlo.wool_collection;

import com.tristankechlo.wool_collection.client.WeavingStationScreen;
import com.tristankechlo.wool_collection.init.ModRegistry;
import net.minecraft.client.gui.screens.MenuScreens;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = TheWoolCollection.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TheWoolCollectionNeoforgeClient {

    @SubscribeEvent
    public static void init(final FMLClientSetupEvent event) {
        MenuScreens.register(ModRegistry.WEAVING_STATION_CONTAINER.get(), WeavingStationScreen::new);
    }

}
