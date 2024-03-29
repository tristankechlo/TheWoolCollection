package com.tristankechlo.wool_collection.platform;

import com.tristankechlo.wool_collection.TheWoolCollection;
import com.tristankechlo.wool_collection.container.WeavingStationContainer;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;

import java.nio.file.Path;
import java.util.function.Supplier;

public interface IPlatformHelper {

    public static final IPlatformHelper INSTANCE = TheWoolCollection.load(IPlatformHelper.class);

    String getPlatformName();

    boolean isModLoaded(String modId);

    boolean isDevelopmentEnvironment();

    default String getEnvironmentName() {

        return isDevelopmentEnvironment() ? "development" : "production";
    }

    Path getConfigDirectory();

    Supplier<MenuType<WeavingStationContainer>> buildContainer();

    CreativeModeTab.Builder getCreativeTabBuilder();

}