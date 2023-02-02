package com.tristankechlo.more_wool_blocks.blocks;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class WoolFenceGateBlock extends FenceGateBlock {

    public WoolFenceGateBlock(MaterialColor color) {
        super(BlockBehaviour.Properties.of(Material.WOOL, color).strength(0.8F).sound(SoundType.WOOL), SoundEvents.FENCE_GATE_CLOSE, SoundEvents.FENCE_GATE_OPEN);
    }

}
