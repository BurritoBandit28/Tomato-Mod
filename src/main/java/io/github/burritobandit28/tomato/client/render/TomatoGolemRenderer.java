package io.github.burritobandit28.tomato.client.render;

import io.github.burritobandit28.tomato.Tomato;
import io.github.burritobandit28.tomato.client.TomatoClient;
import io.github.burritobandit28.tomato.entities.TomatoGolemEntity;
import net.minecraft.client.render.entity.*;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.ArmorEntityModel;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;

public class TomatoGolemRenderer extends MobEntityRenderer<TomatoGolemEntity, TomatoGolemModel<TomatoGolemEntity>> {

    private static final Identifier TEXTURE = Tomato.ID("textures/entity/tomato_golem.png");
    private static final Identifier TEXTURE_NO_HAT = Tomato.ID("textures/entity/tomato_golem_hatless.png");

    public TomatoGolemRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new TomatoGolemModel<>(ctx.getPart(TomatoGolemModel.TOMATO_GOLEM_ROOT)), 0.36F);
        this.addFeature(new ArmorFeatureRenderer(this, new ArmorEntityModel(ctx.getPart(TomatoClient.TomatoArmorInnerLayer)), new ArmorEntityModel(ctx.getPart(TomatoClient.TomatoArmorOuterLayer)), ctx.getModelManager()));
    }

    @Override
    public Identifier getTexture(TomatoGolemEntity entity) {
        if (!entity.getEquippedStack(EquipmentSlot.HEAD).isEmpty()) {
            return TEXTURE_NO_HAT;
        }
        return TEXTURE;
    }
}
