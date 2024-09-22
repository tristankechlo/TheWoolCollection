package com.tristankechlo.wool_collection;

import com.tristankechlo.wool_collection.init.ModBlocks;
import com.tristankechlo.wool_collection.init.ModRegistry;
import com.tristankechlo.wool_collection.platform.ForgeItemGroup;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(TheWoolCollection.MOD_ID)
public class TheWoolCollectionForge {

    public static CreativeModeTab ITEM_GROUP = new ForgeItemGroup();

    public TheWoolCollectionForge() {
        ModRegistry.load();
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::onCommonSetup);
    }

    /* mark all blocks as flammable */
    private void onCommonSetup(FMLCommonSetupEvent event) {
        FireBlock fireBlock = (FireBlock) Blocks.FIRE;
        ModBlocks.ALL_BLOCKS.forEach((id, block) -> {
            fireBlock.setFlammable(block, 30, 60);
        });
    }

    @Mod.EventBusSubscriber(modid = TheWoolCollection.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {

        /* register all blocks */
        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> event) {
            ModBlocks.ALL_BLOCKS.forEach((id, block) -> event.getRegistry().register(block));
        }

        /* register all items */
        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> event) {
            ModBlocks.ALL_ITEMS.forEach((id, item) -> event.getRegistry().register(item));
        }

    }

}