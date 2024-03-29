package com.tristankechlo.wool_collection.init;

import com.tristankechlo.wool_collection.TheWoolCollection;
import com.tristankechlo.wool_collection.blocks.WeavingStationBlock;
import com.tristankechlo.wool_collection.container.WeavingStationContainer;
import com.tristankechlo.wool_collection.platform.IPlatformHelper;
import com.tristankechlo.wool_collection.platform.RegistrationProvider;
import com.tristankechlo.wool_collection.platform.RegistryObject;
import com.tristankechlo.wool_collection.recipe.WeavingStationRecipe;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;

public final class ModRegistry {

    public static void load() {}

    /* REGISTRIES */
    public static final RegistrationProvider<CreativeModeTab> CREATIVE_TABS = RegistrationProvider.get(BuiltInRegistries.CREATIVE_MODE_TAB, TheWoolCollection.MOD_ID);
    public static final RegistrationProvider<Block> BLOCKS = RegistrationProvider.get(BuiltInRegistries.BLOCK, TheWoolCollection.MOD_ID);
    public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(BuiltInRegistries.ITEM, TheWoolCollection.MOD_ID);
    public static final RegistrationProvider<MenuType<?>> CONTAINERS = RegistrationProvider.get(BuiltInRegistries.MENU, TheWoolCollection.MOD_ID);
    public static final RegistrationProvider<RecipeType<?>> RECIPES = RegistrationProvider.get(BuiltInRegistries.RECIPE_TYPE, TheWoolCollection.MOD_ID);
    public static final RegistrationProvider<RecipeSerializer<?>> RECIPE_SERIALIZERS = RegistrationProvider.get(BuiltInRegistries.RECIPE_SERIALIZER, TheWoolCollection.MOD_ID);

    /* Content */
    public static final RegistryObject<CreativeModeTab> GENERAL = CREATIVE_TABS.register("general", ModRegistry::createCreativeTab);
    public static final RegistryObject<Block> WEAVING_STATION_BLOCK = BLOCKS.register("weaving_station", WeavingStationBlock::new);
    public static final RegistryObject<BlockItem> WEAVING_STATION_ITEM = ITEMS.register("weaving_station", () -> new BlockItem(WEAVING_STATION_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<MenuType<WeavingStationContainer>> WEAVING_STATION_CONTAINER = CONTAINERS.register("weaving_station", IPlatformHelper.INSTANCE.buildContainer());
    public static final RegistryObject<RecipeType<WeavingStationRecipe>> WEAVING_STATION_RECIPE_TYPE = RECIPES.register("weaving_station", () -> new RecipeType<>() {});
    public static final RegistryObject<RecipeSerializer<WeavingStationRecipe>> WEAVING_STATION_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register("weaving_station", WeavingStationRecipe.Serializer::new);

    private static CreativeModeTab createCreativeTab() {
        return IPlatformHelper.INSTANCE.getCreativeTabBuilder()
                .title(Component.translatable("itemGroup.wool_collection.general"))
                .icon(() -> WEAVING_STATION_ITEM.get().getDefaultInstance())
                .displayItems((parameters, output) -> {
                    output.accept(ModRegistry.WEAVING_STATION_ITEM.get().getDefaultInstance());
                    TheWoolCollection.sortedListByColor(ModBlocks.FENCES).forEach(stack -> output.accept(stack.getDefaultInstance()));
                    TheWoolCollection.sortedListByColor(ModBlocks.FENCE_GATES).forEach(stack -> output.accept(stack.getDefaultInstance()));
                    TheWoolCollection.sortedListByColor(ModBlocks.STAIRS).forEach(stack -> output.accept(stack.getDefaultInstance()));
                    TheWoolCollection.sortedListByColor(ModBlocks.SLABS).forEach(stack -> output.accept(stack.getDefaultInstance()));
                    TheWoolCollection.sortedListByColor(ModBlocks.WALLS).forEach(stack -> output.accept(stack.getDefaultInstance()));
                    TheWoolCollection.sortedListByColor(ModBlocks.PRESSURE_PLATES).forEach(stack -> output.accept(stack.getDefaultInstance()));
                    TheWoolCollection.sortedListByColor(ModBlocks.BUTTONS).forEach(stack -> output.accept(stack.getDefaultInstance()));
                })
                .build();
    }

}
