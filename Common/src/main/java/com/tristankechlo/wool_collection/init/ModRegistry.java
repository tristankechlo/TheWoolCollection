package com.tristankechlo.wool_collection.init;

import com.tristankechlo.wool_collection.TheWoolCollection;
import com.tristankechlo.wool_collection.blocks.WoolProcessorBlock;
import com.tristankechlo.wool_collection.container.WoolProcessorContainer;
import com.tristankechlo.wool_collection.platform.RegistrationProvider;
import com.tristankechlo.wool_collection.platform.RegistryObject;
import com.tristankechlo.wool_collection.platform.Services;
import com.tristankechlo.wool_collection.recipe.WoolProcessorRecipe;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;

public final class ModRegistry {

    public static void load() {}

    /* REGISTRIES */
    public static final RegistrationProvider<Block> BLOCKS = RegistrationProvider.get(BuiltInRegistries.BLOCK, TheWoolCollection.MOD_ID);
    public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(BuiltInRegistries.ITEM, TheWoolCollection.MOD_ID);
    public static final RegistrationProvider<MenuType<?>> CONTAINERS = RegistrationProvider.get(BuiltInRegistries.MENU, TheWoolCollection.MOD_ID);
    public static final RegistrationProvider<RecipeType<?>> RECIPES = RegistrationProvider.get(BuiltInRegistries.RECIPE_TYPE, TheWoolCollection.MOD_ID);
    public static final RegistrationProvider<RecipeSerializer<?>> RECIPE_SERIALIZERS = RegistrationProvider.get(BuiltInRegistries.RECIPE_SERIALIZER, TheWoolCollection.MOD_ID);

    /* Content */
    public static final RegistryObject<Block> WOOL_PROCESSOR_BLOCK = BLOCKS.register("wool_processor", WoolProcessorBlock::new);
    public static final RegistryObject<Item> WOOL_PROCESSOR_ITEM = ITEMS.register("wool_processor", () -> new BlockItem(WOOL_PROCESSOR_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<MenuType<WoolProcessorContainer>> WOOL_PROCESSOR_CONTAINER = CONTAINERS.register("wool_processor", Services.PLATFORM.buildContainer());
    public static final RegistryObject<RecipeType<WoolProcessorRecipe>> WOOL_PROCESSOR_RECIPE_TYPE = RECIPES.register("wool_processor", () -> new RecipeType<>() {});
    public static final RegistryObject<RecipeSerializer<WoolProcessorRecipe>> WOOL_PROCESSOR_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register("wool_processor", WoolProcessorRecipe.Serializer::new);

}
