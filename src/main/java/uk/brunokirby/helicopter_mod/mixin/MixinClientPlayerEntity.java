package uk.brunokirby.helicopter_mod.mixin;

import net.minecraft.client.input.Input;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.brunokirby.helicopter_mod.HelicopterEntity;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/*
 * Mixin to intercept ClientPlayerEntity.tickRiding & capture keypresses
 */
@Mixin(ClientPlayerEntity.class)
public class MixinClientPlayerEntity {
    @Shadow
    public Input input;

    // Based on contents of ClientPlayerEntity.tickRiding(...)
    //
    // This code is inserted at the bottom of the function, adding a section
    // similar to the BoatEntity input handling

    // non-obfuscated field "vehicle" for MC 1.16.4
    private final static String VEHICLE_FIELD_1_16_4 = "field_6034";

    @Inject(method = "tickRiding", at = @At(value = "TAIL"))
    private void injected(CallbackInfo ci) {

        // get the 'vehicle' field for the Entity
        // try both obfuscated & de-obfuscated strings for the 'vehicle' field
        // (the de-obfuscation doesn't work here because we're using Reflection)
        Field vehicleField = null;
        List<String> fieldNames = Arrays.asList("vehicle", VEHICLE_FIELD_1_16_4);
        for (String fieldName: fieldNames) {
            try {
                vehicleField = Entity.class.getDeclaredField(fieldName);
                break;
            } catch (NoSuchFieldException e) {
                // try the other one
            }
        }

        if (vehicleField == null) {
            // failed
            // TODO emit a warning (first time only)
            return;
        }

        // hack private -> public & get the vehicle
        vehicleField.setAccessible(true);
        Entity vehicle;
        try {
            vehicle = (Entity)vehicleField.get(this);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        // pass the 'input' object to our Helicopter, so we can read movement keypresses
        if (vehicle instanceof HelicopterEntity) {
            HelicopterEntity helicopterEntity = (HelicopterEntity)vehicle;
//          System.out.println("inputs="+ input.pressingLeft + input.pressingRight + input.pressingForward + input.pressingBack);
            helicopterEntity.playerTickRiding(input);
        }
    }
}
