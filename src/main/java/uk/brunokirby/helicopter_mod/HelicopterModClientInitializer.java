package uk.brunokirby.helicopter_mod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.Entity;
import net.minecraft.text.LiteralText;
import org.lwjgl.glfw.GLFW;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.TreeMap;

/*
 * "client" entrypoint e.g. in fabric.mod.json
```
"entrypoints": {
    "main": [  ... ],
    "client": [
        "org.anthony.experiment.GorillaModClient"
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

        helicopterControls = new HelicopterControls();
    }

    public static HelicopterControls getHelicopterControls() {
        return helicopterControls;
    }
}
