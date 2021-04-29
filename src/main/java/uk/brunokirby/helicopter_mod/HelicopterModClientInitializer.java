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

    private static KeyBinding testKeyBinding;

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

        // register key binding
        testKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.helicopter_mod.spook", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_R, // The keycode of the key
                "category.helicopter_mod.test" // The translation key of the keybinding's category.
        ));

        // create handler for key binding
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (testKeyBinding.wasPressed()) {
                client.player.sendMessage(new LiteralText("test: Key R was pressed!"), false);
                handleKey(client.player, testKeyBinding);
            }
        });

    }

    // TODO multiple keys
    // TODO call new interface on HelicopterEntity
    private void handleKey(ClientPlayerEntity clientPlayerEntity, KeyBinding keyBinding) {
        try {
            Field f = Entity.class.getDeclaredField("vehicle");
            f.setAccessible(true);
            Entity vehicle = (Entity)f.get(clientPlayerEntity);
//            System.out.println("gotta vehicle");
            if (vehicle instanceof HelicopterEntity) {
                HelicopterEntity helicopterEntity = (HelicopterEntity)vehicle;
                System.out.println("custom helicopter keypress");
                helicopterEntity.customKeyPressed(keyBinding);
            } else {
//                System.out.println("notta helicopter");
            }

        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
