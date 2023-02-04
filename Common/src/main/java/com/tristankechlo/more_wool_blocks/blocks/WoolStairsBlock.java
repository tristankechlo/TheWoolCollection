package com.tristankechlo.more_wool_blocks.blocks;

import com.tristankechlo.more_wool_blocks.MoreWoolBlocks;
import com.tristankechlo.more_wool_blocks.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Optional;

public class WoolStairsBlock extends StairBlock implements CustomWoolBlock {

    public WoolStairsBlock(DyeColor color) {
        super(WOOL_MAP.get(color).defaultBlockState(), Properties.copy(WOOL_MAP.get(color)));
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        Optional<InteractionResult> optional = this.use(state, level, pos, player, hand);
        if (optional.isEmpty()) {
            return super.use(state, level, pos, player, hand, result);
        }
        return optional.get();
    }

    @Override
    public Block getNewBlock(DyeColor color) {
        if (color.getId() >= 16) {
            MoreWoolBlocks.LOGGER.warn("Invalid color for WoolStairsBlock: " + color.getName());
            return null;
        }
        return ModBlocks.STAIRS.get(color).getBlock();
    }

    @Override
    public BlockState copyBlockState(BlockState newState, BlockState oldState) {
        return newState.setValue(FACING, oldState.getValue(FACING))
                .setValue(HALF, oldState.getValue(HALF))
                .setValue(SHAPE, oldState.getValue(SHAPE))
                .setValue(WATERLOGGED, oldState.getValue(WATERLOGGED));
    }

}
