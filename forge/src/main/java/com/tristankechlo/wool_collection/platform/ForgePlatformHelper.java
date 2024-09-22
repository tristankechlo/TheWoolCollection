package com.tristankechlo.wool_collection.platform;

import com.tristankechlo.wool_collection.container.WeavingStationContainer;
import com.tristankechlo.wool_collection.recipe.WeavingStationRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;
import java.util.function.Function;
import java.util.function.Supplier;

public class ForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "Forge";
    }

    @Override
    public boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    @Override
    public Path getConfigDirectory() {
        return FMLPaths.CONFIGDIR.get();
    }

    @Override
    public Block createBlock(Function<DyeColor, Block> bs, DyeColor color, ResourceLocation id) {
        Block block = bs.apply(color);
        block.setRegistryName(id);
        return block;
    }

    @Override
    public BlockItem createBlockItem(Block block, Item.Properties p, ResourceLocation id) {
        BlockItem item = new BlockItem(block, p);
        item.setRegistryName(id);
        return item;
    }

    @Override
    public Supplier<MenuType<WeavingStationContainer>> buildContainer() {
        return () -> IForgeMenuType.create(WeavingStationContainer::new);
    }

    @Override
    public Supplier<RecipeSerializer<WeavingStationRecipe>> buildRecipeSerializer() {
        return ForgeWeavingStationRecipeSerializer::new;
    }

}