package io.github.burritobandit28.tomato.item;

import io.github.burritobandit28.tomato.entities.ThrownGoldenTomatoEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;

public class GoldenTomatoItem extends TomatoItem{
    public GoldenTomatoItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
        return new ThrownGoldenTomatoEntity(world, pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    public void throwTomato(World world, PlayerEntity user, ItemStack itemStack) {
        ThrownGoldenTomatoEntity tomato = new ThrownGoldenTomatoEntity(world, user);
        tomato.setItem(itemStack);
        tomato.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
        world.spawnEntity(tomato);
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        itemStack.decrementUnlessCreative(1, user);
    }
}
