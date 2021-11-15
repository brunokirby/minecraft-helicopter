package uk.brunokirby.helicopter_mod;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.util.Identifier;

import static uk.brunokirby.helicopter_mod.HelicopterModInitializer.HELICOPTER_MOD_NAMESPACE;

/*
 * A renderer is used to provide an entity model, shadow size, and texture.
 */
@Environment(EnvType.CLIENT)
public class HelicopterMissileEntityOriginalRenderer extends EntityRenderer<HelicopterMissileOriginalEntity> {
    protected final HelicopterMissileEntityOriginalModel model = new HelicopterMissileEntityOriginalModel();

    public HelicopterMissileEntityOriginalRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher);
        this.shadowRadius = 0.8F;
    }

    public void render(HelicopterMissileOriginalEntity helicopterMissileEntity, float yaw, float tickDelta, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        matrixStack.translate(0.0D, 1.375D, 0.0D);
        matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(180.0F - yaw));

        matrixStack.scale(-1.0F, -1.0F, 1.0F);
        matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(90.0F));

        // animate
        this.model.animateModel(helicopterMissileEntity, 0, 0, tickDelta);

        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(this.model.getLayer(this.getTexture(helicopterMissileEntity)));
        this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);

        matrixStack.pop();
        super.render(helicopterMissileEntity, yaw, tickDelta, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(HelicopterMissileOriginalEntity entity) {
        return new Identifier(HELICOPTER_MOD_NAMESPACE, "textures/camouflage_helicopter_texture_128_128_wip.png");
    }
}