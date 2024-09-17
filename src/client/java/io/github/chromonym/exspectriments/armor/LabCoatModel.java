package io.github.chromonym.exspectriments.armor;

import de.dafuqs.spectrum.items.armor.FullArmorModel;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;

// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

public class LabCoatModel extends FullArmorModel {
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart right_arm;
	private final ModelPart left_arm;
	private final ModelPart right_leg;
	private final ModelPart left_leg;
	public LabCoatModel(ModelPart root, EquipmentSlot slot) {
		super(root, slot);
		this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.right_arm = root.getChild("right_arm");
		this.left_arm = root.getChild("left_arm");
		this.right_leg = root.getChild("right_leg");
		this.left_leg = root.getChild("left_leg");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData root = modelData.getRoot();
		root.addChild("hat", ModelPartBuilder.create(), ModelTransform.NONE);
		ModelPartData head = root.addChild("head", ModelPartBuilder.create(), ModelTransform.NONE);
		ModelPartData body = root.addChild("body", ModelPartBuilder.create(), ModelTransform.NONE);
		body.addChild("armor_body", ModelPartBuilder.create().uv(0, 0).cuboid(-4.5F, -0.5F, -2.5F, 9.0F, 14.0F, 5.0F, new Dilation(0.01F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		ModelPartData rightArm = root.addChild("right_arm", ModelPartBuilder.create(), ModelTransform.NONE);
		rightArm.addChild("armor_right_arm", ModelPartBuilder.create().uv(24, 19).cuboid(-3.5F, -2.5F, -2.5F, 5.0F, 9.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -0.5F, 0.0F));
		ModelPartData leftArm = root.addChild("left_arm", ModelPartBuilder.create(), ModelTransform.NONE);
		leftArm.addChild("armor_left_arm", ModelPartBuilder.create().uv(2, 19).cuboid(-1.5F, -2.5F, -2.5F, 5.0F, 9.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -0.5F, 0.0F));
		ModelPartData rightLeg = root.addChild("right_leg", ModelPartBuilder.create(), ModelTransform.NONE);
		rightLeg.addChild("armor_right_leg", ModelPartBuilder.create().uv(46, 11).cuboid(-2.6F, -0.5F, -2.5F, 4.0F, 6.0F, 5.0F, new Dilation(0.01F)), ModelTransform.pivot(0F, 0.0F, 0.0F));
		ModelPartData leftLeg = root.addChild("left_leg", ModelPartBuilder.create(), ModelTransform.NONE);
		leftLeg.addChild("armor_left_leg", ModelPartBuilder.create().uv(46, 0).cuboid(-1.4F, -0.5F, -2.5F, 4.0F, 6.0F, 5.0F, new Dilation(0.01F)), ModelTransform.pivot(0F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		right_arm.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		left_arm.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		right_leg.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		left_leg.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}