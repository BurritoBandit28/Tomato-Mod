package io.github.burritobandit28.tomato.entities;

import io.github.burritobandit28.tomato.Tomato;
import io.github.burritobandit28.tomato.item.ItemRegister;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.world.World;

public class ThrownGoldenTomatoEntity extends ThrownTomatoEntity{
    public ThrownGoldenTomatoEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public ThrownGoldenTomatoEntity(World world, LivingEntity owner) {
        super(EntityRegister.golden_tomato, owner, world);
    }

    public ThrownGoldenTomatoEntity(World world, double x, double y, double z) {
        super(EntityRegister.golden_tomato, x, y, z, world);
    }

    @Override
    public float getDamage() {
        return 5f;
    }

    @Override
    public SimpleParticleType getParticle() {
        return Tomato.GOLDEN_TOMATO_SPLAT_PARTICLE;
    }

    @Override
    protected Item getDefaultItem() {
        return ItemRegister.golden_tomato;
    }


}
