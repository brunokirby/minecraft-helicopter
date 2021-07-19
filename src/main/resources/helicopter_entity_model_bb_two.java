// Made with Blockbench 3.9.1
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


public class helicopter_entity_model extends EntityModel<Entity> {
	private final ModelRenderer full_heli;
	private final ModelRenderer heli_body;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer rear_wheel_stalk_r1;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r5;
	private final ModelRenderer cube_r6;
	private final ModelRenderer cube_r7;
	private final ModelRenderer cube_r8;
	private final ModelRenderer cube_r9;
	private final ModelRenderer cube_r10;
	private final ModelRenderer secondary_rotors;
	private final ModelRenderer secondary_rotors_top_r1;
	private final ModelRenderer secondary_rotors_front_r1;
	private final ModelRenderer secondary_rotorsback_r1;
	private final ModelRenderer secondary_rotors_bottom_r1;
	private final ModelRenderer r_outer_missiles;
	private final ModelRenderer r_inner_missiles;
	private final ModelRenderer l_inner_missiles;
	private final ModelRenderer l_outer_missiles;
	private final ModelRenderer r_wheel_assembly;
	private final ModelRenderer r_inner_wheel_stalk_r1;
	private final ModelRenderer r_outer_wheel_stalk_r1;
	private final ModelRenderer l_wheel_assembly;
	private final ModelRenderer l_inner_wheel_stalk_r1;
	private final ModelRenderer l_outer_wheel_stalk_r1;
	private final ModelRenderer main_rotors;
	private final ModelRenderer main_rotors_right_r1;
	private final ModelRenderer main_rotors_front_r1;
	private final ModelRenderer main_rotors_left_r1;
	private final ModelRenderer main_rotors_back_r1;

	public helicopter_entity_model() {
		textureWidth = 128;
		textureHeight = 128;

		full_heli = new ModelRenderer(this);
		full_heli.setRotationPoint(0.0F, 16.0F, 0.0F);
		

		heli_body = new ModelRenderer(this);
		heli_body.setRotationPoint(0.0F, 0.0F, 0.0F);
		full_heli.addChild(heli_body);
		heli_body.setTextureOffset(34, 65).addBox(-15.0F, -8.0F, -7.0F, 14.0F, 10.0F, 14.0F, 0.0F, false);
		heli_body.setTextureOffset(0, 39).addBox(37.0F, -16.0F, -3.0F, 22.0F, 11.0F, 6.0F, 0.0F, false);
		heli_body.setTextureOffset(2, 70).addBox(-5.0F, -12.0F, 7.0F, 22.0F, 8.0F, 1.0F, 0.0F, false);
		heli_body.setTextureOffset(0, 62).addBox(-5.0F, -12.0F, -8.0F, 22.0F, 8.0F, 1.0F, 0.0F, false);
		heli_body.setTextureOffset(1, 31).addBox(-2.0F, -29.0F, -5.0F, 21.0F, 4.0F, 10.0F, 0.0F, false);
		heli_body.setTextureOffset(0, 26).addBox(0.0F, -25.0F, -6.0F, 19.0F, 7.0F, 12.0F, 0.0F, false);
		heli_body.setTextureOffset(40, 49).addBox(53.0F, -10.0F, 3.0F, 2.0F, 1.0F, 6.0F, 0.0F, false);
		heli_body.setTextureOffset(40, 49).addBox(53.0F, -10.0F, -9.0F, 2.0F, 1.0F, 6.0F, 0.0F, false);
		heli_body.setTextureOffset(14, 29).addBox(16.0F, -18.1F, -7.0F, 7.0F, 13.0F, 14.0F, -0.01F, false);
		heli_body.setTextureOffset(16, 36).addBox(3.0F, -9.0F, 8.0F, 6.0F, 1.0F, 14.0F, 0.0F, false);
		heli_body.setTextureOffset(13, 40).addBox(3.0F, -9.0F, -22.0F, 6.0F, 1.0F, 14.0F, 0.0F, false);
		heli_body.setTextureOffset(78, 0).addBox(49.0F, 6.1F, -1.5F, 1.0F, 1.0F, 3.0F, -0.2F, false);
		heli_body.setTextureOffset(116, 28).addBox(48.0F, 5.0F, -0.5F, 3.0F, 3.0F, 1.0F, 0.1F, false);
		heli_body.setTextureOffset(0, 0).addBox(45.0F, -5.0F, -2.0F, 8.0F, 4.0F, 4.0F, 0.0F, false);
		heli_body.setTextureOffset(72, 89).addBox(15.0F, -16.0F, -3.0F, 22.0F, 11.0F, 6.0F, 0.0F, false);
		heli_body.setTextureOffset(72, 42).addBox(4.0F, -8.0F, -7.0F, 14.0F, 9.0F, 14.0F, 0.0F, false);
		heli_body.setTextureOffset(90, 65).addBox(-1.0F, -8.0F, -7.0F, 5.0F, 10.0F, 14.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(-18.9F, -6.1F, 6.9F);
		heli_body.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 0.0F, 0.1745F);
		cube_r1.setTextureOffset(60, 56).addBox(-2.0F, -6.0F, 0.0F, 2.0F, 4.0F, 1.0F, 0.0F, false);
		cube_r1.setTextureOffset(66, 56).addBox(-2.0F, -6.0F, -14.8F, 2.0F, 4.0F, 1.0F, 0.0F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(-20.0F, -10.0F, -8.0F);
		heli_body.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.0F, 0.1745F);
		cube_r2.setTextureOffset(64, 61).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		cube_r2.setTextureOffset(68, 61).addBox(-1.0F, -1.0F, 15.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(-3.0F, -15.0F, 6.9F);
		heli_body.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 0.0F, 0.2618F);
		cube_r3.setTextureOffset(124, 20).addBox(-1.0F, -9.0F, 0.0F, 1.0F, 18.0F, 1.0F, 0.0F, false);
		cube_r3.setTextureOffset(124, 20).addBox(-1.0F, -9.0F, -14.8F, 1.0F, 18.0F, 1.0F, 0.0F, false);

		rear_wheel_stalk_r1 = new ModelRenderer(this);
		rear_wheel_stalk_r1.setRotationPoint(48.0F, 0.0F, -0.5F);
		heli_body.addChild(rear_wheel_stalk_r1);
		setRotationAngle(rear_wheel_stalk_r1, 0.0F, 0.0F, -0.3054F);
		rear_wheel_stalk_r1.setTextureOffset(118, 28).addBox(-1.0F, -3.0F, 0.0F, 1.0F, 9.0F, 1.0F, 0.0F, false);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(-6.0F, -6.0F, -8.0F);
		heli_body.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, 0.0F, 0.1745F);
		cube_r4.setTextureOffset(58, 47).addBox(-13.0F, -5.0F, 0.0F, 13.0F, 8.0F, 1.0F, 0.0F, false);
		cube_r4.setTextureOffset(6, 79).addBox(-13.0F, -5.0F, 15.0F, 13.0F, 8.0F, 1.0F, 0.0F, false);

		cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(17.0F, -1.0F, 0.0F);
		heli_body.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.0F, 0.0F, -0.5236F);
		cube_r5.setTextureOffset(14, 41).addBox(-1.0F, -3.0F, -6.0F, 9.0F, 4.0F, 12.0F, 0.0F, false);

		cube_r6 = new ModelRenderer(this);
		cube_r6.setRotationPoint(-22.0F, -10.0F, 0.0F);
		heli_body.addChild(cube_r6);
		setRotationAngle(cube_r6, 0.0F, 0.0F, -0.5672F);
		cube_r6.setTextureOffset(30, 43).addBox(-1.0F, -1.0F, -6.0F, 1.0F, 1.0F, 12.0F, 0.0F, false);

		cube_r7 = new ModelRenderer(this);
		cube_r7.setRotationPoint(-17.0F, -13.0F, -7.0F);
		heli_body.addChild(cube_r7);
		setRotationAngle(cube_r7, 0.0F, 0.0F, -0.5672F);
		cube_r7.setTextureOffset(70, 20).addBox(-7.0F, -1.0F, 0.0F, 28.0F, 1.0F, 1.0F, 0.0F, false);
		cube_r7.setTextureOffset(78, 20).addBox(-7.0F, -1.0F, 13.0F, 28.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r8 = new ModelRenderer(this);
		cube_r8.setRotationPoint(55.0F, -9.0F, -10.0F);
		heli_body.addChild(cube_r8);
		setRotationAngle(cube_r8, 0.0F, 0.0F, 0.3491F);
		cube_r8.setTextureOffset(46, 47).addBox(-3.0F, -7.0F, 0.0F, 4.0F, 8.0F, 1.0F, 0.0F, false);
		cube_r8.setTextureOffset(46, 47).addBox(-3.0F, -7.0F, 19.0F, 4.0F, 8.0F, 1.0F, 0.0F, false);

		cube_r9 = new ModelRenderer(this);
		cube_r9.setRotationPoint(-16.0F, -3.0F, 0.0F);
		heli_body.addChild(cube_r9);
		setRotationAngle(cube_r9, 0.0F, 0.0F, 0.6545F);
		cube_r9.setTextureOffset(20, 89).addBox(-9.0F, -2.0F, -7.0F, 12.0F, 4.0F, 14.0F, -0.001F, false);

		cube_r10 = new ModelRenderer(this);
		cube_r10.setRotationPoint(53.0F, -14.0F, 0.0F);
		heli_body.addChild(cube_r10);
		setRotationAngle(cube_r10, 0.0F, 0.0F, 0.3927F);
		cube_r10.setTextureOffset(0, 35).addBox(-5.0F, -19.0F, -1.0F, 9.0F, 19.0F, 2.0F, 0.0F, false);

		secondary_rotors = new ModelRenderer(this);
		secondary_rotors.setRotationPoint(58.0F, -28.0F, -3.0F);
		full_heli.addChild(secondary_rotors);
		secondary_rotors.setTextureOffset(114, 21).addBox(-2.0F, -2.0F, -1.0F, 4.0F, 4.0F, 3.0F, 0.0F, false);

		secondary_rotors_top_r1 = new ModelRenderer(this);
		secondary_rotors_top_r1.setRotationPoint(0.0F, -2.0F, 0.0F);
		secondary_rotors.addChild(secondary_rotors_top_r1);
		setRotationAngle(secondary_rotors_top_r1, -0.3927F, 0.0F, 1.5708F);
		secondary_rotors_top_r1.setTextureOffset(108, 21).addBox(-9.0F, -1.0F, 0.0F, 9.0F, 2.0F, 1.0F, 0.0F, false);

		secondary_rotors_front_r1 = new ModelRenderer(this);
		secondary_rotors_front_r1.setRotationPoint(-2.0F, 0.0F, 0.0F);
		secondary_rotors.addChild(secondary_rotors_front_r1);
		setRotationAngle(secondary_rotors_front_r1, -0.3927F, 0.0F, 0.0F);
		secondary_rotors_front_r1.setTextureOffset(108, 21).addBox(-9.0F, -1.0F, 0.0F, 9.0F, 2.0F, 1.0F, 0.0F, false);

		secondary_rotorsback_r1 = new ModelRenderer(this);
		secondary_rotorsback_r1.setRotationPoint(11.0F, 0.0F, 0.0F);
		secondary_rotors.addChild(secondary_rotorsback_r1);
		setRotationAngle(secondary_rotorsback_r1, 0.3927F, 0.0F, 0.0F);
		secondary_rotorsback_r1.setTextureOffset(108, 21).addBox(-9.0F, -1.0F, 0.0F, 9.0F, 2.0F, 1.0F, 0.0F, false);

		secondary_rotors_bottom_r1 = new ModelRenderer(this);
		secondary_rotors_bottom_r1.setRotationPoint(0.0F, 11.0F, 0.0F);
		secondary_rotors.addChild(secondary_rotors_bottom_r1);
		setRotationAngle(secondary_rotors_bottom_r1, 0.3927F, 0.0F, 1.5708F);
		secondary_rotors_bottom_r1.setTextureOffset(108, 21).addBox(-9.0F, -1.0F, 0.0F, 9.0F, 2.0F, 1.0F, 0.0F, false);

		r_outer_missiles = new ModelRenderer(this);
		r_outer_missiles.setRotationPoint(0.0F, 0.0F, 39.0F);
		full_heli.addChild(r_outer_missiles);
		r_outer_missiles.setTextureOffset(44, 0).addBox(7.0F, -8.0F, -22.0F, 2.0F, 4.0F, 4.0F, 0.0F, false);
		r_outer_missiles.setTextureOffset(100, 0).addBox(2.0F, -9.5F, -23.5F, 7.0F, 7.0F, 7.0F, -1.8F, false);

		r_inner_missiles = new ModelRenderer(this);
		r_inner_missiles.setRotationPoint(0.0F, 0.0F, 34.0F);
		full_heli.addChild(r_inner_missiles);
		r_inner_missiles.setTextureOffset(34, 9).addBox(7.0F, -8.0F, -22.0F, 2.0F, 4.0F, 4.0F, 0.0F, false);
		r_inner_missiles.setTextureOffset(100, 0).addBox(2.0F, -9.5F, -23.5F, 7.0F, 7.0F, 7.0F, -1.8F, false);

		l_inner_missiles = new ModelRenderer(this);
		l_inner_missiles.setRotationPoint(0.0F, 0.0F, 6.0F);
		full_heli.addChild(l_inner_missiles);
		l_inner_missiles.setTextureOffset(34, 9).addBox(7.0F, -8.0F, -22.0F, 2.0F, 4.0F, 4.0F, 0.0F, false);
		l_inner_missiles.setTextureOffset(100, 0).addBox(2.0F, -9.5F, -23.5F, 7.0F, 7.0F, 7.0F, -1.8F, false);

		l_outer_missiles = new ModelRenderer(this);
		l_outer_missiles.setRotationPoint(0.0F, 0.0F, 1.0F);
		full_heli.addChild(l_outer_missiles);
		l_outer_missiles.setTextureOffset(34, 9).addBox(7.0F, -8.0F, -22.0F, 2.0F, 4.0F, 4.0F, 0.0F, false);
		l_outer_missiles.setTextureOffset(100, 0).addBox(2.0F, -9.5F, -23.5F, 7.0F, 7.0F, 7.0F, -1.8F, false);

		r_wheel_assembly = new ModelRenderer(this);
		r_wheel_assembly.setRotationPoint(-11.0F, 6.8F, 9.0F);
		full_heli.addChild(r_wheel_assembly);
		setRotationAngle(r_wheel_assembly, 0.0F, 3.1416F, 0.0F);
		r_wheel_assembly.setTextureOffset(87, 0).addBox(-2.0F, -2.0F, -1.0F, 3.0F, 3.0F, 1.0F, 0.25F, false);
		r_wheel_assembly.setTextureOffset(114, 30).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.25F, false);

		r_inner_wheel_stalk_r1 = new ModelRenderer(this);
		r_inner_wheel_stalk_r1.setRotationPoint(0.0F, -5.0F, 4.0F);
		r_wheel_assembly.addChild(r_inner_wheel_stalk_r1);
		setRotationAngle(r_inner_wheel_stalk_r1, -0.6981F, 0.0F, 0.0F);
		r_inner_wheel_stalk_r1.setTextureOffset(119, 28).addBox(-1.0F, -4.0F, 0.0F, 1.0F, 10.0F, 1.0F, 0.0F, false);

		r_outer_wheel_stalk_r1 = new ModelRenderer(this);
		r_outer_wheel_stalk_r1.setRotationPoint(0.0F, -7.0F, 2.0F);
		r_wheel_assembly.addChild(r_outer_wheel_stalk_r1);
		setRotationAngle(r_outer_wheel_stalk_r1, -0.3491F, 0.0F, 0.0F);
		r_outer_wheel_stalk_r1.setTextureOffset(114, 30).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 7.0F, 1.0F, 0.0F, false);

		l_wheel_assembly = new ModelRenderer(this);
		l_wheel_assembly.setRotationPoint(-10.0F, 6.8F, -9.0F);
		full_heli.addChild(l_wheel_assembly);
		l_wheel_assembly.setTextureOffset(87, 0).addBox(-2.0F, -2.0F, -1.0F, 3.0F, 3.0F, 1.0F, 0.25F, false);
		l_wheel_assembly.setTextureOffset(114, 30).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.25F, false);

		l_inner_wheel_stalk_r1 = new ModelRenderer(this);
		l_inner_wheel_stalk_r1.setRotationPoint(0.0F, -5.0F, 4.0F);
		l_wheel_assembly.addChild(l_inner_wheel_stalk_r1);
		setRotationAngle(l_inner_wheel_stalk_r1, -0.6981F, 0.0F, 0.0F);
		l_inner_wheel_stalk_r1.setTextureOffset(120, 28).addBox(-1.0F, -4.0F, 0.0F, 1.0F, 10.0F, 1.0F, 0.0F, false);

		l_outer_wheel_stalk_r1 = new ModelRenderer(this);
		l_outer_wheel_stalk_r1.setRotationPoint(0.0F, -7.0F, 2.0F);
		l_wheel_assembly.addChild(l_outer_wheel_stalk_r1);
		setRotationAngle(l_outer_wheel_stalk_r1, -0.3491F, 0.0F, 0.0F);
		l_outer_wheel_stalk_r1.setTextureOffset(114, 30).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 7.0F, 1.0F, 0.0F, false);

		main_rotors = new ModelRenderer(this);
		main_rotors.setRotationPoint(8.1F, -31.7F, 0.0F);
		full_heli.addChild(main_rotors);
		main_rotors.setTextureOffset(112, 21).addBox(-2.1F, -0.3F, -2.0F, 4.0F, 3.0F, 4.0F, 0.0F, false);

		main_rotors_right_r1 = new ModelRenderer(this);
		main_rotors_right_r1.setRotationPoint(-0.1F, 1.7F, 3.0F);
		main_rotors.addChild(main_rotors_right_r1);
		setRotationAngle(main_rotors_right_r1, -0.3927F, 1.5708F, 0.0F);
		main_rotors_right_r1.setTextureOffset(56, 20).addBox(-33.0F, -1.0F, -1.0F, 34.0F, 1.0F, 2.0F, 0.0F, false);

		main_rotors_front_r1 = new ModelRenderer(this);
		main_rotors_front_r1.setRotationPoint(-2.1F, 1.7F, 0.0F);
		main_rotors.addChild(main_rotors_front_r1);
		setRotationAngle(main_rotors_front_r1, -0.3927F, 0.0F, 0.0F);
		main_rotors_front_r1.setTextureOffset(56, 21).addBox(-33.0F, -1.0F, -1.0F, 34.0F, 1.0F, 2.0F, 0.0F, false);

		main_rotors_left_r1 = new ModelRenderer(this);
		main_rotors_left_r1.setRotationPoint(-0.1F, 1.7F, -35.0F);
		main_rotors.addChild(main_rotors_left_r1);
		setRotationAngle(main_rotors_left_r1, 0.3927F, 1.5708F, 0.0F);
		main_rotors_left_r1.setTextureOffset(56, 20).addBox(-33.0F, -1.0F, -1.0F, 34.0F, 1.0F, 2.0F, 0.0F, false);

		main_rotors_back_r1 = new ModelRenderer(this);
		main_rotors_back_r1.setRotationPoint(34.9F, 1.7F, 0.0F);
		main_rotors.addChild(main_rotors_back_r1);
		setRotationAngle(main_rotors_back_r1, 0.3927F, 0.0F, 0.0F);
		main_rotors_back_r1.setTextureOffset(56, 20).addBox(-33.0F, -1.0F, -1.0F, 34.0F, 1.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		full_heli.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}