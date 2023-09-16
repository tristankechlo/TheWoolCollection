package com.tristankechlo.wool_collection.init;

import com.tristankechlo.wool_collection.TheWoolCollection;
import com.tristankechlo.wool_collection.blocks.*;
import com.tristankechlo.wool_collection.platform.IPlatformHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public final class ModBlocks {

    private static final Item.Properties ITEM_PROPERTIES = new Item.Properties().tab(CreativeModeTab.TAB_SEARCH);
    public static final Map<ResourceLocation, Block> ALL_BLOCKS = new HashMap<>();
    public static final Map<ResourceLocation, BlockItem> ALL_ITEMS = new HashMap<>();
    public static final Map<DyeColor, BlockItem> FENCES = new HashMap<>();
    public static final Map<DyeColor, BlockItem> FENCE_GATES = new HashMap<>();
    public static final Map<DyeColor, BlockItem> STAIRS = new HashMap<>();
    public static final Map<DyeColor, BlockItem> SLABS = new HashMap<>();
    public static final Map<DyeColor, BlockItem> WALLS = new HashMap<>();
    public static final Map<DyeColor, BlockItem> BUTTONS = new HashMap<>();
    public static final Map<DyeColor, BlockItem> PRESSURE_PLATES = new HashMap<>();

    static {
        final int colorCount = DyeColor.values().length;
        for (int index = 0; index < colorCount; index++) {
            DyeColor color = DyeColor.byId(index);
            if (color.getId() >= 16) {
                TheWoolCollection.LOGGER.warn("Skipping color " + color.getName() + " because it's not supported by this mod");
                continue;
            }
            register(color.getName() + "_wool_fence", color, WoolFenceBlock::new, FENCES);
            register(color.getName() + "_wool_fence_gate", color, WoolFenceGateBlock::new, FENCE_GATES);
            register(color.getName() + "_wool_stairs", color, WoolStairsBlock::new, STAIRS);
            register(color.getName() + "_wool_slab", color, WoolSlabBlock::new, SLABS);
            register(color.getName() + "_wool_wall", color, WoolWallBlock::new, WALLS);
            register(color.getName() + "_wool_button", color, WoolButtonBlock::new, BUTTONS);
            register(color.getName() + "_wool_pressure_plate", color, WoolPressurePlateBlock::new, PRESSURE_PLATES);
        }
    }

    private static void register(String id, DyeColor color, Function<DyeColor, Block> bs, Map<DyeColor, BlockItem> category) {
        ResourceLocation rl = new ResourceLocation(TheWoolCollection.MOD_ID, id);
        Block block = IPlatformHelper.INSTANCE.createBlock(bs, color, rl);
        BlockItem item = IPlatformHelper.INSTANCE.createBlockItem(block, ITEM_PROPERTIES, rl);
        ALL_ITEMS.put(rl, item);
        ALL_BLOCKS.put(rl, block);
        category.put(color, item);
    }

}
