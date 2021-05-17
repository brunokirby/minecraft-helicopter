package uk.brunokirby.helicopter_mod;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import java.util.Map;
import java.util.TreeMap;

public class HelicopterControls {

    private Map<KeyPress, KeyBinding> keyMapping = new TreeMap<>();


    public HelicopterControls() {
        keyMapping.put(KeyPress.KEY_UP_ARROW, registerKeyBinding(GLFW.GLFW_KEY_UP, "key.helicopter_mod.up_arrow"));
        keyMapping.put(KeyPress.KEY_DOWN_ARROW, registerKeyBinding(GLFW.GLFW_KEY_DOWN, "key.helicopter_mod.down_arrow"));
        keyMapping.put(KeyPress.KEY_LEFT_ARROW, registerKeyBinding(GLFW.GLFW_KEY_LEFT, "key.helicopter_mod.left_arrow"));
        keyMapping.put(KeyPress.KEY_RIGHT_ARROW, registerKeyBinding(GLFW.GLFW_KEY_RIGHT, "key.helicopter_mod.right_arrow"));
    }

    public enum KeyPress {
        KEY_UP_ARROW,
        KEY_DOWN_ARROW,
        KEY_LEFT_ARROW,
        KEY_RIGHT_ARROW
    }

    private KeyBinding registerKeyBinding(int keyCode, String translationKey) {
        return KeyBindingHelper.registerKeyBinding(new KeyBinding(
                translationKey, // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                keyCode, // The keycode of the key
                "category.helicopter_mod.custom_keys" // The translation key of the keybinding's category.
        ));
    }


    public boolean isPressed(KeyPress keyPress) {
        return keyMapping.get(keyPress).isPressed();
    }
}
