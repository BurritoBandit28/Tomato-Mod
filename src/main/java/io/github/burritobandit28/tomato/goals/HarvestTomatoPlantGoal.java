package io.github.burritobandit28.tomato.goals;

import io.github.burritobandit28.tomato.block.BlockRegister;
import io.github.burritobandit28.tomato.block.SmallTomatoPlant;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class HarvestTomatoPlantGoal extends MoveToTargetPosGoal {


    public HarvestTomatoPlantGoal(PathAwareEntity mob, int range) {
        super(mob, 1, range);
    }


    @Override
    public boolean canStart() {
        return this.findTargetPos();
    }

    @Override
    protected boolean isTargetPos(WorldView world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        if (state.getBlock() instanceof CropBlock plant) {
            return state.get(CropBlock.AGE) >= plant.getMaxAge();
        }
        return false;
    }

    @Override
    public void tick() {
        super.tick();

        World world = this.mob.getWorld();
        BlockPos currentblockPos = this.tweakToProperPos(this.mob.getBlockPos(), world);
        System.out.println(this.targetPos);
        if (currentblockPos != null && currentblockPos.equals(this.targetPos)) {
            System.out.println("BREAK BLOCK NOW");
            harvest(currentblockPos, world);
        }
    }

    private void harvest(BlockPos pos, World world) {
        if (world.getBlockState(pos).getBlock() instanceof CropBlock plant) {
            world.breakBlock(pos, true);
            world.setBlockState(pos, plant.getDefaultState());
        }
    }

    // stolen from StepAndDestroyBlockGoal
    private BlockPos tweakToProperPos(BlockPos pos, BlockView world) {
        if (world.getBlockState(pos).getBlock() instanceof CropBlock) {
            return pos;
        } else {
            BlockPos[] blockPoss = new BlockPos[]{pos.down(), pos.west(), pos.east(), pos.north(), pos.south(), pos.down().down()};

            for(BlockPos blockPos : blockPoss) {
                if (world.getBlockState(pos).getBlock() instanceof CropBlock) {
                    return blockPos;
                }
            }

            return null;
        }
    }

}
