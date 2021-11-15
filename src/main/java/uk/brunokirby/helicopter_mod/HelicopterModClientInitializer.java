package uk.brunokirby.helicopter_mod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;

/*
 * "client" entrypoint e.g. in fabric.mod.json
```
  "entrypoints": {
    "client": [
        "uk.brunokirby.helicopter_mod.HelicopterModClientInitializer"
    ]
  }
```

 */

@Environment(EnvType.CLIENT)
public class HelicopterModClientInitializer implements ClientModInitializer {

    public static HelicopterControls helicopterControls;

    @Override
    public void onInitializeClient() {
        /*
         * Registers our Cube Entity's renderer, which provides a model and texture for the entity.
         *
         * Entity Renderers can also manipulate the model before it renders based on entity context (EndermanEntityRenderer#render).
         */
        EntityRendererRegistry.INSTANCE.register(
                HelicopterModInitializer.HELICOPTER,
                (dispatcher, context) -> new HelicopterEntityRenderer(dispatcher)
        );

        EntityRendererRegistry.INSTANCE.register(
                HelicopterModInitializer.HELICOPTER_MISSILE,
                (dispatcher, context) -> new HelicopterMissileEntityRenderer(dispatcher)
        );

        helicopterControls = new HelicopterControls();
    }

    public static HelicopterControls getHelicopterControls() {
        return helicopterControls;
    }
}
