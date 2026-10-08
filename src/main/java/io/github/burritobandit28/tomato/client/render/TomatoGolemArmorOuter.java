// Made with Blockbench 5.2.1
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package io.github.burritobandit28.tomato.client.render;

import io.github.burritobandit28.tomato.entities.TomatoGolemEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.BipedEntityModel;

public class TomatoGolemArmorOuter extends BipedEntityModel<TomatoGolemEntity> {

	public TomatoGolemArmorOuter(ModelPart root) {
		super(root, RenderLayer::getEntityCutoutNoCull);
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();

		// I want to quickly disclaim something
		// The hat and head model are complete bs made entirely inside intellij
		// they may be off-centre by like 0.7 of a pixel or the hat layer might be misaligned
		// "might" no it definitely is

		ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -9.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(2.3F)), ModelTransform.pivot(0.0F, 12.5F, 0.5F));

		ModelPartData hat = head.addChild("real_hat", ModelPartBuilder.create().uv(32, 0).cuboid(-4.0F, -21.5F, -4.5F, 8.0F, 8.0F, 8.0F, new Dilation(2.9F)), ModelTransform.pivot(0.0F, 12.5F, 0.5F));

		ModelPartData fake_hat = modelPartData.addChild("hat", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 4.5F, 0.0F));


		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData right_arm = modelPartData.addChild("right_arm", ModelPartBuilder.create(), ModelTransform.pivot(-5.75F, 15.0F, 0.5F));

		ModelPartData cube_r1 = right_arm.addChild("cube_r1", ModelPartBuilder.create().uv(40, 16).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new Dilation(0.5F)), ModelTransform.of(0.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		ModelPartData left_arm = modelPartData.addChild("left_arm", ModelPartBuilder.create(), ModelTransform.pivot(5.75F, 15.0F, 0.5F));

		ModelPartData cube_r2 = left_arm.addChild("cube_r2", ModelPartBuilder.create().uv(40, 16).mirrored().cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new Dilation(0.5F)).mirrored(false), ModelTransform.of(-0.5F, 0.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

		ModelPartData right_leg = modelPartData.addChild("right_leg", ModelPartBuilder.create().uv(0, 16).mirrored().cuboid(0.75F, -2.5F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.3F)).mirrored(false), ModelTransform.pivot(-5.75F, 15.0F, 0.5F));

		ModelPartData left_leg = modelPartData.addChild("left_leg", ModelPartBuilder.create().uv(0, 16).cuboid(6.75F, -2.5F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.3F)), ModelTransform.pivot(-5.75F, 15.0F, 0.5F));
		return TexturedModelData.of(modelData, 64, 32);
	}
}