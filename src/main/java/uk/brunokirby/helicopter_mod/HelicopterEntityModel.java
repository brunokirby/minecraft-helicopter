package uk.brunokirby.helicopter_mod;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
//import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class HelicopterEntityModel extends EntityModel<HelicopterEntity> {
    protected ModelPart heliBody;
    protected ModelPart secondaryRotors;
    protected ModelPart primaryRotors;
    protected ModelPart rotatedCube1;

    public HelicopterEntityModel() {
        textureWidth = 256;
        textureHeight = 256;

        heliBody = new ModelPart(this);
        // heli_body.setRotationPoint(0.0F, 24.0F, 0.0F);
        heliBody.setPivot(0.0F, 24.0F, 0.0F);
        //		heli_body.setTextureOffset(0, 34).addBox(-15.0F, -8.0F, -7.0F, 32.0F, 8.0F, 14.0F, 0.0F, false);
        heliBody.addCuboid("1", -15.0F, -8.0F, -7.0F, 32, 8, 14,
                0.0F, 0, 34);
        //		heli_body.setTextureOffset(1, 110).addBox(14.0F, -16.0F, -3.0F, 45.0F, 11.0F, 6.0F, 0.0F, false);
        heliBody.addCuboid("2",14.0F, -16.0F, -3.0F, 45, 11, 6, 0.0F,
                1, 110);
        //		heli_body.setTextureOffset(189, 166).addBox(-5.0F, -12.0F, 7.0F, 22.0F, 8.0F, 1.0F, 0.0F, false);
        heliBody.addCuboid("3", -5.0F, -12.0F, 7.0F, 22, 8, 1,
                0.0F, 189, 166);
        //		heli_body.setTextureOffset(187, 150).addBox(-5.0F, -12.0F, -8.0F, 22.0F, 8.0F, 1.0F, 0.0F, false);
        heliBody.addCuboid("4", -5.0F, -12.0F, -8.0F, 22, 8, 1,
                0.0F,187, 150);
        //		heli_body.setTextureOffset(191, 47).addBox(-2.0F, -29.0F, -5.0F, 21.0F, 4.0F, 10.0F, 0.0F, false);
        heliBody.addCuboid("5", -2.0F, -29.0F, -5.0F, 21, 4, 10,
                0.0F,191, 47);
        //		heli_body.setTextureOffset(190, 22).addBox(0.0F, -25.0F, -6.0F, 19.0F, 7.0F, 12.0F, 0.0F, false);
        heliBody.addCuboid("6", 0.0F, -25.0F, -6.0F, 19, 7, 12,
                0.0F, 190, 22);
        //		heli_body.setTextureOffset(218, 110).addBox(53.0F, -10.0F, 3.0F, 2.0F, 1.0F, 6.0F, 0.0F, false);
        heliBody.addCuboid("7", 53.0F, -10.0F, 3.0F, 2, 1, 6,
                0.0F, 218, 110);
        //		heli_body.setTextureOffset(218, 122).addBox(53.0F, -10.0F, -9.0F, 2.0F, 1.0F, 6.0F, 0.0F, false);
        heliBody.addCuboid("8", 53.0F, -10.0F, -9.0F, 2, 1, 6,
                0.0F, 218, 122);
        //		heli_body.setTextureOffset(30, 186).addBox(16.0F, -18.1F, -7.0F, 7.0F, 13.0F, 14.0F, 0.0F, false);
        heliBody.addCuboid("9", 16.0F, -18.1F, -7.0F, 7, 13, 14,
                0.0F, 30, 186);

        //		cube_r1 = new ModelRenderer(this);
        rotatedCube1 = new ModelPart(this);
        //		cube_r1.setRotationPoint(-6.0F, -6.0F, -8.0F);
        rotatedCube1.setPivot(-6.0F, -6.0F, -8.0F);
        //		heli_body.addChild(cube_r1);
        heliBody.addChild(rotatedCube1);
        //		setRotationAngle(cube_r1, 0.0F, 0.0F, 0.1745F);
        rotatedCube1.roll = 0.1745F;
        //		cube_r1.setTextureOffset(37, 147).addBox(-13.0F, -5.0F, 0.0F, 13.0F, 8.0F, 1.0F, 0.0F, false);
        rotatedCube1.addCuboid("101",-13.0F, -5.0F, 0.0F, 13, 8, 1,
                0.0F, 37, 147);
        //		cube_r1.setTextureOffset(78, 155).addBox(-13.0F, -5.0F, 15.0F, 13.0F, 8.0F, 1.0F, 0.0F, false);
        rotatedCube1.addCuboid("102", -13.0F, -5.0F, 15.0F, 13, 8, 1,
                0.0F, 78, 155);
    }

    @Override
    public void setAngles(HelicopterEntity entity, float limbAngle, float limbDistance,
                          float animationProgress, float headYaw, float headPitch) {

        System.out.println("Helicopter setAngles x="+entity.getPos().x+" y="+entity.getPos().y+" z="+entity.getPos().z);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices,
                       int light, int overlay, float red, float green, float blue, float alpha) {
        System.out.println("Helicopter render");
        heliBody.render(matrices, vertices, light, overlay, red, blue, green, alpha);
    }
}

// for rotations ( , , roll)
