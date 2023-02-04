package com.tristankechlo.more_wool_blocks;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public final class MoreWoolBlocks {

    public static final String MOD_ID = "more_wool_blocks";
    public static final String MOD_NAME = "More Wool Blocks";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static List<BlockItem> sortedListByColor(Map<DyeColor, BlockItem> map) {
        return map.entrySet().stream()
                .sorted(Comparator.comparingInt(e -> e.getKey().getId()))
                .map(Map.Entry::getValue)
                .toList();
    }

}