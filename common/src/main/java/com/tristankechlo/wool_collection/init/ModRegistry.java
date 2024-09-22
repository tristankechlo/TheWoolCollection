package com.tristankechlo.wool_collection.init;

import com.tristankechlo.wool_collection.TheWoolCollection;
import com.tristankechlo.wool_collection.blocks.WeavingStationBlock;
import com.tristankechlo.wool_collection.container.WeavingStationContainer;
import com.tristankechlo.wool_collection.platform.IPlatformHelper;
import com.tristankechlo.wool_collection.platform.RegistrationProvider;
import com.tristankechlo.wool_collection.platform.RegistryObject;
import com.tristankechlo.wool_collection.recipe.WeavingStationRecipe;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public final class ModRegistry {

    public static void load() {}

    /* REGISTRIES */
    public static final RegistrationProvider<Block> BLOCKS = RegistrationProvider.get(Registry.BLOCK, TheWoolCollection.MOD_ID);
    public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(Registry.ITEM, TheWoolCollection.MOD_ID);
    public static final RegistrationProvider<MenuType<?>> CONTAINERS = RegistrationProvider.get(Registry.MENU, TheWoolCollection.MOD_ID);
    public static final RegistrationProvider<RecipeSerializer<?>> RECIPE_SERIALIZERS = RegistrationProvider.get(Registry.RECIPE_SERIALIZER, TheWoolCollection.MOD_ID);

    /* Content */
    public static final RegistryObject<Block> WEAVING_STATION_BLOCK = BLOCKS.register("weaving_station", WeavingStationBlock::new);
    public static final RegistryObject<BlockItem> WEAVING_STATION_ITEM = ITEMS.register("weaving_station", () -> new BlockItem(WEAVING_STATION_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_SEARCH)));
    public static final RegistryObject<MenuType<WeavingStationContainer>> WEAVING_STATION_CONTAINER = CONTAINERS.register("weaving_station", IPlatformHelper.INSTANCE.buildContainer());
    public static final Supplier<RecipeType<WeavingStationRecipe>> WEAVING_STATION_RECIPE_TYPE = registerRecipeType("weaving_station");
    public static final RegistryObject<RecipeSerializer<WeavingStationRecipe>> WEAVING_STATION_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register("weaving_station", IPlatformHelper.INSTANCE.buildRecipeSerializer());

    private static <T extends Recipe<?>> Supplier<RecipeType<T>> registerRecipeType(String id) {
        RecipeType<T> type = Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(TheWoolCollection.MOD_ID, id), new RecipeType<T>() {
            public String toString() {
                return id;
            }
        });
        return () -> type;
    }
}
