package com.tristankechlo.more_wool_blocks;

import com.tristankechlo.more_wool_blocks.init.ModBlocks;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MoreWoolBlocks.MOD_ID)
public class ForgeMoreWoolBlocks {

    public ForgeMoreWoolBlocks() {
        MoreWoolBlocks.init();

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::onCreativeModeTabRegister);
    }

    private void onCreativeModeTabRegister(CreativeModeTabEvent.BuildContents event) {
        if (event.getTab() == CreativeModeTabs.COLORED_BLOCKS) {
            ModBlocks.FENCES.forEach(item -> event.accept(item.get()));
            ModBlocks.FENCE_GATES.forEach(item -> event.accept(item.get()));
        }
    }

}