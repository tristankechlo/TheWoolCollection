package com.tristankechlo.wool_collection.blocks;

import com.google.common.collect.ImmutableMap;
import com.tristankechlo.wool_collection.TheWoolCollection;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

public interface CustomWoolBlock {

    ImmutableMap<DyeColor, Block> WOOL_MAP = ImmutableMap.<DyeColor, Block>builder()
            .put(DyeColor.WHITE, Blocks.WHITE_WOOL).put(DyeColor.ORANGE, Blocks.ORANGE_WOOL)
            .put(DyeColor.MAGENTA, Blocks.MAGENTA_WOOL).put(DyeColor.LIGHT_BLUE, Blocks.LIGHT_BLUE_WOOL)
            .put(DyeColor.YELLOW, Blocks.YELLOW_WOOL).put(DyeColor.LIME, Blocks.LIME_WOOL)
            .put(DyeColor.PINK, Blocks.PINK_WOOL).put(DyeColor.GRAY, Blocks.GRAY_WOOL)
            .put(DyeColor.LIGHT_GRAY, Blocks.LIGHT_GRAY_WOOL).put(DyeColor.CYAN, Blocks.CYAN_WOOL)
            .put(DyeColor.PURPLE, Blocks.PURPLE_WOOL).put(DyeColor.BLUE, Blocks.BLUE_WOOL)
            .put(DyeColor.BROWN, Blocks.BROWN_WOOL).put(DyeColor.GREEN, Blocks.GREEN_WOOL)
            .put(DyeColor.RED, Blocks.RED_WOOL).put(DyeColor.BLACK, Blocks.BLACK_WOOL)
            .build();

    default Optional<InteractionResult> use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        Item item = stack.getItem();

        if (stack.is(Items.SHEARS)) {
            return Optional.of(this.onSheared(state, level, pos));
        } else if (item instanceof DyeItem) {
            return Optional.ofNullable(this.onDyed(state, level, pos, stack, (DyeItem) item, player));
        }
        return Optional.empty();
    }

    default InteractionResult onSheared(BlockState state, Level level, BlockPos pos) {
        Block.dropResources(state, level, pos);
        level.destroyBlock(pos, false);
        return (InteractionResult.SUCCESS);
    }

    default InteractionResult onDyed(BlockState state, Level level, BlockPos pos, ItemStack stack, DyeItem item, Player player) {
        Block block = state.getBlock();
        String blockName = BuiltInRegistries.BLOCK.getKey(block).getPath(); //name of the block without the modid
        String blockColor = blockName.split("_wool_")[0];
        DyeColor color = item.getDyeColor();

        if (!blockColor.equals(color.getName())) { // prevent recoloring to the same color
            Optional<Block> optional = getNewBlock(color);
            if (optional.isEmpty()) {
                TheWoolCollection.LOGGER.error("Tried to repaint {} to the unsupported color {}!", blockName, color.getName());
                return InteractionResult.FAIL;
            }
            BlockState newState = copyBlockState(optional.get().defaultBlockState(), state);
            level.setBlockAndUpdate(pos, newState);
            level.scheduleTick(pos, newState.getBlock(), 5); // prevent buttons and pressure plates from being stuck in the down position
            if (!player.isCreative()) {
                stack.shrink(1);
            }
            return InteractionResult.SUCCESS;
        }
        return null;
    }

    Optional<Block> getNewBlock(DyeColor color);

    BlockState copyBlockState(BlockState newState, BlockState oldState);

}
