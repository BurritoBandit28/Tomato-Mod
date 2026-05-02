package io.github.burritobandit28.tomato.client;

import net.minecraft.block.IceBlock;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.server.network.ServerPlayerEntity;

public class TomatoSplatEffectParticle extends SimpleParticleType {
    public TomatoSplatEffectParticle(boolean alwaysShow) {
        super(alwaysShow);
    }
}
