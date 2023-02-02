package com.tristankechlo.more_wool_blocks.blocks;

import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class WoolFenceBlock extends FenceBlock {

    public WoolFenceBlock(MaterialColor color) {
        super(BlockBehaviour.Properties.of(Material.WOOL, color).strength(0.8F).sound(SoundType.WOOL));
    }

}
