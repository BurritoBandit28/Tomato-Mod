package io.github.burritobandit28.tomato.goals;

import io.github.burritobandit28.tomato.block.SmallTomatoPlant;
import io.github.burritobandit28.tomato.entities.TomatoGolemEntity;
import net.minecraft.block.Block;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;

public class FindAndOrbitTomatoPlantGoal extends MoveToTargetPosGoal {


    private final TomatoGolemEntity golem;
    //private final int searchRange;

    public FindAndOrbitTomatoPlantGoal(TomatoGolemEntity golem, int searchRange) {
        super(golem, 1, searchRange);

        this.golem = golem;
        //this.searchRange = searchRange;

    }

    @Override
    protected boolean isTargetPos(WorldView world, BlockPos pos) {
        Block block = world.getBlockState(pos).getBlock();

        if (golem.getAnchorPlantPos() == null && block instanceof SmallTomatoPlant) {
            golem.setAnchorPlantPos(pos);
            //System.out.printf("POS set to %s\n", pos.toString());
            return true;
        }
        else {
            if (golem.getAnchorPlantPos() == null) {return false;}
        }

        return golem.getAnchorPlantPos().equals(pos);
    }


}
