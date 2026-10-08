// Made with Blockbench 5.2.1
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package io.github.burritobandit28.tomato.client.render;

import io.github.burritobandit28.tomato.entities.TomatoGolemEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.BipedEntityModel;

public class TomatoGolemArmorInner extends BipedEntityModel<TomatoGolemEntity> {

	public TomatoGolemArmorInner(ModelPart root) {
		super(root, RenderLayer::getEntityCutoutNoCull);
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData head = modelPartData.addChild("head",ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData hat = modelPartData.addChild("hat", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData right_arm = modelPartData.addChild("right_arm", ModelPartBuilder.create(), ModelTransform.pivot(-5.75F, 15.0F, 0.5F));

		ModelPartData cube_r1 = right_arm.addChild("cube_r1", ModelPartBuilder.create().uv(40, 16).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new Dilation(0.2F)), ModelTransform.of(0.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		ModelPartData left_arm = modelPartData.addChild("left_arm", ModelPartBuilder.create(), ModelTransform.pivot(5.75F, 15.0F, 0.5F));

		ModelPartData cube_r2 = left_arm.addChild("cube_r2", ModelPartBuilder.create().uv(40, 16).mirrored().cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new Dilation(0.2F)).mirrored(false), ModelTransform.of(-0.5F, 0.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

		ModelPartData right_leg = modelPartData.addChild("right_leg", ModelPartBuilder.create().uv(0, 16).mirrored().cuboid(0.75F, -2.2F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.1F)).mirrored(false), ModelTransform.pivot(-5.75F, 15.0F, 0.5F));

		ModelPartData left_leg = modelPartData.addChild("left_leg", ModelPartBuilder.create().uv(0, 16).cuboid(6.75F, -2.2F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.1F)), ModelTransform.pivot(-5.75F, 15.0F, 0.5F));
		return TexturedModelData.of(modelData, 64, 32);
	}
}