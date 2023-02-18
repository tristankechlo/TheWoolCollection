package com.tristankechlo.wool_collection.platform;

import com.tristankechlo.wool_collection.init.ModBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.nio.file.Path;

public interface IPlatformHelper {

    String getPlatformName();

    boolean isModLoaded(String modId);

    Path getConfigDirectory();

    default Block createBlock(ModBlocks.BlockSupplier bs, DyeColor color, ResourceLocation id) {
        return bs.create(color);
    }

    default BlockItem createBlockItem(Block block, Item.Properties p, ResourceLocation id) {
        return new BlockItem(block, p);
    }

}
