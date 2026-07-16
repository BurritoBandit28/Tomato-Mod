package io.github.burritobandit28.tomato.block;


import io.github.burritobandit28.tomato.entities.EntityRegister;
import io.github.burritobandit28.tomato.entities.TomatoGolemEntity;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.*;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.BlockPatternBuilder;
import net.minecraft.block.pattern.CachedBlockPosition;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.predicate.block.BlockStatePredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class CarvedTomatoBlock extends Block{


    @Nullable
    private BlockPattern tomatoGolemDispenserPattern;
    @Nullable
    private BlockPattern tomatoGolemPattern;


    public static final EnumProperty<Direction> FACING;

    public CarvedTomatoBlock(Settings settings) {
        super(settings);


        this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(FACING, Direction.NORTH));

    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{FACING});
    }

    @Override
    protected void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if (!oldState.isOf(state.getBlock())) {
            this.trySpawnEntity(world, pos);
        }
    }

    public boolean canDispense(WorldView world, BlockPos pos) {
        return this.getTomatoGolemDispenserPattern().searchAround(world, pos) != null ;
    }

    private void trySpawnEntity(World world, BlockPos pos) {
        BlockPattern.Result result = this.getTomatoGolemPattern().searchAround(world, pos);
        if (result != null) {
            TomatoGolemEntity tomatoGolemEntity = EntityRegister.TOMATO_GOLEM_ENTITY_TYPE.create(world);
            if (tomatoGolemEntity != null) {
                spawnEntity(world, result, tomatoGolemEntity, result.translate(0, 1, 0).getBlockPos());
            }
        }
    }

    private static void spawnEntity(World world, BlockPattern.Result patternResult, Entity entity, BlockPos pos) {
        breakPatternBlocks(world, patternResult);
        entity.refreshPositionAndAngles(pos.getX() + 0.5, pos.getY() + 0.05, pos.getZ() + 0.5, 0.0F, 0.0F);
        world.spawnEntity(entity);

        for (ServerPlayerEntity serverPlayerEntity : world.getNonSpectatingEntities(ServerPlayerEntity.class, entity.getBoundingBox().expand(5.0))) {
            Criteria.SUMMONED_ENTITY.trigger(serverPlayerEntity, entity);
        }

        updatePatternBlocks(world, patternResult);
    }

    public static void breakPatternBlocks(World world, BlockPattern.Result patternResult) {
        for (int i = 0; i < patternResult.getWidth(); i++) {
            for (int j = 0; j < patternResult.getHeight(); j++) {
                CachedBlockPosition cachedBlockPosition = patternResult.translate(i, j, 0);
                world.setBlockState(cachedBlockPosition.getBlockPos(), Blocks.AIR.getDefaultState(), Block.NOTIFY_LISTENERS);
                world.syncWorldEvent(WorldEvents.BLOCK_BROKEN, cachedBlockPosition.getBlockPos(), Block.getRawIdFromState(cachedBlockPosition.getBlockState()));
            }
        }
    }

    public static void updatePatternBlocks(World world, BlockPattern.Result patternResult) {
        for (int i = 0; i < patternResult.getWidth(); i++) {
            for (int j = 0; j < patternResult.getHeight(); j++) {
                CachedBlockPosition cachedBlockPosition = patternResult.translate(i, j, 0);
                world.updateNeighbors(cachedBlockPosition.getBlockPos(), Blocks.AIR);
            }
        }
    }

    private BlockPattern getTomatoGolemDispenserPattern() {
        if (this.tomatoGolemDispenserPattern == null) {
            this.tomatoGolemDispenserPattern = BlockPatternBuilder.start()
                    .aisle(" ", "#")
                    .where('#', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(Blocks.HAY_BLOCK)))
                    .build();
        }

        return this.tomatoGolemDispenserPattern;
    }

    private BlockPattern getTomatoGolemPattern() {
        if (this.tomatoGolemPattern == null) {
            this.tomatoGolemPattern = BlockPatternBuilder.start()
                    .aisle("^", "#")
                    .where('^', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(BlockRegister.CARVED_TOMATO_BLOCK)))
                    .where('#', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(Blocks.HAY_BLOCK)))
                    .build();
        }

        return this.tomatoGolemPattern;
    }


    static {
        FACING = HorizontalFacingBlock.FACING;
    }

}
