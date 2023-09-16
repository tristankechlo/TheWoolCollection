package com.tristankechlo.wool_collection.platform;

import com.tristankechlo.wool_collection.TheWoolCollection;
import com.tristankechlo.wool_collection.container.WeavingStationContainer;
import com.tristankechlo.wool_collection.recipe.WeavingStationRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Block;

import java.nio.file.Path;
import java.util.function.Function;
import java.util.function.Supplier;

public interface IPlatformHelper {

    public static final IPlatformHelper INSTANCE = TheWoolCollection.load(IPlatformHelper.class);

    String getPlatformName();

    boolean isModLoaded(String modId);

    Path getConfigDirectory();

    default Block createBlock(Function<DyeColor, Block> bs, DyeColor color, ResourceLocation id) {
        return bs.apply(color);
    }

    default BlockItem createBlockItem(Block block, Item.Properties p, ResourceLocation id) {
        return new BlockItem(block, p);
    }

    Supplier<MenuType<WeavingStationContainer>> buildContainer();

    Supplier<RecipeSerializer<WeavingStationRecipe>> buildRecipeSerializer();

}
