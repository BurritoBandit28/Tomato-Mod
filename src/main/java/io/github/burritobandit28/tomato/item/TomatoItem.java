package io.github.burritobandit28.tomato.item;

import eu.pb4.polymer.core.api.item.SimplePolymerItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;

public class TomatoItem extends SimplePolymerItem implements ProjectileItem {

    public TomatoItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (user.isSneaking()) {
            return super.use(world,user,hand);
        }
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound((Entity)null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        if (world instanceof ServerWorld serverWorld) {
            SnowballEntity sb = ProjectileEntity.spawnWithVelocity(SnowballEntity::new, serverWorld, itemStack, user, 0.0F, 1.5F, 1.0F);
            sb.setItem(ItemRegister.tomato.getDefaultStack());
        }
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        itemStack.decrementUnlessCreative(1, user);
        return ActionResult.SUCCESS;
    }

    @Override
    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
        SnowballEntity sb = new SnowballEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack);
        sb.setItem(ItemRegister.tomato.getDefaultStack());
        return sb;
    }
}
