package com.tristankechlo.more_wool_blocks.blocks;

import com.google.common.collect.ImmutableMap;
import com.tristankechlo.more_wool_blocks.MoreWoolBlocks;
import com.tristankechlo.more_wool_blocks.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CrossCollisionBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Optional;

public class WoolStairsBlock extends StairBlock implements CustomWoolBlock {

    private static final ImmutableMap<DyeColor, Block> STAIRS = ImmutableMap.<DyeColor, Block>builder()
            .put(DyeColor.WHITE, Blocks.WHITE_WOOL).put(DyeColor.ORANGE, Blocks.ORANGE_WOOL)
            .put(DyeColor.MAGENTA, Blocks.MAGENTA_WOOL).put(DyeColor.LIGHT_BLUE, Blocks.LIGHT_BLUE_WOOL)
            .put(DyeColor.YELLOW, Blocks.YELLOW_WOOL).put(DyeColor.LIME, Blocks.LIME_WOOL)
            .put(DyeColor.PINK, Blocks.PINK_WOOL).put(DyeColor.GRAY, Blocks.GRAY_WOOL)
            .put(DyeColor.LIGHT_GRAY, Blocks.LIGHT_GRAY_WOOL).put(DyeColor.CYAN, Blocks.CYAN_WOOL)
            .put(DyeColor.PURPLE, Blocks.PURPLE_WOOL).put(DyeColor.BLUE, Blocks.BLUE_WOOL)
            .put(DyeColor.BROWN, Blocks.BROWN_WOOL).put(DyeColor.GREEN, Blocks.GREEN_WOOL)
            .put(DyeColor.RED, Blocks.RED_WOOL).put(DyeColor.BLACK, Blocks.BLACK_WOOL)
            .build();

    public WoolStairsBlock(DyeColor color) {
        super(STAIRS.get(color).defaultBlockState(), Properties.copy(STAIRS.get(color)));
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
        return ModBlocks.STAIRS.get(color).get().getBlock();
    }

    @Override
    public BlockState copyBlockState(BlockState newState, BlockState oldState) {
        return newState.setValue(FACING, oldState.getValue(FACING))
                .setValue(HALF, oldState.getValue(HALF))
                .setValue(SHAPE, oldState.getValue(SHAPE))
                .setValue(WATERLOGGED, oldState.getValue(WATERLOGGED));
    }

}
