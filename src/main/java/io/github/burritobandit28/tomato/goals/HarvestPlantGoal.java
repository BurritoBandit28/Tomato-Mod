package io.github.burritobandit28.tomato.goals;

import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class HarvestPlantGoal extends MoveToTargetPosGoal {


    public HarvestPlantGoal(PathAwareEntity mob, int range) {
        super(mob, 1, range, 3);
    }

    //@Override
    //public boolean shouldContinue() {
    //    return this.tryingTime >= -this.safeWaitingTime && this.tryingTime <= 1200;
    //}


    @Override
    protected boolean isTargetPos(WorldView world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        if (state.getBlock() instanceof CropBlock plant) {
            return plant.getAge(state) >= plant.getMaxAge();
        }
        return false;
    }


    @Override
    public void tick() {
        super.tick();

        World world = this.mob.getWorld();
        //BlockPos currentblockPos = this.tweakToProperPos(this.mob.getBlockPos(), world);
        BlockPos currentblockPos = this.mob.getBlockPos();
        if (blockPosEquals(currentblockPos, this.targetPos) || blockPosEquals(currentblockPos.up(), this.targetPos) ) {
            harvest(this.targetPos, world);
        }
    }

    private void harvest(BlockPos pos, World world) {
        if (world.getBlockState(pos).getBlock() instanceof CropBlock plant) {
            world.breakBlock(pos, true);
            world.setBlockState(pos, plant.getDefaultState());
        }
    }

    // having a hard time comparing blockPos
    private boolean blockPosEquals(BlockPos pos1, BlockPos pos2) {
        return (pos1.getX() == pos2.getX()) && (pos1.getY() == pos2.getY()) && (pos1.getZ() == pos2.getZ());
    }

}
