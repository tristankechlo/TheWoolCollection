package com.tristankechlo.wool_collection;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;

public final class TheWoolCollection {

    public static final String MOD_ID = "wool_collection";
    public static final String MOD_NAME = "The Wool Collection";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);
    public static final ResourceLocation ICON_LOCATION = new ResourceLocation(MOD_ID, "white_wool_fence");
    public static boolean JEI_LOADED = false;

    public static List<BlockItem> sortedListByColor(Map<DyeColor, BlockItem> map) {
        return map.entrySet().stream()
                .sorted(Comparator.comparingInt(e -> e.getKey().getId()))
                .map(Map.Entry::getValue)
                .toList();
    }

    public static <T> T load(Class<T> clazz) {
        final T loadedService = ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        LOGGER.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }

}
