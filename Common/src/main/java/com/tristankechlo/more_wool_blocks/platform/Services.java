package com.tristankechlo.more_wool_blocks.platform;

import com.tristankechlo.more_wool_blocks.MoreWoolBlocks;

import java.util.ServiceLoader;

public class Services {

    public static final IPlatformHelper PLATFORM = load(IPlatformHelper.class);

    public static <T> T load(Class<T> clazz) {
        final T loadedService = ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        MoreWoolBlocks.LOGGER.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }
}