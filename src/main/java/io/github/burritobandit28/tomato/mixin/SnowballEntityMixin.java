package io.github.burritobandit28.tomato.mixin;

import io.github.burritobandit28.tomato.Tomato;
import io.github.burritobandit28.tomato.item.ItemRegister;
import io.github.burritobandit28.tomato.item.TomatoItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.SnowballItem;
import net.minecraft.server.command.DamageCommand;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.sql.SQLOutput;

@Mixin(SnowballEntity.class)
public abstract class SnowballEntityMixin extends ThrownItemEntity  {

    public SnowballEntityMixin(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at = @At("HEAD"), method = "onEntityHit" ,cancellable = true)
    private void doTomatoDamage(EntityHitResult entityHitResult, CallbackInfo ci) {

        if (this.getStack().getItem() == ItemRegister.tomato) {

            super.onEntityHit(entityHitResult);
            Entity entity = entityHitResult.getEntity();
            DamageCommand
            entity.serverDamage(Tomato.getTomatoedDamageSource(this, this.getOwner()), (float)2);
            ci.cancel();
        }


    }

    @Inject(at = @At("HEAD"), method = "onCollision")
    private void playTomatoNoise(HitResult hitResult, CallbackInfo ci) {
        World world = this.getEntityWorld();
        if (this.getStack().getItem() == ItemRegister.tomato && world instanceof ServerWorld) {
            world.playSound((Entity)null, this.getX(), this.getY(), this.getZ(), Tomato.TOMATO_SPLAT, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));

        }
    }

}
