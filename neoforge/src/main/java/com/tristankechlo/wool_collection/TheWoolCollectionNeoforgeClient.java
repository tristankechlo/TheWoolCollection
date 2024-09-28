package com.tristankechlo.wool_collection;

import com.tristankechlo.wool_collection.client.WeavingStationScreen;
import com.tristankechlo.wool_collection.init.ModRegistry;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(modid = TheWoolCollection.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class TheWoolCollectionNeoforgeClient {

    @SubscribeEvent
    public static void init(final RegisterMenuScreensEvent event) {
        event.register(ModRegistry.WEAVING_STATION_CONTAINER.get(), WeavingStationScreen::new);
    }

}
