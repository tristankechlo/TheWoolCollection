package com.tristankechlo.more_wool_blocks.blocks;

import com.tristankechlo.more_wool_blocks.MoreWoolBlocks;
import com.tristankechlo.more_wool_blocks.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Optional;

public class WoolButtonBlock extends ButtonBlock implements CustomWoolBlock {

    private static final boolean ARROW_TRIGGERED = true;
    private static final int POWER_TIME = 30;

    public WoolButtonBlock(DyeColor color) {
        super(get(color), POWER_TIME, ARROW_TRIGGERED, SoundEvents.WOODEN_BUTTON_CLICK_OFF, SoundEvents.WOODEN_BUTTON_CLICK_ON);
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
            MoreWoolBlocks.LOGGER.warn("Invalid color for WoolFenceBlock: " + color.getName());
            return null;
        }
        return ModBlocks.BUTTONS.get(color).getBlock();
    }

    @Override
    public BlockState copyBlockState(BlockState newState, BlockState oldState) {
        return newState.setValue(POWERED, Boolean.FALSE) // reset powered state, because the active timer can't be copied
                .setValue(FACE, oldState.getValue(FACE))
                .setValue(FACING, oldState.getValue(FACING));
    }

    private static Properties get(DyeColor color) {
        return Properties.of(Material.DECORATION, color).noCollission().strength(0.5F).sound(SoundType.WOOL);
    }

}
