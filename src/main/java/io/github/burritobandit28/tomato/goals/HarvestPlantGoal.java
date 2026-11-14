package io.github.burritobandit28.tomato.goals;

import io.github.burritobandit28.tomato.block.SmallTomatoPlant;
import io.github.burritobandit28.tomato.entities.TomatoGolemEntity;
import io.github.burritobandit28.tomato.item.ItemRegister;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

import java.util.Collections;

import static net.minecraft.block.CropBlock.AGE;

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
            System.out.println("begin harvestw");
            harvest(this.targetPos, world);
        }
    }

    private void harvest(BlockPos pos, World world) {
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();

        if (!world.isClient()) {

            if (block instanceof SmallTomatoPlant tplant) {
                System.out.println("tomato harvest");
                var stack = ItemRegister.tomato.getDefaultStack();
                stack.setCount(2);
                ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), stack);
                var blockSoundGroup = state.getSoundGroup();
                world.playSound(this.mob, pos, Registries.SOUND_EVENT.getEntry(SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES).value(), SoundCategory.BLOCKS, (blockSoundGroup.getVolume() + 1.0F) / 2.0F, blockSoundGroup.getPitch() * 0.8F);
                world.setBlockState(pos, state.with(AGE, 2));
            } else {
                if (block instanceof CropBlock plant) {
                    BlockItem seed = (BlockItem) plant.asItem();
                    world.breakBlock(pos, true);
                    if (((TomatoGolemEntity) this.mob).getInventory().containsAny(Collections.singleton(seed))) {
                        world.setBlockState(pos, plant.getDefaultState());
                        ((TomatoGolemEntity) this.mob).getInventory().removeItem(seed, 1);
                    }
                    ((TomatoGolemEntity) this.mob).setLastPlanted(seed);
                }
            }
        }
    }

    // having a hard time comparing blockPos
    private boolean blockPosEquals(BlockPos pos1, BlockPos pos2) {
        return (pos1.getX() == pos2.getX()) && (pos1.getY() == pos2.getY()) && (pos1.getZ() == pos2.getZ());
    }

}
