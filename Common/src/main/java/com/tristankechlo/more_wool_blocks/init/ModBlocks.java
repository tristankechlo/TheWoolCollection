package com.tristankechlo.more_wool_blocks.init;

import com.tristankechlo.more_wool_blocks.MoreWoolBlocks;
import com.tristankechlo.more_wool_blocks.blocks.*;
import com.tristankechlo.more_wool_blocks.registration.RegistrationProvider;
import com.tristankechlo.more_wool_blocks.registration.RegistryObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ModBlocks {

    private static final Item.Properties ITEM_PROPERTIES = new Item.Properties();
    public static final RegistrationProvider<Block> BLOCKS = RegistrationProvider.get(BuiltInRegistries.BLOCK, MoreWoolBlocks.MOD_ID);
    public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(BuiltInRegistries.ITEM, MoreWoolBlocks.MOD_ID);
    public static final List<RegistryObject<BlockItem>> ALL_ITEMS = new ArrayList<>();
    public static final Map<DyeColor, RegistryObject<BlockItem>> FENCES = new HashMap<>();
    public static final Map<DyeColor, RegistryObject<BlockItem>> FENCE_GATES = new HashMap<>();
    public static final Map<DyeColor, RegistryObject<BlockItem>> STAIRS = new HashMap<>();
    public static final Map<DyeColor, RegistryObject<BlockItem>> SLABS = new HashMap<>();
    public static final Map<DyeColor, RegistryObject<BlockItem>> WALLS = new HashMap<>();

    public static void loadClass() {
        final int colorCount = DyeColor.values().length;
        for (int index = 0; index < colorCount; index++) {
            DyeColor color = DyeColor.byId(index);
            if (color.getId() >= 16) {
                MoreWoolBlocks.LOGGER.warn("Skipping color " + color.getName() + " because it's not supported by this mod");
                continue;
            }
            registerFence(color.getName() + "_wool_fence", color);
            registerFenceGate(color.getName() + "_wool_fence_gate", color);
            registerStairs(color.getName() + "_wool_stairs", color);
            registerSlab(color.getName() + "_wool_slab", color);
            registerWall(color.getName() + "_wool_wall", color);
        }
    }

    private static void registerFence(String id, DyeColor color) {
        RegistryObject<Block> block = BLOCKS.register(id, () -> new WoolFenceBlock(color));
        RegistryObject<BlockItem> item = ITEMS.register(id, () -> new BlockItem(block.get(), ITEM_PROPERTIES));
        ALL_ITEMS.add(item);
        FENCES.put(color, item);
    }

    private static void registerFenceGate(String id, DyeColor color) {
        RegistryObject<Block> block = BLOCKS.register(id, () -> new WoolFenceGateBlock(color));
        RegistryObject<BlockItem> item = ITEMS.register(id, () -> new BlockItem(block.get(), ITEM_PROPERTIES));
        ALL_ITEMS.add(item);
        FENCE_GATES.put(color, item);
    }

    private static void registerStairs(String id, DyeColor color) {
        RegistryObject<Block> block = BLOCKS.register(id, () -> new WoolStairsBlock(color));
        RegistryObject<BlockItem> item = ITEMS.register(id, () -> new BlockItem(block.get(), ITEM_PROPERTIES));
        ALL_ITEMS.add(item);
        STAIRS.put(color, item);
    }

    private static void registerSlab(String id, DyeColor color) {
        RegistryObject<Block> block = BLOCKS.register(id, () -> new WoolSlabBlock(color));
        RegistryObject<BlockItem> item = ITEMS.register(id, () -> new BlockItem(block.get(), ITEM_PROPERTIES));
        ALL_ITEMS.add(item);
        SLABS.put(color, item);
    }

    private static void registerWall(String id, DyeColor color) {
        RegistryObject<Block> block = BLOCKS.register(id, () -> new WoolWallBlock(color));
        RegistryObject<BlockItem> item = ITEMS.register(id, () -> new BlockItem(block.get(), ITEM_PROPERTIES));
        ALL_ITEMS.add(item);
        WALLS.put(color, item);
    }

}
