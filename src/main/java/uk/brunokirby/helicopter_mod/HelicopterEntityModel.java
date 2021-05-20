package uk.brunokirby.helicopter_mod;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;

import static uk.brunokirby.helicopter_mod.HelicopterEntity.Flying.IS_FLYING;
// Made with Blockbench 3.8.2
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


@Environment(EnvType.CLIENT)
public class HelicopterEntityModel extends EntityModel<HelicopterEntity> {
	protected final ModelPart full_heli;
	protected final ModelPart heli_body;
	protected final ModelPart cube_r1;
	protected final ModelPart cube_r2;
	protected final ModelPart cube_r3;
	protected final ModelPart rear_wheel_stalk_r1;
	protected final ModelPart cube_r4;
	protected final ModelPart cube_r5;
	protected final ModelPart cube_r6;
	protected final ModelPart cube_r7;
	protected final ModelPart cube_r8;
	protected final ModelPart cube_r9;
	protected final ModelPart cube_r10;
	protected final ModelPart secondary_rotors;
	protected final ModelPart secondary_rotors_top_r1;
	protected final ModelPart secondary_rotors_front_r1;
	protected final ModelPart secondary_rotorsback_r1;
	protected final ModelPart secondary_rotors_bottom_r1;
	protected final ModelPart r_outer_missiles;
	protected final ModelPart r_inner_missiles;
	protected final ModelPart l_inner_missiles;
	protected final ModelPart l_outer_missiles;
	protected final ModelPart r_wheel_assembly;
	protected final ModelPart r_inner_wheel_stalk_r1;
	protected final ModelPart r_outer_wheel_stalk_r1;
	protected final ModelPart l_wheel_assembly;
	protected final ModelPart l_inner_wheel_stalk_r1;
	protected final ModelPart l_outer_wheel_stalk_r1;
	protected final ModelPart main_rotors;
	protected final ModelPart main_rotors_right_r1;
	protected final ModelPart main_rotors_front_r1;
	protected final ModelPart main_rotors_left_r1;
	protected final ModelPart main_rotors_back_r1;

	public HelicopterEntityModel() {
		textureWidth = 128;
		textureHeight = 128;

		full_heli = new ModelPart(this);
		full_heli.setPivot(0.0F, 16.0F, 0.0F);
		

		heli_body = new ModelPart(this);
		heli_body.setPivot(0.0F, 0.0F, 0.0F);
		full_heli.addChild(heli_body);
		heli_body.addCuboid("banana", -15.0F, -8.0F, -7.0F, 14, 10, 14, 0.0F, 34, 65);
		heli_body.addCuboid("banana", 37.0F, -16.0F, -3.0F, 22, 11, 6, 0.0F, 0, 39);
		heli_body.addCuboid("banana", -5.0F, -12.0F, 7.0F, 22, 8, 1, 0.0F, 2, 70);
		heli_body.addCuboid("banana", -5.0F, -12.0F, -8.0F, 22, 8, 1, 0.0F, 2, 61);
		heli_body.addCuboid("banana", -2.0F, -29.0F, -5.0F, 21, 4, 10, 0.0F, 0, 42);
		heli_body.addCuboid("banana", 0.0F, -25.0F, -6.0F, 19, 7, 12, 0.0F, 0, 37);
		heli_body.addCuboid("banana", 53.0F, -10.0F, 3.0F, 2, 1, 6, 0.0F, 40, 49);
		heli_body.addCuboid("banana", 53.0F, -10.0F, -9.0F, 2, 1, 6, 0.0F, 40, 49);
		heli_body.addCuboid("banana", 16.0F, -18.1F, -7.0F, 7, 13, 14, -0.01F, 14, 29);
		heli_body.addCuboid("banana", 3.0F, -9.0F, 8.0F, 6, 1, 14, 0.0F, 16, 36);
		heli_body.addCuboid("banana", 3.0F, -9.0F, -22.0F, 6, 1, 14, 0.0F, 13, 40);
		heli_body.addCuboid("banana", 49.0F, 6.1F, -1.5F, 1, 1, 3, -0.2F, 78, 0);
		heli_body.addCuboid("banana", 48.0F, 5.0F, -0.5F, 3, 3, 1, 0.1F, 116, 28);
		heli_body.addCuboid("banana", 45.0F, -5.0F, -2.0F, 8, 4, 4, 0.0F, 0, 0);
		heli_body.addCuboid("banana", 15.0F, -16.0F, -3.0F, 22, 11, 6, 0.0F, 72, 89);
		heli_body.addCuboid("banana", 4.0F, -8.0F, -7.0F, 14, 9, 14, 0.0F, 72, 42);
		heli_body.addCuboid("banana", -1.0F, -8.0F, -7.0F, 5, 10, 14, 0.0F, 90, 65);

		cube_r1 = new ModelPart(this);
		cube_r1.setPivot(-18.9F, -6.1F, 6.9F);
		heli_body.addChild(cube_r1);
		cube_r1.roll = 0.1745F;
		cube_r1.addCuboid("banana", -2.0F, -6.0F, 0.0F, 2, 4, 1, 0.0F, 60, 56);
		cube_r1.addCuboid("banana", -2.0F, -6.0F, -14.8F, 2, 4, 1, 0.0F, 66, 56);

		cube_r2 = new ModelPart(this);
		cube_r2.setPivot(-20.0F, -10.0F, -8.0F);
		heli_body.addChild(cube_r2);
		cube_r2.roll = 0.1745F;
		cube_r2.addCuboid("banana", -1.0F, -1.0F, 0.0F, 1, 1, 1, 0.0F, 64, 61);
		cube_r2.addCuboid("banana", -1.0F, -1.0F, 15.0F, 1, 1, 1, 0.0F, 68, 61);

		cube_r3 = new ModelPart(this);
		cube_r3.setPivot(-3.0F, -15.0F, 6.9F);
		heli_body.addChild(cube_r3);
		cube_r3.roll = 0.2618F;
		cube_r3.addCuboid("banana", -1.0F, -9.0F, 0.0F, 1, 18, 1, 0.0F, 124, 20);
		cube_r3.addCuboid("banana", -1.0F, -9.0F, -14.8F, 1, 18, 1, 0.0F, 124, 20);

		rear_wheel_stalk_r1 = new ModelPart(this);
		rear_wheel_stalk_r1.setPivot(48.0F, 0.0F, -0.5F);
		heli_body.addChild(rear_wheel_stalk_r1);
		rear_wheel_stalk_r1.roll = -0.3054F;
		rear_wheel_stalk_r1.addCuboid("banana", -1.0F, -3.0F, 0.0F, 1, 9, 1, 0.0F, 118, 28);

		cube_r4 = new ModelPart(this);
		cube_r4.setPivot(-6.0F, -6.0F, -8.0F);
		heli_body.addChild(cube_r4);
		cube_r4.roll = 0.1745F;
		cube_r4.addCuboid("banana", -13.0F, -5.0F, 0.0F, 13, 8, 1, 0.0F, 58, 47);
		cube_r4.addCuboid("banana", -13.0F, -5.0F, 15.0F, 13, 8, 1, 0.0F, 6, 79);

		cube_r5 = new ModelPart(this);
		cube_r5.setPivot(17.0F, -1.0F, 0.0F);
		heli_body.addChild(cube_r5);
		cube_r5.roll = -0.5236F;
		cube_r5.addCuboid("banana", -1.0F, -3.0F, -6.0F, 9, 4, 12, 0.0F, 14, 41);

		cube_r6 = new ModelPart(this);
		cube_r6.setPivot(-22.0F, -10.0F, 0.0F);
		heli_body.addChild(cube_r6);
		cube_r6.roll = -0.5672F;
		cube_r6.addCuboid("banana", -1.0F, -1.0F, -6.0F, 1, 1, 12, 0.0F, 30, 43);

		cube_r7 = new ModelPart(this);
		cube_r7.setPivot(-17.0F, -13.0F, -7.0F);
		heli_body.addChild(cube_r7);
		cube_r7.roll = -0.5672F;
		cube_r7.addCuboid("banana", -7.0F, -1.0F, 0.0F, 28, 1, 1, 0.0F, 70, 20);
		cube_r7.addCuboid("banana", -3.0F, -1.0F, 13.0F, 24, 1, 1, 0.0F, 78, 20);

		cube_r8 = new ModelPart(this);
		cube_r8.setPivot(55.0F, -9.0F, -10.0F);
		heli_body.addChild(cube_r8);
		cube_r8.roll = 0.3491F;
		cube_r8.addCuboid("banana", -3.0F, -7.0F, 0.0F, 4, 8, 1, 0.0F, 46, 47);
		cube_r8.addCuboid("banana", -3.0F, -7.0F, 19.0F, 4, 8, 1, 0.0F, 46, 47);

		cube_r9 = new ModelPart(this);
		cube_r9.setPivot(-16.0F, -3.0F, 0.0F);
		heli_body.addChild(cube_r9);
		cube_r9.roll = 0.6545F;
		cube_r9.addCuboid("banana", -9.0F, -2.0F, -7.0F, 12, 4, 14, -0.001F, 20, 89);

		cube_r10 = new ModelPart(this);
		cube_r10.setPivot(53.0F, -14.0F, 0.0F);
		heli_body.addChild(cube_r10);
		cube_r10.roll = 0.3927F;
		cube_r10.addCuboid("banana", -5.0F, -19.0F, -1.0F, 9, 19, 2, 0.0F, 0, 35);

		secondary_rotors = new ModelPart(this);
		secondary_rotors.setPivot(0.0F, 2.0F, 0.0F);
		full_heli.addChild(secondary_rotors);
		secondary_rotors.addCuboid("banana", 56.0F, -32.0F, -4.0F, 4, 4, 3, 0.0F, 114, 21);

		secondary_rotors_top_r1 = new ModelPart(this);
		secondary_rotors_top_r1.setPivot(58.0F, -32.0F, -3.0F);
		secondary_rotors.addChild(secondary_rotors_top_r1);
		secondary_rotors_top_r1.pitch = -0.3927F;
		secondary_rotors_top_r1.roll = 1.5708F;
		secondary_rotors_top_r1.addCuboid("banana", -9.0F, -1.0F, 0.0F, 9, 2, 1, 0.0F, 108, 21);

		secondary_rotors_front_r1 = new ModelPart(this);
		secondary_rotors_front_r1.setPivot(56.0F, -30.0F, -3.0F);
		secondary_rotors.addChild(secondary_rotors_front_r1);
		secondary_rotors_front_r1.pitch = -0.3927F;
		secondary_rotors_front_r1.addCuboid("banana", -9.0F, -1.0F, 0.0F, 9, 2, 1, 0.0F, 108, 21);

		secondary_rotorsback_r1 = new ModelPart(this);
		secondary_rotorsback_r1.setPivot(69.0F, -30.0F, -3.0F);
		secondary_rotors.addChild(secondary_rotorsback_r1);
		secondary_rotorsback_r1.pitch = 0.3927F;
		secondary_rotorsback_r1.addCuboid("banana", -9.0F, -1.0F, 0.0F, 9, 2, 1, 0.0F, 108, 21);

		secondary_rotors_bottom_r1 = new ModelPart(this);
		secondary_rotors_bottom_r1.setPivot(58.0F, -19.0F, -3.0F);
		secondary_rotors.addChild(secondary_rotors_bottom_r1);
		secondary_rotors_bottom_r1.pitch = 0.3927F;
		secondary_rotors_bottom_r1.roll = 1.5708F;
		secondary_rotors_bottom_r1.addCuboid("banana", -9.0F, -1.0F, 0.0F, 9, 2, 1, 0.0F, 108, 21);

		r_outer_missiles = new ModelPart(this);
		r_outer_missiles.setPivot(0.0F, 0.0F, 39.0F);
		full_heli.addChild(r_outer_missiles);
		r_outer_missiles.addCuboid("banana", 7.0F, -8.0F, -22.0F, 2, 4, 4, 0.0F, 44, 0);
		r_outer_missiles.addCuboid("banana", 2.0F, -9.5F, -23.5F, 7, 7, 7, -1.8F, 100, 0);

		r_inner_missiles = new ModelPart(this);
		r_inner_missiles.setPivot(0.0F, 0.0F, 34.0F);
		full_heli.addChild(r_inner_missiles);
		r_inner_missiles.addCuboid("banana", 7.0F, -8.0F, -22.0F, 2, 4, 4, 0.0F, 34, 9);
		r_inner_missiles.addCuboid("banana", 2.0F, -9.5F, -23.5F, 7, 7, 7, -1.8F, 100, 0);

		l_inner_missiles = new ModelPart(this);
		l_inner_missiles.setPivot(0.0F, 0.0F, 6.0F);
		full_heli.addChild(l_inner_missiles);
		l_inner_missiles.addCuboid("banana", 7.0F, -8.0F, -22.0F, 2, 4, 4, 0.0F, 34, 9);
		l_inner_missiles.addCuboid("banana", 2.0F, -9.5F, -23.5F, 7, 7, 7, -1.8F, 100, 0);

		l_outer_missiles = new ModelPart(this);
		l_outer_missiles.setPivot(0.0F, 0.0F, 1.0F);
		full_heli.addChild(l_outer_missiles);
		l_outer_missiles.addCuboid("banana", 7.0F, -8.0F, -22.0F, 2, 4, 4, 0.0F, 34, 9);
		l_outer_missiles.addCuboid("banana", 2.0F, -9.5F, -23.5F, 7, 7, 7, -1.8F, 100, 0);

		r_wheel_assembly = new ModelPart(this);
		r_wheel_assembly.setPivot(-11.0F, 6.8F, 9.0F);
		full_heli.addChild(r_wheel_assembly);
		r_wheel_assembly.yaw = 3.1416F;
		r_wheel_assembly.addCuboid("banana", -2.0F, -2.0F, -1.0F, 3, 3, 1, 0.25F, 87, 0);
		r_wheel_assembly.addCuboid("banana", -1.0F, -1.0F, 0.0F, 1, 1, 1, 0.25F, 114, 30);

		r_inner_wheel_stalk_r1 = new ModelPart(this);
		r_inner_wheel_stalk_r1.setPivot(0.0F, -5.0F, 4.0F);
		r_wheel_assembly.addChild(r_inner_wheel_stalk_r1);
		r_inner_wheel_stalk_r1.pitch = -0.6981F;
		r_inner_wheel_stalk_r1.addCuboid("banana", -1.0F, -4.0F, 0.0F, 1, 10, 1, 0.0F, 119, 28);

		r_outer_wheel_stalk_r1 = new ModelPart(this);
		r_outer_wheel_stalk_r1.setPivot(0.0F, -7.0F, 2.0F);
		r_wheel_assembly.addChild(r_outer_wheel_stalk_r1);
		r_outer_wheel_stalk_r1.pitch = -0.3491F;
		r_outer_wheel_stalk_r1.addCuboid("banana", -1.0F, -1.0F, 0.0F, 1, 7, 1, 0.0F, 114, 30);

		l_wheel_assembly = new ModelPart(this);
		l_wheel_assembly.setPivot(-10.0F, 6.8F, -9.0F);
		full_heli.addChild(l_wheel_assembly);
		l_wheel_assembly.addCuboid("banana", -2.0F, -2.0F, -1.0F, 3, 3, 1, 0.25F, 87, 0);
		l_wheel_assembly.addCuboid("banana", -1.0F, -1.0F, 0.0F, 1, 1, 1, 0.25F, 114, 30);

		l_inner_wheel_stalk_r1 = new ModelPart(this);
		l_inner_wheel_stalk_r1.setPivot(0.0F, -5.0F, 4.0F);
		l_wheel_assembly.addChild(l_inner_wheel_stalk_r1);
		l_inner_wheel_stalk_r1.pitch = -0.6981F;
		l_inner_wheel_stalk_r1.addCuboid("banana", -1.0F, -4.0F, 0.0F, 1, 10, 1, 0.0F, 120, 28);

		l_outer_wheel_stalk_r1 = new ModelPart(this);
		l_outer_wheel_stalk_r1.setPivot(0.0F, -7.0F, 2.0F);
		l_wheel_assembly.addChild(l_outer_wheel_stalk_r1);
		l_outer_wheel_stalk_r1.pitch = -0.3491F;
		l_outer_wheel_stalk_r1.addCuboid("banana", -1.0F, -1.0F, 0.0F, 1, 7, 1, 0.0F, 114, 30);

		main_rotors = new ModelPart(this);
		main_rotors.setPivot(0.0F, 0.0F, 0.0F);
		full_heli.addChild(main_rotors);
		main_rotors.addCuboid("banana", 6.0F, -32.0F, -2.0F, 4, 3, 4, 0.0F, 112, 21);

		main_rotors_right_r1 = new ModelPart(this);
		main_rotors_right_r1.setPivot(8.0F, -30.0F, 3.0F);
		main_rotors.addChild(main_rotors_right_r1);
		main_rotors_right_r1.pitch = -0.3927F;
		main_rotors_right_r1.yaw = 1.5708F;
		main_rotors_right_r1.addCuboid("banana", -33.0F, -1.0F, -1.0F, 34, 1, 2, 0.0F, 56, 20);

		main_rotors_front_r1 = new ModelPart(this);
		main_rotors_front_r1.setPivot(6.0F, -30.0F, 0.0F);
		main_rotors.addChild(main_rotors_front_r1);
		main_rotors_front_r1.pitch = -0.3927F;
		main_rotors_front_r1.addCuboid("banana", -33.0F, -1.0F, -1.0F, 34, 1, 2, 0.0F, 56, 21);

		main_rotors_left_r1 = new ModelPart(this);
		main_rotors_left_r1.setPivot(8.0F, -30.0F, -35.0F);
		main_rotors.addChild(main_rotors_left_r1);
		main_rotors_left_r1.pitch = 0.3927F;
		main_rotors_left_r1.yaw = 1.5708F;
		main_rotors_left_r1.addCuboid("banana", -33.0F, -1.0F, -1.0F, 34, 1, 2, 0.0F, 56, 20);

		main_rotors_back_r1 = new ModelPart(this);
		main_rotors_back_r1.setPivot(43.0F, -30.0F, 0.0F);
		main_rotors.addChild(main_rotors_back_r1);
		main_rotors_back_r1.pitch = 0.3927F;
		main_rotors_back_r1.addCuboid("banana", -33.0F, -1.0F, -1.0F, 34, 1, 2, 0.0F, 56, 20);
	}

	@Override
	public void setAngles(HelicopterEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
	}


	public void animateModel(HelicopterEntity entity, float limbAngle, float limbDistance, float tickDelta) {
		if (entity.getFlying() == IS_FLYING) {
			float animationProgress = (float) entity.age + tickDelta;
			main_rotors.yaw = animationProgress / 2.0F;
		} else {
			main_rotors.yaw = (float) Math.PI / 4.0F;
		}
	}

	@Override
	public void render(MatrixStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		full_heli.yaw = (float) Math.PI;
		full_heli.render(matrixStack, buffer, packedLight, packedOverlay);
	}






}
