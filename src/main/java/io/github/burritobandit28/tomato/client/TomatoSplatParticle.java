package io.github.burritobandit28.tomato.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;

public class TomatoSplatParticle extends AnimatedParticle {

    protected TomatoSplatParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteProvider spriteProvider) {
        super(world, x, y, z, spriteProvider, 0.00625F);
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.velocityZ = velocityZ;
        this.scale *= 5F;
        this.maxAge = 400 + this.random.nextInt(12);
        this.collidesWithWorld = true;
        //this.setTargetColor(15916745);
        this.setSpriteForAge(spriteProvider);
    }

    public void move(double dx, double dy, double dz) {
        this.setBoundingBox(this.getBoundingBox().offset(dx, dy, dz));
        this.repositionFromBoundingBox();
    }

    public static class Factory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(SimpleParticleType simpleParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            return new TomatoSplatParticle(clientWorld, d, e, f, g, h, i, this.spriteProvider);
        }
    }

    @Override
    public Particle scale(float scale) {

        return this;
    }

}
