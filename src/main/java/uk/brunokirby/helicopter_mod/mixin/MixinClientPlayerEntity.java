package uk.brunokirby.helicopter_mod.mixin;

import net.minecraft.client.input.Input;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import org.lwjgl.system.CallbackI;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.brunokirby.helicopter_mod.HelicopterEntity;
import uk.brunokirby.helicopter_mod.HelicopterEntityType;

import java.lang.reflect.Field;


@Mixin(ClientPlayerEntity.class)
//public abstract class MixinClientPlayerEntity {
public class MixinClientPlayerEntity {
//    @Shadow
//    private boolean riding;

//    @Shadow
//    public Entity getVehicle() { return null; };

//    @Shadow
//    private Entity vehicle;

    @Shadow
    public Input input;


    // Based on contents of ClientPlayerEntity.tickRiding(...)
    //
    // This code is inserted at the bottom of the function, adding a section similar
    // to the BoatEntity input handling

    @Inject(method = "tickRiding", at = @At(value = "TAIL"))
    private void injected(CallbackInfo ci) {
        System.out.println("My bananas got injected");

        try {
            Field f = Entity.class.getDeclaredField("vehicle");
            f.setAccessible(true);
            Entity vehicle = (Entity)f.get(this);
            System.out.println("gotta vehicle");
            if (vehicle instanceof HelicopterEntity) {
                System.out.println("gotta helicopter");
//            this.input.pressingLeft, this.input.pressingRight, this.input.pressingForward, this.input.pressingBack
                System.out.println("inputs="+ this.input.pressingLeft + this.input.pressingRight + this.input.pressingForward + this.input.pressingBack);
            }

        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }


//        if (this.getVehicle() instanceof HelicopterEntity) {
//            BoatEntity boatEntity = (BoatEntity)this.getVehicle();
//            boatEntity.setInputs(this.input.pressingLeft, this.input.pressingRight, this.input.pressingForward, this.input.pressingBack);
//            this.riding |= this.input.pressingLeft || this.input.pressingRight || this.input.pressingForward || this.input.pressingBack;
//        }

    }
}
