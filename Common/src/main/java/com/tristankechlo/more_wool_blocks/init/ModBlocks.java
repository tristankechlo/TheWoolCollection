package com.tristankechlo.more_wool_blocks.init;

import com.tristankechlo.more_wool_blocks.MoreWoolBlocks;
import com.tristankechlo.more_wool_blocks.blocks.WoolFenceBlock;
import com.tristankechlo.more_wool_blocks.blocks.WoolFenceGateBlock;
import com.tristankechlo.more_wool_blocks.registration.RegistrationProvider;
import com.tristankechlo.more_wool_blocks.registration.RegistryObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.material.MaterialColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ModBlocks {

    private static final Item.Properties ITEM_PROPERTIES = new Item.Properties();
    public static final RegistrationProvider<Block> BLOCKS = RegistrationProvider.get(BuiltInRegistries.BLOCK, MoreWoolBlocks.MOD_ID);
    public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(BuiltInRegistries.ITEM, MoreWoolBlocks.MOD_ID);
    public static final List<RegistryObject<BlockItem>> ALL_ITEMS = new ArrayList<>();

    public static void loadClass() {
        List<DyeColor> colors = Arrays.stream(DyeColor.values()).toList();
        for (DyeColor color : colors) {
            if (color.getId() >= 16) {
                MoreWoolBlocks.LOGGER.warn("Skipping color " + color.getName() + " because it's supported by this mod");
                continue;
            }
            registerFence(color.getName() + "_wool_fence", color.getMaterialColor());
            registerFenceGate(color.getName() + "_wool_fence_gate", color.getMaterialColor());
            //registerWall(color.getName() + "_wool_wall", color.getMaterialColor());
            //registerStairs(color.getName() + "_wool_stairs", color.getMaterialColor());
            //registerSlab(color.getName() + "_wool_slab", color.getMaterialColor());
        }
    }

    private static void registerFence(String id, MaterialColor color) {
        RegistryObject<Block> block = BLOCKS.register(id, () -> new WoolFenceBlock(color));
        RegistryObject<BlockItem> item = ITEMS.register(id, () -> new BlockItem(block.get(), ITEM_PROPERTIES));
        ALL_ITEMS.add(item);
    }

    private static void registerFenceGate(String id, MaterialColor color) {
        RegistryObject<Block> block = BLOCKS.register(id, () -> new WoolFenceGateBlock(color));
        RegistryObject<BlockItem> item = ITEMS.register(id, () -> new BlockItem(block.get(), ITEM_PROPERTIES));
        ALL_ITEMS.add(item);
    }

}
