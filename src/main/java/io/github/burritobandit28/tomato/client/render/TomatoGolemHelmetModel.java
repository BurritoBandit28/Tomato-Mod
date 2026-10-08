// Made with Blockbench 5.2.1
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package io.github.burritobandit28.tomato.client.render;

import io.github.burritobandit28.tomato.entities.TomatoGolemEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class TomatoGolemHelmetModel extends BipedEntityModel<TomatoGolemEntity> {

	public TomatoGolemHelmetModel(ModelPart root) {
		super(root, RenderLayer::getEntityCutoutNoCull);
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -10.0F, -4.5F, 8.0F, 8.0F, 8.0F, new Dilation(2.3F)), ModelTransform.pivot(0.0F, 13.0F, 1.0F));

		ModelPartData hat = modelPartData.addChild("hat", ModelPartBuilder.create().uv(32, 0).cuboid(-4.0F, -10.0F, -4.5F, 8.0F, 8.0F, 8.0F, new Dilation(2.8F)), ModelTransform.pivot(0.0F, 13.0F, 1.0F));

		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData right_arm = modelPartData.addChild("right_arm", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData left_arm = modelPartData.addChild("left_arm", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData right_leg = modelPartData.addChild("right_leg", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData left_leg = modelPartData.addChild("left_leg", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 32);
	}
}