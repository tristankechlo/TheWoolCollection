package com.tristankechlo.wool_collection.blocks;

import com.tristankechlo.wool_collection.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Optional;

public class WoolStairsBlock extends StairBlock implements CustomWoolBlock {

    public WoolStairsBlock(DyeColor color) {
        super(WOOL_MAP.get(color).defaultBlockState(), Properties.ofFullCopy(WOOL_MAP.get(color)));
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
    public Optional<Block> getNewBlock(DyeColor color) {
        BlockItem item = ModBlocks.STAIRS.get(color);
        return (item == null) ? Optional.empty() : Optional.of(item.getBlock());
    }

    @Override
    public BlockState copyBlockState(BlockState newState, BlockState oldState) {
        return newState.setValue(FACING, oldState.getValue(FACING))
                .setValue(HALF, oldState.getValue(HALF))
                .setValue(SHAPE, oldState.getValue(SHAPE))
                .setValue(WATERLOGGED, oldState.getValue(WATERLOGGED));
    }

}
