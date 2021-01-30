package uk.brunokirby.helicopter_mod;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class HelicopterEntityModel extends EntityModel<HelicopterEntity> {

    protected ModelPart body;
    protected ModelPart arm_l;
    protected ModelPart arm_r;
    protected ModelPart leg_l;
    protected ModelPart leg_r;

    public HelicopterEntityModel() {
        textureWidth = 64;
        textureHeight = 64;

        body = new ModelPart(this);

//        bb_main = new ModelRenderer(this);
//        bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
//        bb_main.setTextureOffset(0, 0).addBox(-6.0F, -9.0F, -6.0F, 12.0F, 9.0F, 12.0F, 0.0F, false);
        body.setPivot(0.0F, 24.0F, 0.0F);
        body.addCuboid("body", -6.0F, -9.0F, -6.0F,
                (int) 12.0F, (int) 9.0F, (int) 12.0F,
                0.0F, 0, 0);

//        bone_head = new ModelRenderer(this);
//        bone_head.setRotationPoint(0.0F, 24.0F, 0.0F);
//        setRotationAngle(bone_head, -0.0873F, 0.0F, 0.0F);
//        bone_head.setTextureOffset(1, 18).addBox(-5.5F, -17.5F, -6.0F, 11.0F, 9.0F, 10.0F, 0.0F, false);
//        bone_head.setTextureOffset(0, 0).addBox(-6.5F, -15.0F, -4.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
//        bone_head.setTextureOffset(0, 0).addBox(5.5F, -15.0F, -4.0F, 1.0F, 3.0F, 1.0F, 0.0F, true);
        ModelPart head = new ModelPart(this);
        head.setPivot(0.0F, 0.0F, 0.0F);
        body.addChild(head);
        head.addCuboid("head", -5.5F, -17.5F, -6.0F,
                11, 9, 10,
                0.0F, 1, 18);
        head.addCuboid("ear_r", -6.5F, -15.0F, -4.0F,
                1, 3, 1,
                0.0F, 0, 0);
        head.addCuboid("ear_l", 5.5F, -15.0F, -4.0F,
                1, 3, 1,
                0.0F, 0, 0);
        head.pitch = -0.0873F;

//        bone_leg_r = new ModelRenderer(this);
//        bone_leg_r.setRotationPoint(-3.25F, 23.0F, -3.0F);
//        bone_leg_r.setTextureOffset(39, 38).addBox(-2.5F, -1.0F, -6.0F, 5.0F, 2.0F, 4.0F, 0.0F, false);
        leg_r = new ModelPart(this);
        leg_r.setPivot(-3.25F, -1.0F, -3.0F);
        body.addChild(leg_r);
        leg_r.addCuboid("leg_r", -2.5F, -1.0F, -6.0F,
                5, 2, 4,
                0.0F, 39, 38);


//        bone_leg_l = new ModelRenderer(this);
//        bone_leg_l.setRotationPoint(3.25F, 23.0F, -3.0F);
//        bone_leg_l.setTextureOffset(39, 38).addBox(-2.5F, -1.0F, -6.0F, 5.0F, 2.0F, 4.0F, 0.0F, true);
        leg_l = new ModelPart(this);
        leg_l.setPivot(3.25F, -1.0F, -3.0F);
        body.addChild(leg_l);
        leg_l.addCuboid("leg_l", -2.5F, -1.0F, -6.0F,
                5, 2, 4,
                0.0F, 39, 38);


//        bone_arm_r = new ModelRenderer(this);
//        bone_arm_r.setRotationPoint(0.0F, 24.0F, 0.0F);
//        arm_r_r1 = new ModelRenderer(this);
//        arm_r_r1.setRotationPoint(-5.25F, -11.5F, 0.0F);
//        bone_arm_r.addChild(arm_r_r1);
//        setRotationAngle(arm_r_r1, 0.0F, 0.0F, -1.0472F);
//        arm_r_r1.setTextureOffset(0, 47).addBox(-9.75F, -1.5F, -1.25F, 12.0F, 2.0F, 3.0F, 0.25F, false);
        arm_r = new ModelPart(this);
        arm_r.setPivot(-5.25F, -11.5F, 0.0F);
        body.addChild(arm_r);
        arm_r.addCuboid("arm_r", -9.75F, -1.5F, -1.25F,
                12, 2, 3,
                0.25F, 0, 47);
        arm_r.roll = -1.0472F;

//        bone_arm_l = new ModelRenderer(this);
//        bone_arm_l.setRotationPoint(0.0F, 24.0F, 0.0F);
//        arm_l_r1 = new ModelRenderer(this);
//        arm_l_r1.setRotationPoint(5.25F, -11.5F, 0.0F);
//        bone_arm_l.addChild(arm_l_r1);
//        setRotationAngle(arm_l_r1, 0.0F, 0.0F, 1.0472F);
//        arm_l_r1.setTextureOffset(0, 53).addBox(-2.25F, -1.5F, -1.25F, 12.0F, 2.0F, 3.0F, 0.25F, true);
        arm_l = new ModelPart(this);
        arm_l.setPivot(5.25F, -11.5F, 0.0F);
        body.addChild(arm_l);
        arm_l.addCuboid("arm_l", -2.25F, -1.5F, -1.25F,
                12, 2, 3,
                0.25F, 0, 53);
        arm_l.roll = 1.0472F;

    }

    @Override
    public void setAngles(HelicopterEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        // TODO do the movement
        //System.out.println("limbAngle=" + limbAngle + " limbDistance=" + limbDistance + " animationProgress=" + animationProgress);

        // we don't want all the gorillas to be in sync!
        float unique = (float)(System.identityHashCode(entity) % 10000)/1000; // effectively random from 0-10

        // arms flap all the time
        float arm_angle = 0.8F + MathHelper.sin(unique + animationProgress * 0.5F) * 0.2F;
        arm_l.roll = arm_angle;
        arm_r.roll = -arm_angle;

        unique += 1.0F;

        // limbs move only when gorilla is moving (via limbDistance)
        float leg_range = 0.2F; // how far the legs move (from horizontal)
        float leg_speed = 2.0F; // how fast the legs flap
        float leg_l_angle = leg_range * (1.0F + MathHelper.sin(unique + limbAngle * leg_speed + 3.14F));
        float leg_r_angle = leg_range * (1.0F + MathHelper.sin(unique + limbAngle * leg_speed));
        leg_l.pitch = -leg_l_angle * limbDistance;
        leg_r.pitch = -leg_r_angle * limbDistance;
    }


    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        body.render(matrices, vertices, light, overlay, red, blue, green, alpha);
    }
}