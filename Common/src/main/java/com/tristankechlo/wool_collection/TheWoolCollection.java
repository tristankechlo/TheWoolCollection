package com.tristankechlo.wool_collection;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public final class TheWoolCollection {

    public static final String MOD_ID = "wool_collection";
    public static final String MOD_NAME = "The Wool Collection";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static List<BlockItem> sortedListByColor(Map<DyeColor, BlockItem> map) {
        return map.entrySet().stream()
                .sorted(Comparator.comparingInt(e -> e.getKey().getId()))
                .map(Map.Entry::getValue)
                .toList();
    }

}