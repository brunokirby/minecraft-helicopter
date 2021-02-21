//// Made with Blockbench 3.7.5
//// Exported for Minecraft version 1.15
//// Paste this class into your mod and generate all required imports
//
//
//public class helicopter_entity_model extends EntityModel<Entity> {
//	private final ModelRenderer heli_body;
//	private final ModelRenderer cube_r1;
//	private final ModelRenderer cube_r2;
//	private final ModelRenderer cube_r3;
//	private final ModelRenderer cube_r4;
//	private final ModelRenderer cube_r5;
//	private final ModelRenderer cube_r6;
//	private final ModelRenderer cube_r7;
//	private final ModelRenderer secondary_rotors;
//	private final ModelRenderer secondary_rotors_top_r1;
//	private final ModelRenderer secondary_rotors_front_r1;
//	private final ModelRenderer secondary_rotorsback_r1;
//	private final ModelRenderer secondary_rotors_bottom_r1;
//	private final ModelRenderer main_rotors;
//	private final ModelRenderer main_rotors_right_r1;
//	private final ModelRenderer main_rotors_front_r1;
//	private final ModelRenderer main_rotors_left_r1;
//	private final ModelRenderer main_rotors_back_r1;
//	private final ModelRenderer l_wheel_assembly;
//	private final ModelRenderer l_inner_wheel_stalk_r1;
//	private final ModelRenderer l_outer_wheel_stalk_r1;
//	private final ModelRenderer r_wheel_assembly;
//	private final ModelRenderer r_inner_wheel_stalk_r1;
//	private final ModelRenderer r_outer_wheel_stalk_r1;
//	private final ModelRenderer l_outer_missiles;
//	private final ModelRenderer l_inner_missiles;
//	private final ModelRenderer r_inner_missiles;
//	private final ModelRenderer r_outer_missiles;
//	private final ModelRenderer bb_main;
//	private final ModelRenderer rear_wheel_stalk_r1;
//	private final ModelRenderer cube_r8;
//	private final ModelRenderer cube_r9;
//	private final ModelRenderer cube_r10;
//
//	public helicopter_entity_model() {
//		textureWidth = 252;
//		textureHeight = 252;
//
//		heli_body = new ModelRenderer(this);
//		heli_body.setRotationPoint(0.0F, 24.0F, 0.0F);
//		heli_body.setTextureOffset(0, 34).addBox(-15.0F, -8.0F, -7.0F, 32.0F, 8.0F, 14.0F, 0.0F, false);
//		heli_body.setTextureOffset(1, 110).addBox(14.0F, -16.0F, -3.0F, 45.0F, 11.0F, 6.0F, 0.0F, false);
//		heli_body.setTextureOffset(189, 166).addBox(-5.0F, -12.0F, 7.0F, 22.0F, 8.0F, 1.0F, 0.0F, false);
//		heli_body.setTextureOffset(187, 150).addBox(-5.0F, -12.0F, -8.0F, 22.0F, 8.0F, 1.0F, 0.0F, false);
//		heli_body.setTextureOffset(191, 47).addBox(-2.0F, -29.0F, -5.0F, 21.0F, 4.0F, 10.0F, 0.0F, false);
//		heli_body.setTextureOffset(190, 22).addBox(0.0F, -25.0F, -6.0F, 19.0F, 7.0F, 12.0F, 0.0F, false);
//		heli_body.setTextureOffset(218, 110).addBox(53.0F, -10.0F, 3.0F, 2.0F, 1.0F, 6.0F, 0.0F, false);
//		heli_body.setTextureOffset(218, 122).addBox(53.0F, -10.0F, -9.0F, 2.0F, 1.0F, 6.0F, 0.0F, false);
//		heli_body.setTextureOffset(30, 186).addBox(16.0F, -18.1F, -7.0F, 7.0F, 13.0F, 14.0F, 0.0F, false);
//
//		cube_r1 = new ModelRenderer(this);
//		cube_r1.setRotationPoint(-6.0F, -6.0F, -8.0F);
//		heli_body.addChild(cube_r1);
//		setRotationAngle(cube_r1, 0.0F, 0.0F, 0.1745F);
//		cube_r1.setTextureOffset(37, 147).addBox(-13.0F, -5.0F, 0.0F, 13.0F, 8.0F, 1.0F, 0.0F, false);
//		cube_r1.setTextureOffset(78, 155).addBox(-13.0F, -5.0F, 15.0F, 13.0F, 8.0F, 1.0F, 0.0F, false);
//
//		cube_r2 = new ModelRenderer(this);
//		cube_r2.setRotationPoint(17.0F, -1.0F, 0.0F);
//		heli_body.addChild(cube_r2);
//		setRotationAngle(cube_r2, 0.0F, 0.0F, -0.5236F);
//		cube_r2.setTextureOffset(60, 62).addBox(-1.0F, -3.0F, -6.0F, 9.0F, 3.0F, 12.0F, 0.0F, false);
//
//		cube_r3 = new ModelRenderer(this);
//		cube_r3.setRotationPoint(-22.0F, -10.0F, 0.0F);
//		heli_body.addChild(cube_r3);
//		setRotationAngle(cube_r3, 0.0F, 0.0F, -0.5672F);
//		cube_r3.setTextureOffset(188, 212).addBox(-1.0F, -1.0F, -6.0F, 1.0F, 1.0F, 12.0F, 0.0F, false);
//
//		cube_r4 = new ModelRenderer(this);
//		cube_r4.setRotationPoint(-17.0F, -13.0F, -7.0F);
//		heli_body.addChild(cube_r4);
//		setRotationAngle(cube_r4, 0.0F, 0.0F, -0.5672F);
//		cube_r4.setTextureOffset(0, 94).addBox(-7.0F, -1.0F, 0.0F, 28.0F, 1.0F, 1.0F, 0.0F, false);
//		cube_r4.setTextureOffset(0, 88).addBox(-7.0F, -1.0F, 13.0F, 28.0F, 1.0F, 1.0F, 0.0F, false);
//
//		cube_r5 = new ModelRenderer(this);
//		cube_r5.setRotationPoint(55.0F, -9.0F, -10.0F);
//		heli_body.addChild(cube_r5);
//		setRotationAngle(cube_r5, 0.0F, 0.0F, 0.3491F);
//		cube_r5.setTextureOffset(190, 118).addBox(-3.0F, -7.0F, 0.0F, 4.0F, 8.0F, 1.0F, 0.0F, false);
//		cube_r5.setTextureOffset(169, 114).addBox(-3.0F, -7.0F, 19.0F, 4.0F, 8.0F, 1.0F, 0.0F, false);
//
//		cube_r6 = new ModelRenderer(this);
//		cube_r6.setRotationPoint(-16.0F, -3.0F, 0.0F);
//		heli_body.addChild(cube_r6);
//		setRotationAngle(cube_r6, 0.0F, 0.0F, 0.5236F);
//		cube_r6.setTextureOffset(65, 0).addBox(-8.0F, -4.0F, -7.0F, 8.0F, 4.0F, 14.0F, 0.0F, false);
//
//		cube_r7 = new ModelRenderer(this);
//		cube_r7.setRotationPoint(53.0F, -14.0F, 0.0F);
//		heli_body.addChild(cube_r7);
//		setRotationAngle(cube_r7, 0.0F, 0.0F, 0.3927F);
//		cube_r7.setTextureOffset(2, 60).addBox(-5.0F, -19.0F, -1.0F, 9.0F, 19.0F, 2.0F, 0.0F, false);
//
//		secondary_rotors = new ModelRenderer(this);
//		secondary_rotors.setRotationPoint(0.0F, 26.0F, 0.0F);
//		secondary_rotors.setTextureOffset(228, 198).addBox(56.0F, -32.0F, -4.0F, 4.0F, 4.0F, 3.0F, 0.0F, false);
//
//		secondary_rotors_top_r1 = new ModelRenderer(this);
//		secondary_rotors_top_r1.setRotationPoint(58.0F, -32.0F, -3.0F);
//		secondary_rotors.addChild(secondary_rotors_top_r1);
//		setRotationAngle(secondary_rotors_top_r1, -0.3927F, 0.0F, 1.5708F);
//		secondary_rotors_top_r1.setTextureOffset(228, 198).addBox(-9.0F, -1.0F, 0.0F, 9.0F, 2.0F, 1.0F, 0.0F, false);
//
//		secondary_rotors_front_r1 = new ModelRenderer(this);
//		secondary_rotors_front_r1.setRotationPoint(56.0F, -30.0F, -3.0F);
//		secondary_rotors.addChild(secondary_rotors_front_r1);
//		setRotationAngle(secondary_rotors_front_r1, -0.3927F, 0.0F, 0.0F);
//		secondary_rotors_front_r1.setTextureOffset(228, 198).addBox(-9.0F, -1.0F, 0.0F, 9.0F, 2.0F, 1.0F, 0.0F, false);
//
//		secondary_rotorsback_r1 = new ModelRenderer(this);
//		secondary_rotorsback_r1.setRotationPoint(69.0F, -30.0F, -3.0F);
//		secondary_rotors.addChild(secondary_rotorsback_r1);
//		setRotationAngle(secondary_rotorsback_r1, 0.3927F, 0.0F, 0.0F);
//		secondary_rotorsback_r1.setTextureOffset(228, 198).addBox(-9.0F, -1.0F, 0.0F, 9.0F, 2.0F, 1.0F, 0.0F, false);
//
//		secondary_rotors_bottom_r1 = new ModelRenderer(this);
//		secondary_rotors_bottom_r1.setRotationPoint(58.0F, -19.0F, -3.0F);
//		secondary_rotors.addChild(secondary_rotors_bottom_r1);
//		setRotationAngle(secondary_rotors_bottom_r1, 0.3927F, 0.0F, 1.5708F);
//		secondary_rotors_bottom_r1.setTextureOffset(228, 198).addBox(-9.0F, -1.0F, 0.0F, 9.0F, 2.0F, 1.0F, 0.0F, false);
//
//		main_rotors = new ModelRenderer(this);
//		main_rotors.setRotationPoint(0.0F, 24.0F, 0.0F);
//		main_rotors.setTextureOffset(110, 246).addBox(6.0F, -32.0F, -2.0F, 4.0F, 3.0F, 4.0F, 0.0F, false);
//
//		main_rotors_right_r1 = new ModelRenderer(this);
//		main_rotors_right_r1.setRotationPoint(8.0F, -30.0F, 3.0F);
//		main_rotors.addChild(main_rotors_right_r1);
//		setRotationAngle(main_rotors_right_r1, -0.3927F, 1.5708F, 0.0F);
//		main_rotors_right_r1.setTextureOffset(110, 246).addBox(-33.0F, -1.0F, -1.0F, 34.0F, 1.0F, 2.0F, 0.0F, false);
//
//		main_rotors_front_r1 = new ModelRenderer(this);
//		main_rotors_front_r1.setRotationPoint(6.0F, -30.0F, 0.0F);
//		main_rotors.addChild(main_rotors_front_r1);
//		setRotationAngle(main_rotors_front_r1, -0.3927F, 0.0F, 0.0F);
//		main_rotors_front_r1.setTextureOffset(110, 246).addBox(-33.0F, -1.0F, -1.0F, 34.0F, 1.0F, 2.0F, 0.0F, false);
//
//		main_rotors_left_r1 = new ModelRenderer(this);
//		main_rotors_left_r1.setRotationPoint(8.0F, -30.0F, -35.0F);
//		main_rotors.addChild(main_rotors_left_r1);
//		setRotationAngle(main_rotors_left_r1, 0.3927F, 1.5708F, 0.0F);
//		main_rotors_left_r1.setTextureOffset(110, 246).addBox(-33.0F, -1.0F, -1.0F, 34.0F, 1.0F, 2.0F, 0.0F, false);
//
//		main_rotors_back_r1 = new ModelRenderer(this);
//		main_rotors_back_r1.setRotationPoint(43.0F, -30.0F, 0.0F);
//		main_rotors.addChild(main_rotors_back_r1);
//		setRotationAngle(main_rotors_back_r1, 0.3927F, 0.0F, 0.0F);
//		main_rotors_back_r1.setTextureOffset(110, 246).addBox(-33.0F, -1.0F, -1.0F, 34.0F, 1.0F, 2.0F, 0.0F, false);
//
//		l_wheel_assembly = new ModelRenderer(this);
//		l_wheel_assembly.setRotationPoint(-10.0F, 31.0F, -10.0F);
//		l_wheel_assembly.setTextureOffset(123, 226).addBox(-2.0F, -2.0F, -1.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);
//		l_wheel_assembly.setTextureOffset(123, 226).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.25F, false);
//
//		l_inner_wheel_stalk_r1 = new ModelRenderer(this);
//		l_inner_wheel_stalk_r1.setRotationPoint(0.0F, -5.0F, 4.0F);
//		l_wheel_assembly.addChild(l_inner_wheel_stalk_r1);
//		setRotationAngle(l_inner_wheel_stalk_r1, -0.6981F, 0.0F, 0.0F);
//		l_inner_wheel_stalk_r1.setTextureOffset(123, 226).addBox(-1.0F, -4.0F, 0.0F, 1.0F, 10.0F, 1.0F, 0.0F, false);
//
//		l_outer_wheel_stalk_r1 = new ModelRenderer(this);
//		l_outer_wheel_stalk_r1.setRotationPoint(0.0F, -7.0F, 2.0F);
//		l_wheel_assembly.addChild(l_outer_wheel_stalk_r1);
//		setRotationAngle(l_outer_wheel_stalk_r1, -0.3491F, 0.0F, 0.0F);
//		l_outer_wheel_stalk_r1.setTextureOffset(123, 226).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 7.0F, 1.0F, 0.0F, false);
//
//		r_wheel_assembly = new ModelRenderer(this);
//		r_wheel_assembly.setRotationPoint(-10.0F, 31.0F, 10.0F);
//		setRotationAngle(r_wheel_assembly, 0.0F, 3.1416F, 0.0F);
//		r_wheel_assembly.setTextureOffset(131, 226).addBox(-2.0F, -2.0F, -1.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);
//		r_wheel_assembly.setTextureOffset(131, 226).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.25F, false);
//
//		r_inner_wheel_stalk_r1 = new ModelRenderer(this);
//		r_inner_wheel_stalk_r1.setRotationPoint(0.0F, -5.0F, 4.0F);
//		r_wheel_assembly.addChild(r_inner_wheel_stalk_r1);
//		setRotationAngle(r_inner_wheel_stalk_r1, -0.6981F, 0.0F, 0.0F);
//		r_inner_wheel_stalk_r1.setTextureOffset(131, 226).addBox(-1.0F, -4.0F, 0.0F, 1.0F, 10.0F, 1.0F, 0.0F, false);
//
//		r_outer_wheel_stalk_r1 = new ModelRenderer(this);
//		r_outer_wheel_stalk_r1.setRotationPoint(0.0F, -7.0F, 2.0F);
//		r_wheel_assembly.addChild(r_outer_wheel_stalk_r1);
//		setRotationAngle(r_outer_wheel_stalk_r1, -0.3491F, 0.0F, 0.0F);
//		r_outer_wheel_stalk_r1.setTextureOffset(131, 226).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 7.0F, 1.0F, 0.0F, false);
//
//		l_outer_missiles = new ModelRenderer(this);
//		l_outer_missiles.setRotationPoint(0.0F, 24.0F, 1.0F);
//		l_outer_missiles.setTextureOffset(112, 212).addBox(7.0F, -8.0F, -22.0F, 2.0F, 4.0F, 4.0F, 0.0F, false);
//		l_outer_missiles.setTextureOffset(112, 212).addBox(3.0F, -8.0F, -22.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		l_outer_missiles.setTextureOffset(112, 212).addBox(3.0F, -8.0F, -21.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		l_outer_missiles.setTextureOffset(112, 212).addBox(3.0F, -7.0F, -21.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		l_outer_missiles.setTextureOffset(112, 212).addBox(3.0F, -6.0F, -21.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		l_outer_missiles.setTextureOffset(112, 212).addBox(3.0F, -5.0F, -21.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		l_outer_missiles.setTextureOffset(112, 212).addBox(3.0F, -8.0F, -20.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		l_outer_missiles.setTextureOffset(112, 212).addBox(3.0F, -7.0F, -20.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		l_outer_missiles.setTextureOffset(112, 212).addBox(3.0F, -6.0F, -20.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		l_outer_missiles.setTextureOffset(112, 212).addBox(3.0F, -5.0F, -20.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		l_outer_missiles.setTextureOffset(112, 212).addBox(3.0F, -8.0F, -19.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		l_outer_missiles.setTextureOffset(112, 212).addBox(3.0F, -7.0F, -19.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		l_outer_missiles.setTextureOffset(112, 212).addBox(3.0F, -6.0F, -19.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		l_outer_missiles.setTextureOffset(112, 212).addBox(3.0F, -5.0F, -19.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		l_outer_missiles.setTextureOffset(112, 212).addBox(3.0F, -7.0F, -22.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		l_outer_missiles.setTextureOffset(112, 212).addBox(3.0F, -6.0F, -22.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		l_outer_missiles.setTextureOffset(112, 212).addBox(3.0F, -5.0F, -22.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//
//		l_inner_missiles = new ModelRenderer(this);
//		l_inner_missiles.setRotationPoint(0.0F, 24.0F, 6.0F);
//		l_inner_missiles.setTextureOffset(112, 174).addBox(7.0F, -8.0F, -22.0F, 2.0F, 4.0F, 4.0F, 0.0F, false);
//		l_inner_missiles.setTextureOffset(112, 174).addBox(3.0F, -8.0F, -22.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		l_inner_missiles.setTextureOffset(112, 174).addBox(3.0F, -8.0F, -21.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		l_inner_missiles.setTextureOffset(112, 174).addBox(3.0F, -7.0F, -21.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		l_inner_missiles.setTextureOffset(112, 174).addBox(3.0F, -6.0F, -21.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		l_inner_missiles.setTextureOffset(112, 174).addBox(3.0F, -5.0F, -21.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		l_inner_missiles.setTextureOffset(112, 174).addBox(3.0F, -8.0F, -20.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		l_inner_missiles.setTextureOffset(112, 174).addBox(3.0F, -7.0F, -20.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		l_inner_missiles.setTextureOffset(112, 174).addBox(3.0F, -6.0F, -20.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		l_inner_missiles.setTextureOffset(112, 174).addBox(3.0F, -5.0F, -20.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		l_inner_missiles.setTextureOffset(112, 174).addBox(3.0F, -8.0F, -19.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		l_inner_missiles.setTextureOffset(112, 174).addBox(3.0F, -7.0F, -19.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		l_inner_missiles.setTextureOffset(112, 174).addBox(3.0F, -6.0F, -19.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		l_inner_missiles.setTextureOffset(112, 174).addBox(3.0F, -5.0F, -19.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		l_inner_missiles.setTextureOffset(112, 174).addBox(3.0F, -7.0F, -22.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		l_inner_missiles.setTextureOffset(112, 174).addBox(3.0F, -6.0F, -22.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		l_inner_missiles.setTextureOffset(112, 174).addBox(3.0F, -5.0F, -22.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//
//		r_inner_missiles = new ModelRenderer(this);
//		r_inner_missiles.setRotationPoint(0.0F, 24.0F, 34.0F);
//		r_inner_missiles.setTextureOffset(110, 188).addBox(7.0F, -8.0F, -22.0F, 2.0F, 4.0F, 4.0F, 0.0F, false);
//		r_inner_missiles.setTextureOffset(110, 188).addBox(3.0F, -8.0F, -22.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		r_inner_missiles.setTextureOffset(110, 188).addBox(3.0F, -8.0F, -21.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		r_inner_missiles.setTextureOffset(110, 188).addBox(3.0F, -7.0F, -21.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		r_inner_missiles.setTextureOffset(110, 188).addBox(3.0F, -6.0F, -21.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		r_inner_missiles.setTextureOffset(110, 188).addBox(3.0F, -5.0F, -21.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		r_inner_missiles.setTextureOffset(110, 188).addBox(3.0F, -8.0F, -20.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		r_inner_missiles.setTextureOffset(110, 188).addBox(3.0F, -7.0F, -20.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		r_inner_missiles.setTextureOffset(110, 188).addBox(3.0F, -6.0F, -20.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		r_inner_missiles.setTextureOffset(110, 188).addBox(3.0F, -5.0F, -20.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		r_inner_missiles.setTextureOffset(110, 188).addBox(3.0F, -8.0F, -19.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		r_inner_missiles.setTextureOffset(110, 188).addBox(3.0F, -7.0F, -19.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		r_inner_missiles.setTextureOffset(110, 188).addBox(3.0F, -6.0F, -19.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		r_inner_missiles.setTextureOffset(110, 188).addBox(3.0F, -5.0F, -19.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		r_inner_missiles.setTextureOffset(110, 188).addBox(3.0F, -7.0F, -22.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		r_inner_missiles.setTextureOffset(110, 188).addBox(3.0F, -6.0F, -22.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		r_inner_missiles.setTextureOffset(110, 188).addBox(3.0F, -5.0F, -22.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//
//		r_outer_missiles = new ModelRenderer(this);
//		r_outer_missiles.setRotationPoint(0.0F, 24.0F, 39.0F);
//		r_outer_missiles.setTextureOffset(112, 202).addBox(7.0F, -8.0F, -22.0F, 2.0F, 4.0F, 4.0F, 0.0F, false);
//		r_outer_missiles.setTextureOffset(112, 202).addBox(3.0F, -8.0F, -22.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		r_outer_missiles.setTextureOffset(112, 202).addBox(3.0F, -8.0F, -21.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		r_outer_missiles.setTextureOffset(112, 202).addBox(3.0F, -7.0F, -21.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		r_outer_missiles.setTextureOffset(112, 202).addBox(3.0F, -6.0F, -21.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		r_outer_missiles.setTextureOffset(112, 202).addBox(3.0F, -5.0F, -21.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		r_outer_missiles.setTextureOffset(112, 202).addBox(3.0F, -8.0F, -20.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		r_outer_missiles.setTextureOffset(112, 202).addBox(3.0F, -7.0F, -20.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		r_outer_missiles.setTextureOffset(112, 202).addBox(3.0F, -6.0F, -20.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		r_outer_missiles.setTextureOffset(112, 202).addBox(3.0F, -5.0F, -20.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		r_outer_missiles.setTextureOffset(112, 202).addBox(3.0F, -8.0F, -19.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		r_outer_missiles.setTextureOffset(112, 202).addBox(3.0F, -7.0F, -19.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		r_outer_missiles.setTextureOffset(112, 202).addBox(3.0F, -6.0F, -19.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		r_outer_missiles.setTextureOffset(112, 202).addBox(3.0F, -5.0F, -19.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		r_outer_missiles.setTextureOffset(112, 202).addBox(3.0F, -7.0F, -22.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		r_outer_missiles.setTextureOffset(112, 202).addBox(3.0F, -6.0F, -22.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//		r_outer_missiles.setTextureOffset(112, 202).addBox(3.0F, -5.0F, -22.0F, 4.0F, 1.0F, 1.0F, -0.1F, false);
//
//		bb_main = new ModelRenderer(this);
//		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
//		bb_main.setTextureOffset(0, 0).addBox(46.0F, -5.0F, -2.0F, 8.0F, 4.0F, 4.0F, 0.0F, false);
//		bb_main.setTextureOffset(0, 0).addBox(48.0F, 6.0F, -0.5F, 3.0F, 3.0F, 1.0F, 0.1F, false);
//		bb_main.setTextureOffset(0, 0).addBox(49.0F, 7.1F, -1.5F, 1.0F, 1.0F, 3.0F, -0.2F, false);
//		bb_main.setTextureOffset(0, 0).addBox(3.0F, -9.0F, -22.0F, 6.0F, 1.0F, 14.0F, 0.0F, false);
//		bb_main.setTextureOffset(0, 0).addBox(3.0F, -9.0F, 8.0F, 6.0F, 1.0F, 14.0F, 0.0F, false);
//
//		rear_wheel_stalk_r1 = new ModelRenderer(this);
//		rear_wheel_stalk_r1.setRotationPoint(48.0F, 0.0F, -0.5F);
//		bb_main.addChild(rear_wheel_stalk_r1);
//		setRotationAngle(rear_wheel_stalk_r1, 0.0F, 0.0F, -0.3054F);
//		rear_wheel_stalk_r1.setTextureOffset(0, 0).addBox(-1.0F, -2.0F, 0.0F, 1.0F, 9.0F, 1.0F, 0.0F, false);
//
//		cube_r8 = new ModelRenderer(this);
//		cube_r8.setRotationPoint(-3.0F, -15.0F, -7.9F);
//		bb_main.addChild(cube_r8);
//		setRotationAngle(cube_r8, 0.0F, 0.0F, 0.2618F);
//		cube_r8.setTextureOffset(0, 139).addBox(-1.0F, -9.0F, 0.0F, 1.0F, 18.0F, 1.0F, 0.0F, false);
//		cube_r8.setTextureOffset(18, 135).addBox(-1.0F, -9.0F, 14.8F, 1.0F, 18.0F, 1.0F, 0.0F, false);
//
//		cube_r9 = new ModelRenderer(this);
//		cube_r9.setRotationPoint(-20.0F, -10.0F, 7.0F);
//		bb_main.addChild(cube_r9);
//		setRotationAngle(cube_r9, 0.0F, 0.0F, 0.1745F);
//		cube_r9.setTextureOffset(154, 244).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
//		cube_r9.setTextureOffset(168, 241).addBox(-1.0F, -1.0F, -15.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
//
//		cube_r10 = new ModelRenderer(this);
//		cube_r10.setRotationPoint(-18.9F, -6.1F, -7.9F);
//		bb_main.addChild(cube_r10);
//		setRotationAngle(cube_r10, 0.0F, 0.0F, 0.1745F);
//		cube_r10.setTextureOffset(214, 249).addBox(-2.0F, -6.0F, 0.0F, 2.0F, 4.0F, 1.0F, 0.0F, false);
//		cube_r10.setTextureOffset(194, 246).addBox(-2.0F, -6.0F, 14.8F, 2.0F, 4.0F, 1.0F, 0.0F, false);
//	}
//
//	@Override
//	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
//		//previously the render function, render code was moved to a method below
//	}
//
//	@Override
//	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
//		heli_body.render(matrixStack, buffer, packedLight, packedOverlay);
//		secondary_rotors.render(matrixStack, buffer, packedLight, packedOverlay);
//		main_rotors.render(matrixStack, buffer, packedLight, packedOverlay);
//		l_wheel_assembly.render(matrixStack, buffer, packedLight, packedOverlay);
//		r_wheel_assembly.render(matrixStack, buffer, packedLight, packedOverlay);
//		l_outer_missiles.render(matrixStack, buffer, packedLight, packedOverlay);
//		l_inner_missiles.render(matrixStack, buffer, packedLight, packedOverlay);
//		r_inner_missiles.render(matrixStack, buffer, packedLight, packedOverlay);
//		r_outer_missiles.render(matrixStack, buffer, packedLight, packedOverlay);
//		bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
//	}
//
//	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
//		modelRenderer.rotateAngleX = x;
//		modelRenderer.rotateAngleY = y;
//		modelRenderer.rotateAngleZ = z;
//	}
//}