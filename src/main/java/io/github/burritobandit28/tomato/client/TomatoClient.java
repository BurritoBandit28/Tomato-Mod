package io.github.burritobandit28.tomato.client;

import io.github.burritobandit28.tomato.Tomato;
import io.github.burritobandit28.tomato.block.BlockRegister;
import io.github.burritobandit28.tomato.client.render.TomatoGolemModel;
import io.github.burritobandit28.tomato.client.render.TomatoGolemRenderer;
import io.github.burritobandit28.tomato.entities.EntityRegister;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.particle.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

public class TomatoClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        TomatoGolemModel.registerModelLayers();
        EntityRendererRegistry.register(EntityRegister.TOMATO_GOLEM_ENTITY_TYPE, TomatoGolemRenderer::new);
        ParticleFactoryRegistry.getInstance().register(Tomato.TOMATO_SPLAT_PARTICLE, TomatoSplatParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(Tomato.GOLDEN_TOMATO_SPLAT_PARTICLE, TomatoSplatParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(Tomato.SPLATTED_EFFECT_PARTICLE, SpellParticle.DefaultFactory::new);
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegister.SMALL_TOMATO_PLANT,RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegister.TOMATO_STEM,RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegister.ATTACHED_TOMATO_STEM,RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegister.TOMATO_BLOCK,RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegister.LIT_CARVED_TOMATO_BLOCK,RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegister.CARVED_TOMATO_BLOCK,RenderLayer.getCutout());

        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> 0x38662b, BlockRegister.TOMATO_STEM);
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> 0x38662b, BlockRegister.ATTACHED_TOMATO_STEM);

        EntityRendererRegistry.register(EntityRegister.tomato, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(EntityRegister.golden_tomato, FlyingItemEntityRenderer::new);
    }
}
