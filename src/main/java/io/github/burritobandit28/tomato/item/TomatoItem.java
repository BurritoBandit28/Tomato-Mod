package io.github.burritobandit28.tomato.item;

import io.github.burritobandit28.tomato.entities.ThrownTomatoEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ProjectileItem;
import net.minecraft.item.SnowballItem;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;

public class TomatoItem extends Item implements ProjectileItem {

    public TomatoItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (user.isSneaking()) {
            return super.use(world,user,hand);
        }
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound((PlayerEntity) null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        if (world instanceof ServerWorld serverWorld) {
            world.playSound(
                    null,
                    user.getX(),
                    user.getY(),
                    user.getZ(),
                    SoundEvents.ENTITY_SNOWBALL_THROW,
                    SoundCategory.NEUTRAL,
                    0.5F,
                    0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F)
            );
            if (!world.isClient) {
                ThrownTomatoEntity tomato = new ThrownTomatoEntity(world, user);
                tomato.setItem(itemStack);
                tomato.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
                world.spawnEntity(tomato);
                user.incrementStat(Stats.USED.getOrCreateStat(this));
                itemStack.decrementUnlessCreative(1, user);
                return TypedActionResult.success(itemStack);
            }

        }
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        itemStack.decrementUnlessCreative(1, user);
        return TypedActionResult.pass(itemStack);
    }

    @Override
    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
        return new ThrownTomatoEntity(world, pos.getX(), pos.getY(), pos.getZ());
    }


}
