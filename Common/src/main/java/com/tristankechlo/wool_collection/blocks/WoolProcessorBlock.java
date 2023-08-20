package com.tristankechlo.wool_collection.blocks;

import com.tristankechlo.wool_collection.container.WoolProcessorContainer;
import com.tristankechlo.wool_collection.init.ModRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class WoolProcessorBlock extends Block {

    private static Component CONTAINER_NAME = null;

    public WoolProcessorBlock() {
        super(BlockBehaviour.Properties.of().mapColor(MapColor.CLAY).sound(SoundType.ANVIL).strength(3.0F, 6.0F));
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (worldIn.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            player.openMenu(state.getMenuProvider(worldIn, pos));
            return InteractionResult.CONSUME;
        }
    }

    @Nullable
    @Override
    public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        return new SimpleMenuProvider((id, playerInv, player) -> {
            return new WoolProcessorContainer(id, playerInv, ContainerLevelAccess.create(level, pos));
        }, getContainerName());
    }

    public static Component getContainerName() {
        if (CONTAINER_NAME == null) {
            CONTAINER_NAME = ModRegistry.WOOL_PROCESSOR_BLOCK.get().getName();
        }
        return CONTAINER_NAME;
    }


}
