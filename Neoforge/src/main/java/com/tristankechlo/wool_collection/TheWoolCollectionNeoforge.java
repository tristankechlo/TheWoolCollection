package com.tristankechlo.wool_collection;

import com.tristankechlo.wool_collection.init.ModBlocks;
import com.tristankechlo.wool_collection.init.ModRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

@Mod(TheWoolCollection.MOD_ID)
public class TheWoolCollectionNeoforge {

    public TheWoolCollectionNeoforge(IEventBus modEventBus) {
        BlockSetType.register(TheWoolCollection.BLOCK_SET_TYPE_WOOL);
        WoodType.register(TheWoolCollection.WOOD_TYPE_WOOL);
        ModRegistry.load();

        modEventBus.addListener(this::onRegister);
        modEventBus.addListener(this::onCommonSetup);
    }

    /* register all blocks and items */
    private void onRegister(final RegisterEvent event) {
        if (event.getRegistryKey() == Registries.BLOCK) {
            ModBlocks.ALL_BLOCKS.forEach((id, block) -> event.register(Registries.BLOCK, id, () -> block));
        }
        if (event.getRegistryKey() == Registries.ITEM) {
            ModBlocks.ALL_ITEMS.forEach((id, item) -> event.register(Registries.ITEM, id, () -> item));
        }
    }

    /* mark all blocks as flammable */
    private void onCommonSetup(FMLCommonSetupEvent event) {
        FireBlock fireBlock = (FireBlock) Blocks.FIRE;
        ModBlocks.ALL_BLOCKS.forEach((id, block) -> {
            fireBlock.setFlammable(block, 30, 60);
        });
    }

}