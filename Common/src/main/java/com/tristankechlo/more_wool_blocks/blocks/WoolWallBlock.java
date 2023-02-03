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
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Optional;

public class WoolWallBlock extends WallBlock implements CustomWoolBlock {

    public WoolWallBlock(DyeColor color) {
        super(Properties.of(Material.WOOL, color).strength(0.8F).sound(SoundType.WOOL));
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
            MoreWoolBlocks.LOGGER.warn("Invalid color for WoolWallBlock: " + color.getName());
            return null;
        }
        return ModBlocks.WALLS.get(color).get().getBlock();
    }

    @Override
    public BlockState copyBlockState(BlockState newState, BlockState oldState) {
        return newState.setValue(UP, oldState.getValue(UP))
                .setValue(NORTH_WALL, oldState.getValue(NORTH_WALL))
                .setValue(EAST_WALL, oldState.getValue(EAST_WALL))
                .setValue(SOUTH_WALL, oldState.getValue(SOUTH_WALL))
                .setValue(WEST_WALL, oldState.getValue(WEST_WALL))
                .setValue(WATERLOGGED, oldState.getValue(WATERLOGGED));
    }

}
