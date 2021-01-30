package uk.brunokirby.helicopter_mod;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

import static uk.brunokirby.helicopter_mod.HelicopterModInitializer.HELICOPTER_MOD_NAMESPACE;

/*
 * A renderer is used to provide an entity model, shadow size, and texture.
 */
@Environment(EnvType.CLIENT)
public class HelicopterEntityRenderer extends MobEntityRenderer<HelicopterEntity, HelicopterEntityModel> {

    public HelicopterEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new HelicopterEntityModel(), 0.5f);  //shadow radius
    }

    @Override
    public Identifier getTexture(HelicopterEntity entity) {
        return new Identifier(HELICOPTER_MOD_NAMESPACE, "textures/helicopter.png");
    }
}