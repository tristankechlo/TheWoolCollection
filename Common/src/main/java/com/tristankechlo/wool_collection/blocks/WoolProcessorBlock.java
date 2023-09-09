package com.tristankechlo.wool_collection.blocks;

import com.tristankechlo.wool_collection.container.WoolProcessorContainer;
import com.tristankechlo.wool_collection.init.ModRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class WoolProcessorBlock extends HorizontalDirectionalBlock {

    private static final VoxelShape SHAPE_SOUTH = Shapes.or(Block.box(0, 0, 6, 16, 12, 16), Block.box(14, 0, 0, 16, 12, 6), Block.box(0, 0, 0, 2, 12, 6), Block.box(2, 0, 0, 14, 8, 9), Block.box(2, 10, 4, 14, 14, 8)).optimize();
    private static final VoxelShape SHAPE_NORTH = Shapes.or(Block.box(0, 0, 0, 16, 12, 10), Block.box(14, 0, 10, 16, 12, 16), Block.box(0, 0, 10, 2, 12, 16), Block.box(2, 0, 7, 14, 8, 16), Block.box(2, 10, 8, 14, 14, 12)).optimize();
    private static final VoxelShape SHAPE_EAST = Shapes.or(Block.box(6, 0, 0, 16, 12, 16), Block.box(0, 0, 14, 6, 12, 16), Block.box(0, 0, 0, 6, 12, 2), Block.box(0, 0, 2, 9, 8, 14), Block.box(4, 10, 2, 8, 14, 14)).optimize();
    private static final VoxelShape SHAPE_WEST = Shapes.or(Block.box(0, 0, 0, 10, 12, 16), Block.box(10, 0, 14, 16, 12, 16), Block.box(10, 0, 0, 16, 12, 2), Block.box(7, 0, 2, 16, 8, 14), Block.box(8, 10, 2, 12, 14, 14)).optimize();
    private static Component CONTAINER_NAME = null;

    public WoolProcessorBlock() {
        super(BlockBehaviour.Properties.copy(Blocks.LOOM));
        registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext ctx) {
        Direction direction = state.getValue(FACING);
        if (direction == Direction.NORTH) {
            return SHAPE_NORTH;
        } else if (direction == Direction.SOUTH) {
            return SHAPE_SOUTH;
        } else if (direction == Direction.EAST) {
            return SHAPE_EAST;
        }
        return SHAPE_WEST;
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

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        BlockState state = super.getStateForPlacement(ctx);
        return state.setValue(FACING, ctx.getHorizontalDirection());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }
}
