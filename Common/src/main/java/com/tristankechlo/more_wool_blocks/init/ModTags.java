package com.tristankechlo.more_wool_blocks.init;

import com.tristankechlo.more_wool_blocks.MoreWoolBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public final class ModTags {

    public static final TagKey<Block> WOOL_FENCES = getTagKey("wool_fences");
    public static final TagKey<Block> WOOL_FENCE_GATES = getTagKey("wool_fence_gates");
    public static final TagKey<Block> WOOL_STAIRS = getTagKey("wool_stairs");
    public static final TagKey<Block> WOOL_SLABS = getTagKey("wool_slabs");
    public static final TagKey<Block> WOOL_WALLS = getTagKey("wool_walls");

    private static TagKey<Block> getTagKey(String id) {
        return TagKey.create(Registries.BLOCK, new ResourceLocation(MoreWoolBlocks.MOD_ID, id));
    }

}