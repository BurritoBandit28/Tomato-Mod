package io.github.burritobandit28.tomato.block;


import com.mojang.serialization.MapCodec;
import io.github.burritobandit28.tomato.Tomato;
import io.github.burritobandit28.tomato.item.ItemRegister;
import net.minecraft.block.*;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTables;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class TomatoBlock extends Block {

    private BlockState state;

    public TomatoBlock(Settings settings) {
        super(settings);

    }


    public static final MapCodec<TomatoBlock> CODEC = createCodec(TomatoBlock::new);

    @Override
    public MapCodec<TomatoBlock> getCodec() {
        return CODEC;
    }

    @Override
    public ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        // stolen from PumpkinBlock.java
        if (!stack.isOf(Items.SHEARS)) {
            return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
        } else if (world.isClient) {
            return ItemActionResult.success(world.isClient);
        } else {
            Direction direction = hit.getSide();
            Direction direction2 = direction.getAxis() == Direction.Axis.Y ? player.getHorizontalFacing().getOpposite() : direction;
            world.playSound(null, pos, SoundEvents.BLOCK_PUMPKIN_CARVE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            world.setBlockState(pos, BlockRegister.CARVED_TOMATO_BLOCK.getDefaultState().with(CarvedTomatoBlock.FACING, direction2), Block.NOTIFY_ALL_AND_REDRAW);
            ItemEntity itemEntity = new ItemEntity(
                    world,
                    pos.getX() + 0.5 + direction2.getOffsetX() * 0.65,
                    pos.getY() + 0.1,
                    pos.getZ() + 0.5 + direction2.getOffsetZ() * 0.65,
                    new ItemStack(ItemRegister.tomato_mega_seeds, 1)
            );
            itemEntity.setVelocity(
                    0.05 * direction2.getOffsetX() + world.random.nextDouble() * 0.02, 0.05, 0.05 * direction2.getOffsetZ() + world.random.nextDouble() * 0.02
            );
            world.spawnEntity(itemEntity);
            stack.damage(1, player, LivingEntity.getSlotForHand(hand));
            world.emitGameEvent(player, GameEvent.SHEAR, pos);
            player.incrementStat(Stats.USED.getOrCreateStat(Items.SHEARS));
            return ItemActionResult.success(world.isClient);
        }
    }
}

