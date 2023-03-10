package com.tristankechlo.wool_collection;

import com.tristankechlo.wool_collection.init.ModBlocks;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

@Mod(TheWoolCollection.MOD_ID)
public class TheWoolCollectionForge {

    public TheWoolCollectionForge() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::onRegister);
        modEventBus.addListener(this::onCommonSetup);
        modEventBus.addListener(this::onCreativeModeTabRegister);
    }

    /* register all blocks and items */
    private void onRegister(final RegisterEvent event) {
        if (event.getRegistryKey() == ForgeRegistries.Keys.BLOCKS) {
            ModBlocks.ALL_BLOCKS.forEach((id, block) -> event.register(ForgeRegistries.Keys.BLOCKS, id, () -> block));
        }
        if (event.getRegistryKey() == ForgeRegistries.Keys.ITEMS) {
            ModBlocks.ALL_ITEMS.forEach((id, item) -> event.register(ForgeRegistries.Keys.ITEMS, id, () -> item));
        }
    }

    /* add items to their creative tab */
    private void onCreativeModeTabRegister(CreativeModeTabEvent.BuildContents event) {
        if (event.getTab() == CreativeModeTabs.COLORED_BLOCKS) {
            TheWoolCollection.sortedListByColor(ModBlocks.FENCES).forEach(event::accept);
            TheWoolCollection.sortedListByColor(ModBlocks.FENCE_GATES).forEach(event::accept);
            TheWoolCollection.sortedListByColor(ModBlocks.STAIRS).forEach(event::accept);
            TheWoolCollection.sortedListByColor(ModBlocks.SLABS).forEach(event::accept);
            TheWoolCollection.sortedListByColor(ModBlocks.WALLS).forEach(event::accept);
        }
        if (event.getTab() == CreativeModeTabs.REDSTONE_BLOCKS) {
            TheWoolCollection.sortedListByColor(ModBlocks.BUTTONS).forEach(event::accept);
            TheWoolCollection.sortedListByColor(ModBlocks.PRESSURE_PLATES).forEach(event::accept);
        }
    }

    /* mark all blocks as flammable */
    private void onCommonSetup(FMLCommonSetupEvent event) {
        ModBlocks.ALL_BLOCKS.forEach((id, block) -> {
            FireBlock fireBlock = (FireBlock) Blocks.FIRE;
            fireBlock.setFlammable(block, 30, 60);
        });
    }

}