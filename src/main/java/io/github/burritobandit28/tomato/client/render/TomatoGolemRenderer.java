package io.github.burritobandit28.tomato.client.render;

import io.github.burritobandit28.tomato.Tomato;
import io.github.burritobandit28.tomato.entities.TomatoGolemEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.util.Identifier;

public class TomatoGolemRenderer extends LivingEntityRenderer<TomatoGolemEntity, TomatoGolemModel<TomatoGolemEntity>> {

    private static final Identifier TEXTURE = Tomato.ID("textures/entity/tomato_golem.png");

    public TomatoGolemRenderer(EntityRendererFactory.Context context) {
        super(context, new TomatoGolemModel<>(context.getPart(TomatoGolemModel.TOMATO_GOLEM_ROOT)), 0.36F);
    }

    @Override
    public Identifier getTexture(TomatoGolemEntity entity) {
        return TEXTURE;
    }
}
