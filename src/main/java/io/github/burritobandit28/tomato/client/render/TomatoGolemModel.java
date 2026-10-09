// Made with Blockbench 5.1.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package io.github.burritobandit28.tomato.client.render;

import io.github.burritobandit28.tomato.Tomato;
import io.github.burritobandit28.tomato.entities.TomatoGolemEntity;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import org.joml.Vector3f;

public class TomatoGolemModel<T extends LivingEntity> extends BipedEntityModel<TomatoGolemEntity> {
	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart hat;
	private final ModelPart right_arm;
	private final ModelPart left_arm;
	private final ModelPart left_leg;
	private final ModelPart right_leg;
	public TomatoGolemModel(ModelPart root) {
        super(root, RenderLayer::getEntityCutoutNoCull);
        this.root = root;
		this.body = root.getChild("body");
		this.head = root.getChild("head");
		this.hat = root.getChild("hat");
		this.right_arm = root.getChild("right_arm");
		this.left_arm = root.getChild("left_arm");
		this.left_leg = root.getChild("left_leg");
		this.right_leg = root.getChild("right_leg");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-6.0F, -9.0F, -4.5F, 12.0F, 9.0F, 9.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 12.5F, 0.5F));

		ModelPartData straw_hat = head.addChild("straw_hat", ModelPartBuilder.create().uv(9, 18).cuboid(-10.0F, 0.0F, -7.0F, 20.0F, 0.0F, 15.0F, new Dilation(0.0F))
				.uv(0, 33).cuboid(-7.0F, -3.0F, -5.0F, 14.0F, 3.0F, 11.0F, new Dilation(0.0F))
				.uv(50, 42).cuboid(4.0F, -4.0F, -5.25F, 7.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -8.0F, -0.5F, -0.0698F, 0.0F, 0.0873F));

		ModelPartData right_arm = modelPartData.addChild("right_arm", ModelPartBuilder.create().uv(0, 22).cuboid(-5.5F, -2.0F, -2.0F, 6.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.75F, 15.0F, 0.5F));

		ModelPartData left_arm = modelPartData.addChild("left_arm", ModelPartBuilder.create().uv(44, 1).mirrored().cuboid(0.0F, -2.0F, -2.0F, 6.0F, 4.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(5.75F, 15.0F, 0.5F));

		ModelPartData left_leg = modelPartData.addChild("left_leg", ModelPartBuilder.create().uv(48, 10).cuboid(6.75F, 5.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.75F, 15.0F, 0.5F));

		ModelPartData right_leg = modelPartData.addChild("right_leg", ModelPartBuilder.create().uv(0, 56).cuboid(0.75F, 5.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.75F, 15.0F, 0.5F));

		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(22, 47).cuboid(-6.0F, -0.75F, -4.5F, 12.0F, 8.0F, 9.0F, new Dilation(-0.25F))
				.uv(51, 34).cuboid(-8.25F, 2.2F, 2.249F, 3.0F, 5.0F, 0.0F, new Dilation(0.0F))
				.uv(51, 34).mirrored().cuboid(5.25F, -2.0F, -2.75F, 3.0F, 5.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, 13.0F, 0.5F));

		ModelPartData hat = modelPartData.addChild("hat", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 4.5F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void setAngles(TomatoGolemEntity entity, float limbAngle, float limbDistance, float animationProgress, float netHeadYaw, float headPitch) {
		this.head.pitch = headPitch * (float) (Math.PI / 180.0);
		this.head.yaw = netHeadYaw * (float) (Math.PI / 180.0);
		this.right_leg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
		this.left_leg.pitch = MathHelper.cos(limbAngle * 0.6662F + (float) Math.PI) * 1.4F * limbDistance;
		this.right_arm.yaw = MathHelper.cos(limbAngle * 0.6662F) * limbDistance;
		this.left_arm.yaw = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
		if (this.riding) {
			//this.root.translate(new Vector3f(0.0F,-1.0F,0.0F));
			this.left_leg.pitch = (float) -Math.PI / 4;
			this.right_leg.pitch = (float) -Math.PI / 4;
		}
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
		root.render(matrices, vertices, light, overlay,color);
	}

	public static final EntityModelLayer TOMATO_GOLEM_ROOT = createMain("root");

	private static EntityModelLayer createMain(String name) {
		return new EntityModelLayer(Tomato.ID(name), "main");
	}

	public static void registerModelLayers() {
		EntityModelLayerRegistry.registerModelLayer(TOMATO_GOLEM_ROOT, TomatoGolemModel::getTexturedModelData);
	}

}