package io.github.burritobandit28.tomato.block;

import eu.pb4.polymer.blocks.api.BlockModelType;
import eu.pb4.polymer.blocks.api.PolymerBlockModel;
import eu.pb4.polymer.blocks.api.PolymerBlockResourceUtils;
import eu.pb4.polymer.blocks.api.PolymerTexturedBlock;
import eu.pb4.polymer.core.api.block.SimplePolymerBlock;
import io.github.burritobandit28.tomato.Tomato;
import io.github.burritobandit28.tomato.item.ItemRegister;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
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
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import xyz.nucleoid.packettweaker.PacketContext;

public class TomatoBlock extends SimplePolymerBlock implements PolymerTexturedBlock {

    private BlockState state;

    public TomatoBlock(Settings settings) {
        super(settings, Blocks.PUMPKIN);

        PolymerBlockModel model = PolymerBlockModel.of(Identifier.of(Tomato.MOD_ID, "block/tomato_block"));
        this.state = PolymerBlockResourceUtils.requestBlock(BlockModelType.FULL_BLOCK, model);

    }

    @Override
    public BlockState getPolymerBlockState(BlockState state, PacketContext context) {
        return this.state;
    }

    @Override
    public ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {


        // stolen from PumpkinBlock.java
        if (!stack.isOf(Items.SHEARS)) {
            return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
        } else if (world instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld)world;
            Direction direction = hit.getSide();
            Direction direction2 = direction.getAxis() == Direction.Axis.Y ? player.getHorizontalFacing().getOpposite() : direction;
            //                                      Swap with Tomato seeds
            generateBlockInteractLoot(serverWorld, LootTables.PUMPKIN_CARVE, state, world.getBlockEntity(pos), stack, player, (worldx, stackx) -> {
                ItemEntity itemEntity = new ItemEntity(world, (double)pos.getX() + (double)0.5F + (double)direction2.getOffsetX() * 0.65, (double)pos.getY() + 0.1, (double)pos.getZ() + (double)0.5F + (double)direction2.getOffsetZ() * 0.65, ItemRegister.tomato_mega_seeds.getDefaultStack());
                itemEntity.setVelocity(0.05 * (double)direction2.getOffsetX() + world.random.nextDouble() * 0.02, 0.05, 0.05 * (double)direction2.getOffsetZ() + world.random.nextDouble() * 0.02);
                world.spawnEntity(itemEntity);
            });
            world.playSound((Entity)null, pos, SoundEvents.BLOCK_PUMPKIN_CARVE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            world.setBlockState(pos, (BlockState) BlockRegister.CARVED_TOMATO_BLOCK.getDefaultState().with(CarvedPumpkinBlock.FACING, direction2), 11);
            stack.damage(1, player, hand.getEquipmentSlot());
            world.emitGameEvent(player, GameEvent.SHEAR, pos);
            player.incrementStat(Stats.USED.getOrCreateStat(Items.SHEARS));
            return ActionResult.SUCCESS;
        } else {
            return ActionResult.SUCCESS;
        }
    }
}
