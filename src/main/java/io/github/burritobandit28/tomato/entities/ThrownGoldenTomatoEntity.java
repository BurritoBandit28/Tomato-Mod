package io.github.burritobandit28.tomato.entities;

import io.github.burritobandit28.tomato.Tomato;
import io.github.burritobandit28.tomato.item.ItemRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.SimpleDefaultedRegistry;
import net.minecraft.world.World;

public class ThrownGoldenTomatoEntity extends ThrownTomatoEntity{
    public ThrownGoldenTomatoEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public ThrownGoldenTomatoEntity(World world, LivingEntity owner) {
        super(tomato, owner, world);
    }

    public ThrownGoldenTomatoEntity(World world, double x, double y, double z) {
        super(golden_tomato, x, y, z, world);
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

    public static final EntityType<ThrownTomatoEntity> golden_tomato = register(
            "golden_tomato",
            EntityType.Builder.<ThrownTomatoEntity>create(ThrownGoldenTomatoEntity::new, SpawnGroup.MISC).dimensions(0.25F, 0.25F).maxTrackingRange(4).trackingTickInterval(10)
    );


    private static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> type) {
        return Registry.register(Registries.ENTITY_TYPE, Tomato.ID(id), type.build(id));
    }

    public static void init(){}

}
