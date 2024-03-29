package com.tristankechlo.wool_collection.blocks;

import com.tristankechlo.wool_collection.TheWoolCollection;
import com.tristankechlo.wool_collection.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Optional;

public class WoolFenceGateBlock extends FenceGateBlock implements CustomWoolBlock {

    public WoolFenceGateBlock(DyeColor color) {
        super(TheWoolCollection.WOOD_TYPE_WOOL, Properties.of().mapColor(color).forceSolidOn().strength(0.8F).sound(SoundType.WOOL).ignitedByLava());
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
        BlockItem item = ModBlocks.FENCE_GATES.get(color);
        return (item == null) ? Optional.empty() : Optional.of(item.getBlock());
    }

    @Override
    public BlockState copyBlockState(BlockState newState, BlockState oldState) {
        return newState.setValue(OPEN, oldState.getValue(OPEN))
                .setValue(POWERED, oldState.getValue(POWERED))
                .setValue(IN_WALL, oldState.getValue(IN_WALL))
                .setValue(FACING, oldState.getValue(FACING));
    }

}
