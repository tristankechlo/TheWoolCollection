package com.tristankechlo.more_wool_blocks.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

public interface CustomWoolBlock {

    default Optional<InteractionResult> use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        Item item = stack.getItem();

        if (level.isClientSide) {
            boolean flag = stack.is(Items.SHEARS) || (item instanceof DyeItem);
            return flag ? Optional.of(InteractionResult.SUCCESS) : Optional.empty();
        }
        if (stack.is(Items.SHEARS)) {
            return Optional.of(this.onSheared(state, level, pos));
        } else if (item instanceof DyeItem) {
            return Optional.of(this.onDyed(state, level, pos, (DyeItem) item, player));
        }
        return Optional.empty();
    }

    default InteractionResult onSheared(BlockState state, Level level, BlockPos pos) {
        Block.dropResources(state, level, pos);
        level.destroyBlock(pos, false);
        return (InteractionResult.SUCCESS);
    }

    default InteractionResult onDyed(BlockState state, Level level, BlockPos pos, DyeItem item, Player player) {
        Block block = state.getBlock();
        String name = BuiltInRegistries.BLOCK.getKey(block).getPath();

        if (!name.contains(item.getDyeColor().getName())) {
            Block newBlock = getNewBlock(item.getDyeColor());
            if (newBlock == null) {
                return InteractionResult.FAIL;
            }
            BlockState newState = copyBlockState(newBlock.defaultBlockState(), state);
            level.setBlockAndUpdate(pos, newState);
            if (!player.isCreative()) { //use up dye
                ItemStack stack = player.getItemInHand(InteractionHand.MAIN_HAND);
                stack.shrink(1);
            }
        }
        return InteractionResult.SUCCESS;
    }

    Block getNewBlock(DyeColor color);

    BlockState copyBlockState(BlockState newState, BlockState oldState);

}
