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

@Mod(MoreWoolBlocks.MOD_ID)
public class ForgeMoreWoolBlocks {

    public ForgeMoreWoolBlocks() {
        MoreWoolBlocks.init();

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::onCreativeModeTabRegister);
        modEventBus.addListener(this::onCommonSetup);
    }

    private void onCreativeModeTabRegister(CreativeModeTabEvent.BuildContents event) {
        if (event.getTab() == CreativeModeTabs.COLORED_BLOCKS) {
            ModBlocks.ALL_ITEMS.forEach(item -> event.accept(item.get()));
        }
    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
        ModBlocks.ALL_ITEMS.forEach(item -> {
            FireBlock fireBlock = (FireBlock) Blocks.FIRE;
            fireBlock.setFlammable(item.get().getBlock(), 30, 60);
        });
    }

}