package com.tristankechlo.wool_collection.blocks;

import com.tristankechlo.wool_collection.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
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

    public WoolButtonBlock(DyeColor color) {
        super(ARROW_TRIGGERED, get(color));
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
        BlockItem item = ModBlocks.BUTTONS.get(color);
        return (item == null) ? Optional.empty() : Optional.of(item.getBlock());
    }

    @Override
    public BlockState copyBlockState(BlockState newState, BlockState oldState) {
        return newState.setValue(POWERED, oldState.getValue(POWERED))
                .setValue(FACE, oldState.getValue(FACE))
                .setValue(FACING, oldState.getValue(FACING));
    }

    private static Properties get(DyeColor color) {
        return Properties.of(Material.DECORATION, color).noCollission().strength(0.5F).sound(SoundType.WOOL);
    }

    @Override
    protected SoundEvent getSound(boolean b) {
        return b ? SoundEvents.WOODEN_BUTTON_CLICK_ON : SoundEvents.WOODEN_BUTTON_CLICK_OFF;
    }
}
