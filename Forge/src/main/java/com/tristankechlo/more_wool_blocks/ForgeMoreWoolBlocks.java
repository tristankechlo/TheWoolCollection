package com.tristankechlo.more_wool_blocks;

import com.tristankechlo.more_wool_blocks.init.ModBlocks;
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

@Mod(MoreWoolBlocks.MOD_ID)
public class ForgeMoreWoolBlocks {

    public ForgeMoreWoolBlocks() {
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
            ModBlocks.FENCES.forEach((color, item) -> event.accept(item));
            ModBlocks.FENCE_GATES.forEach((color, item) -> event.accept(item));
            ModBlocks.STAIRS.forEach((color, item) -> event.accept(item));
            ModBlocks.SLABS.forEach((color, item) -> event.accept(item));
            ModBlocks.WALLS.forEach((color, item) -> event.accept(item));
        }
        if (event.getTab() == CreativeModeTabs.REDSTONE_BLOCKS) {
            ModBlocks.BUTTONS.forEach((color, item) -> event.accept(item));
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