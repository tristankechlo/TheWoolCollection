package com.tristankechlo.more_wool_blocks.init;

import com.tristankechlo.more_wool_blocks.MoreWoolBlocks;
import com.tristankechlo.more_wool_blocks.registration.RegistrationProvider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public final class ModRegistry {

    public static void loadClass() {} //this is just to load the class

    /* BLOCKS */
    public static final RegistrationProvider<Block> BLOCKS = RegistrationProvider.get(BuiltInRegistries.BLOCK, MoreWoolBlocks.MOD_ID);

    /* ITEMS */
    public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(BuiltInRegistries.ITEM, MoreWoolBlocks.MOD_ID);

}
