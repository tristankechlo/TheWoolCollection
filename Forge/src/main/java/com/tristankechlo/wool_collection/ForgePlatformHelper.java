package com.tristankechlo.wool_collection;

import com.tristankechlo.wool_collection.init.ModBlocks;
import com.tristankechlo.wool_collection.platform.IPlatformHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

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
    public Block createBlock(ModBlocks.BlockSupplier bs, DyeColor color, ResourceLocation id) {
        Block block = bs.create(color);
        block.setRegistryName(id);
        return block;
    }

    @Override
    public BlockItem createBlockItem(Block block, Item.Properties p, ResourceLocation id) {
        BlockItem item = new BlockItem(block, p);
        item.setRegistryName(id);
        return item;
    }

}