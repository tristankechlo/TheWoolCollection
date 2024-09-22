package com.tristankechlo.wool_collection.platform;

import com.tristankechlo.wool_collection.container.WeavingStationContainer;
import com.tristankechlo.wool_collection.platform.IPlatformHelper;
import com.tristankechlo.wool_collection.recipe.WeavingStationRecipe;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.crafting.RecipeSerializer;

import java.nio.file.Path;
import java.util.function.Supplier;

public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public Path getConfigDirectory() {
        return FabricLoader.getInstance().getConfigDir();
    }

    @Override
    public Supplier<MenuType<WeavingStationContainer>> buildContainer() {
        return () -> new MenuType<>(WeavingStationContainer::new);
    }

    @Override
    public Supplier<RecipeSerializer<WeavingStationRecipe>> buildRecipeSerializer() {
        return FabricWeavingStationRecipeSerializer::new;
    }

}
