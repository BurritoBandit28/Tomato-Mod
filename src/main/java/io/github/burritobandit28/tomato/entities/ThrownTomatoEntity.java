package io.github.burritobandit28.tomato.entities;

import io.github.burritobandit28.tomato.Tomato;
import io.github.burritobandit28.tomato.effect.SplattedEffect;
import io.github.burritobandit28.tomato.item.ItemRegister;
import net.minecraft.entity.*;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ThrownTomatoEntity extends ThrownItemEntity {

    public ThrownTomatoEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public ThrownTomatoEntity(World world, LivingEntity owner) {
        super(EntityRegister.tomato, owner, world);
    }

    public ThrownTomatoEntity(World world, double x, double y, double z) {
        super(EntityRegister.tomato, x, y, z, world);
    }

    public ThrownTomatoEntity(EntityType<? extends ThrownItemEntity> type, double x, double y, double z, World world) {
        super(type, x, y, z, world);
    }

    public ThrownTomatoEntity(EntityType<? extends ThrownItemEntity> type, LivingEntity owner, World world) {
        super(type, owner, world);
    }

    public float getDamage() {
        return 2f;
    }

    @Override
    protected Item getDefaultItem() {
        return ItemRegister.tomato;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        if (!this.getWorld().isClient()) {
            ServerWorld world = (ServerWorld) this.getEntityWorld();
            if (entity instanceof LivingEntity livingEntity) {
                livingEntity.damage(Tomato.getTomatoDamage(world, this.getOwner()), this.getDamage());
                livingEntity.addStatusEffect(new StatusEffectInstance(SplattedEffect.SPLATTED_EFFECT, 60), this.getOwner());
            }
        }
    }
    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        World world = this.getEntityWorld();
        world.playSound((PlayerEntity) null, this.getX(), this.getY(), this.getZ(), Tomato.TOMATO_SPLAT, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!this.getWorld().isClient()) {


            Vec3d vel  = this.getVelocity();
            ServerWorld sw = (ServerWorld) this.getWorld();

            if (!(hitResult instanceof EntityHitResult)) {
                Vec3d hitPos= hitResult.getPos();
                sw.spawnParticles(this.getParticle(), hitPos.x - (Tomato.posOrNeg(vel.x) * 0.2), hitPos.y - (Tomato.posOrNeg(vel.y) * 0.2), hitPos.z - (Tomato.posOrNeg(vel.z) * 0.2), 0, 0, -0.01, 0, 1);
            }
            this.getWorld().sendEntityStatus(this, EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES);
            this.discard();
        }

    }


    public SimpleParticleType getParticle() {
        return Tomato.TOMATO_SPLAT_PARTICLE;
    }

}