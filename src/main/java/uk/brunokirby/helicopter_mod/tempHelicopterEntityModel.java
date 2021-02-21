package uk.brunokirby.helicopter_mod;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
//import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class tempHelicopterEntityModel extends EntityModel<HelicopterEntity> {
    protected ModelPart body;

    public  tempHelicopterEntityModel() {
        textureWidth = 64;
        textureHeight = 64;

        body = new ModelPart(this);
        // body.setRotationPoint(0.0F, 24.0F, 0.0F);
        body.setPivot(0.0F, 24.0F, 0.0F);

        // body.setTextureOffset(0, 0).addBox(-8.0F, -2.0F, -8.0F, 16.0F, 2.0F, 16.0F, 0.0F, false);
        body.addCuboid("base", -8.0F, -2.0F, -8.0F, 16, 2, 16,
                0.0F, 0, 0);
        // body.setTextureOffset(0, 19).addBox(7.0F, -13.0F, 0.0F, 1.0F, 11.0F, 1.0F, 0.0F, false);
        body.addCuboid("pillar", 7.0F, -13.0F, 0.0F, 1, 11, 1,
                0.0F, 0, 19);
        // body.setTextureOffset(15, 19).addBox(-8.0F, -3.0F, 7.0F, 16.0F, 1.0F, 1.0F, 0.0F, false);
        body.addCuboid("right_side", -8.0F, -3.0F, 7.0F, 16, 1, 1,
                0.0F, 15, 19);
        // body.setTextureOffset(15, 22).addBox(-8.0F, -3.0F, -8.0F, 16.0F, 1.0F, 1.0F, 0.0F, false);
        body.addCuboid("left_side", -8.0F, -3.0F, -8.0F, 16, 1, 1,
                0.0F, 15, 22);
        // body.setTextureOffset(17, 24).addBox(7.0F, -3.0F, -7.0F, 1.0F, 1.0F, 14.0F, 0.0F, false);
        body.addCuboid("back_edge", 7.0F, -3.0F, -7.0F, 1, 1, 14,
                0.0F, 17, 24);
        // body.setTextureOffset(15, 39).addBox(-8.0F, -3.0F, -7.0F, 1.0F, 1.0F, 14.0F, 0.0F, false;
        body.addCuboid("front", -8.0F, -3.0F, -7.0F, 1, 1, 14,
                0.0F, 15, 39);

    }

    @Override
    public void setAngles(HelicopterEntity entity, float limbAngle, float limbDistance,
                          float animationProgress, float headYaw, float headPitch) {

    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices,
                       int light, int overlay, float red, float green, float blue, float alpha) {
        body.render(matrices, vertices, light, overlay, red, blue, green, alpha);

    }
}
