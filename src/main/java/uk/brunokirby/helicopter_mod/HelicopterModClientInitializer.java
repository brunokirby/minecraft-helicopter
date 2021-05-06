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

    private static KeyBinding testKeyBinding;

    private static KeyBinding upArrowKeyBinding;

    private static Map<KeyBinding, HelicopterEntity.KeyPress> keyMapping = new TreeMap<>();

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

        // register key bindings
        testKeyBinding = registerKeyBinding(GLFW.GLFW_KEY_R, "key.helicopter_mod.spook");
        upArrowKeyBinding = registerKeyBinding(GLFW.GLFW_KEY_UP, "key.helicopter_mod.up_arrow");

        keyMapping.put(testKeyBinding, HelicopterEntity.KeyPress.KEY_R);
        keyMapping.put(upArrowKeyBinding, HelicopterEntity.KeyPress.KEY_UP_ARROW);

        // create handler for key binding
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            for (KeyBinding keyBinding: keyMapping.keySet()) {
                while (keyBinding.wasPressed()) {
                    client.player.sendMessage(new LiteralText("test: Key " + keyMapping.get(keyBinding)
                            + " was pressed!"), false);
                    handleKey(client.player, keyBinding);
                }
            }
        });
    }

    private static KeyBinding registerKeyBinding(int keyCode, String translationKey) {
        return KeyBindingHelper.registerKeyBinding(new KeyBinding(
                translationKey, // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                keyCode, // The keycode of the key
                "category.helicopter_mod.custom_keys" // The translation key of the keybinding's category.
        ));
    }


    // TODO multiple keys
    // TODO call new interface on HelicopterEntity
    private void handleKey(ClientPlayerEntity clientPlayerEntity, KeyBinding keyBinding) {
        try {
            Field f = Entity.class.getDeclaredField("vehicle");
            f.setAccessible(true);
            Entity vehicle = (Entity)f.get(clientPlayerEntity);
            if (vehicle instanceof HelicopterEntity) {
                HelicopterEntity helicopterEntity = (HelicopterEntity)vehicle;
                System.out.println("custom helicopter keypress: "+keyBinding.getTranslationKey());
                helicopterEntity.customKeyPressed(keyMapping.get(keyBinding));
            }

        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
