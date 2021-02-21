package uk.brunokirby.helicopter_mod;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.BoatEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Quaternion;

import static uk.brunokirby.helicopter_mod.HelicopterModInitializer.HELICOPTER_MOD_NAMESPACE;

/*
 * A renderer is used to provide an entity model, shadow size, and texture.
 */
@Environment(EnvType.CLIENT)
//public class HelicopterEntityRenderer extends MobEntityRenderer<HelicopterEntity, HelicopterEntityModel> {
public class HelicopterEntityRenderer extends EntityRenderer<HelicopterEntity> {

    protected final HelicopterEntityModel model = new HelicopterEntityModel();

    public HelicopterEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
//        super(entityRenderDispatcher, new HelicopterEntityModel(), 0.5f);  //shadow radius
        super(entityRenderDispatcher);
        this.shadowRadius = 3F;
    }

    public void render(HelicopterEntity helicopterEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        matrixStack.translate(0.0D, 0.375D, 0.0D);
        matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(180.0F - f));
        float h = (float)helicopterEntity.getDamageWobbleTicks() - g;
        float j = helicopterEntity.getDamageWobbleStrength() - g;
        if (j < 0.0F) {
            j = 0.0F;
        }

        if (h > 0.0F) {
            matrixStack.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(MathHelper.sin(h) * h * j / 10.0F * (float)helicopterEntity.getDamageWobbleSide()));
        }

//        float k = helicopterEntity.interpolateBubbleWobble(g);
//        if (!MathHelper.approximatelyEquals(k, 0.0F)) {
//            matrixStack.multiply(new Quaternion(new Vector3f(1.0F, 0.0F, 1.0F), helicopterEntity.interpolateBubbleWobble(g), true));
//        }

        matrixStack.scale(-1.0F, -1.0F, 1.0F);
        matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(90.0F));
        this.model.setAngles(helicopterEntity, g, 0.0F, -0.1F, 0.0F, 0.0F);
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(this.model.getLayer(this.getTexture(helicopterEntity)));
        this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
//        if (!helicopterEntity.isSubmergedInWater()) {
//            VertexConsumer vertexConsumer2 = vertexConsumerProvider.getBuffer(RenderLayer.getWaterMask());
//            this.model.getBottom().render(matrixStack, vertexConsumer2, i, OverlayTexture.DEFAULT_UV);
//        }

        matrixStack.pop();
        super.render(helicopterEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }


    @Override
    public Identifier getTexture(HelicopterEntity entity) {
        return new Identifier(HELICOPTER_MOD_NAMESPACE, "textures/helicopter_entity_texture.png");
    }
}