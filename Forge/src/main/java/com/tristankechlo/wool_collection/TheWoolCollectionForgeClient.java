package com.tristankechlo.wool_collection;

import com.tristankechlo.wool_collection.client.WoolProcessorScreen;
import com.tristankechlo.wool_collection.init.ModRegistry;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = TheWoolCollection.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TheWoolCollectionForgeClient {

    @SubscribeEvent
    public static void init(final FMLClientSetupEvent event) {
        MenuScreens.register(ModRegistry.WOOL_PROCESSOR_CONTAINER.get(), WoolProcessorScreen::new);
    }

}
