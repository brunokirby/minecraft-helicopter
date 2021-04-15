package uk.brunokirby.helicopter_mod.mixin;

import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.brunokirby.helicopter_mod.HelicopterEntityType;


@Mixin(ClientPlayNetworkHandler.class)
public class MixinClientPlayNetworkHandler {
    @Shadow
    private ClientWorld world;

    // Based on contents of ClientPlayNetworkHandler.onEntitySpawn(...)
    //
    // This code is inserted at the top of the function, duplicating the operation
    // of the original code, but for the entity that we know/care about.
    // (The original effectively does nothing, because it doesn't recognise the EntityType)

    @Inject(method = "onEntitySpawn", at = @At(value = "INVOKE",
                target = "Lnet/minecraft/network/packet/s2c/play/EntitySpawnS2CPacket;getX()D"))
    private void handleEntitySpawn(EntitySpawnS2CPacket pkt, CallbackInfo ci) {
        double x = pkt.getX();
        double y = pkt.getY();
        double z = pkt.getZ();
        EntityType<?> entityTypeId = pkt.getEntityTypeId();
        System.out.println("client got an EntitySpawnS2CPacket packet for "+entityTypeId.getClass().getName());
        if (entityTypeId instanceof HelicopterEntityType) {
            HelicopterEntityType entityType = (HelicopterEntityType) entityTypeId;
            Entity helicopterEntity = entityType.create(world);

            // see ClientPlayNetworkHandler#onEntitySpawn
            helicopterEntity.setPos(x,y,z);
            helicopterEntity.updateTrackedPosition(x, y, z);
            helicopterEntity.refreshPositionAfterTeleport(x, y, z);
            helicopterEntity.pitch = (float) (pkt.getPitch() * 360) / 256.0F;
            helicopterEntity.yaw = (float) (pkt.getYaw() * 360) / 256.0F;
            helicopterEntity.setEntityId(pkt.getId());
            helicopterEntity.setUuid(pkt.getUuid());
            this.world.addEntity(pkt.getId(), helicopterEntity);
        }
    }
}
