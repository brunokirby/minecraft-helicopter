package uk.brunokirby.helicopter_mod;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
// Made with Blockbench 3.9.2
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


@Environment(EnvType.CLIENT)
public class HelicopterMissileEntityModel extends EntityModel<HelicopterMissileEntity> {
	protected final ModelPart missile;


	public HelicopterMissileEntityModel() {
		textureWidth = 128;
		textureHeight = 128;

		missile = new ModelPart(this);

		missile.addCuboid("missile", 0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F, 0 ,0);


	}

	@Override
	public void setAngles(HelicopterMissileEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
	//previously the render function, render code was moved to a method below

	}

	@Override
	public void render(MatrixStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		missile.render(matrixStack, buffer, packedLight, packedOverlay);
	}

    public void animateModel(HelicopterMissileEntity entity, float limbAngle, float limbDistance, float tickDelta) {
    }





}
