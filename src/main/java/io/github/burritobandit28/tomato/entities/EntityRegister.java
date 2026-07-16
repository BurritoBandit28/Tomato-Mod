package io.github.burritobandit28.tomato.entities;

import io.github.burritobandit28.tomato.Tomato;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class EntityRegister {
    public static final EntityType<ThrownTomatoEntity> golden_tomato = register(
            "golden_tomato",
            EntityType.Builder.<ThrownTomatoEntity>create(ThrownGoldenTomatoEntity::new, SpawnGroup.MISC).dimensions(0.25F, 0.25F).maxTrackingRange(4).trackingTickInterval(10)
    );
    public static final EntityType<ThrownTomatoEntity> tomato = register(
            "tomato",
            EntityType.Builder.<ThrownTomatoEntity>create(ThrownTomatoEntity::new, SpawnGroup.MISC).dimensions(0.25F, 0.25F).maxTrackingRange(4).trackingTickInterval(10)
    );
    public static final EntityType<TomatoGolemEntity> TOMATO_GOLEM_ENTITY_TYPE = register(
            "tomato_golem",
            EntityType.Builder.create(TomatoGolemEntity::new, SpawnGroup.MISC).dimensions(0.75F, 1.35F).eyeHeight(1.0F)
    );

    private static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> type) {
        return Registry.register(Registries.ENTITY_TYPE, Tomato.ID(id), type.build(id));
    }

    public static void init(){
        registerAttributes();
    }

    static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(TOMATO_GOLEM_ENTITY_TYPE, TomatoGolemEntity.createTomatoGolemAttributes());
    }
}
