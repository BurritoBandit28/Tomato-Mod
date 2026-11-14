package io.github.burritobandit28.tomato.client.render;

import io.github.burritobandit28.tomato.Tomato;
import io.github.burritobandit28.tomato.client.TomatoClient;
import io.github.burritobandit28.tomato.entities.TomatoGolemEntity;
import net.minecraft.client.render.entity.*;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.ArmorEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtString;
import net.minecraft.util.Identifier;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TomatoGolemRenderer extends MobEntityRenderer<TomatoGolemEntity, TomatoGolemModel<TomatoGolemEntity>> {

    private static final Identifier TEXTURE = Tomato.ID("textures/entity/tomato_golem.png");
    private static final Identifier TEXTURE_NO_HAT = Tomato.ID("textures/entity/tomato_golem_hatless.png");


    public TomatoGolemRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new TomatoGolemModel<>(ctx.getPart(TomatoGolemModel.TOMATO_GOLEM_ROOT)), 0.36F);
        this.addFeature(new ArmorFeatureRenderer(this, new ArmorEntityModel(ctx.getPart(TomatoClient.TomatoArmorInnerLayer)), new ArmorEntityModel(ctx.getPart(TomatoClient.TomatoArmorOuterLayer)), ctx.getModelManager()));
    }

    @Override
    public Identifier getTexture(TomatoGolemEntity entity) {

        var should = false;

        try {
            Method m = Entity.class.getMethod("getPersistentData");
            NbtCompound persistant = (NbtCompound) m.invoke(entity);
            should = persistant.contains("TrainHat");
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            //throw new RuntimeException(e);
        }

        if (!entity.getEquippedStack(EquipmentSlot.HEAD).isEmpty() || should ) {
            return TEXTURE_NO_HAT;
        }
        return TEXTURE;
    }
}
