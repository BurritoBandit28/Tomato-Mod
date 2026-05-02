package io.github.burritobandit28.tomato.effect;

import io.github.burritobandit28.tomato.Tomato;
import io.github.burritobandit28.tomato.client.TomatoSplatEffectParticle;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;

public class SplattedEffect extends StatusEffect {

    public static final RegistryEntry<StatusEffect> SPLATTED_EFFECT = Registry.registerReference(Registries.STATUS_EFFECT, Tomato.ID("splatted"), new SplattedEffect());

    protected SplattedEffect() {
        super(StatusEffectCategory.NEUTRAL, 0xcc2c2c, Tomato.SPLATTED_EFFECT_PARTICLE);
    }

    public static void init() {}


}
