package uk.brunokirby.helicopter_mod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class HelicopterEntity extends Entity {
    public HelicopterEntity(EntityType<? extends Entity> entityType, World world) {
        super(entityType, world);
        System.out.println("I'm a new Helicopter!!!");
    }

    @Override
    protected void initDataTracker() {
        // e.g.
        // this.dataTracker.startTracking(DAMAGE_WOBBLE_TICKS, 0);
    }

    @Override
    protected void readCustomDataFromTag(CompoundTag tag) {
        // e.g.
        // if (tag.contains("Type", 8)) {
        //     this.setCanoeType(CanoeEntity.Type.getType(tag.getString("Type")));
        // }
    }

    @Override
    protected void writeCustomDataToTag(CompoundTag tag) {
        // e.g.
        // tag.putString("Type", this.getCanoeType().getName());
    }
    @Override
    public Packet<?> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }

//    public HelicopterEntity(EntityType<? extends AnimalEntity> entityType, World world, double x, double y, double z) {
//        super(entityType, world);
//        this.updatePosition(x, y, z);
//        this.setVelocity(Vec3d.ZERO);
//        this.prevX = x;
//        this.prevY = y;
//        this.prevZ = z;
//    }

    public void moveTo (Vec3d position) {
        updatePosition(position.x, position.y, position.z);
        setVelocity(Vec3d.ZERO);
        prevX = position.x;
        prevY = position.y;
        prevZ = position.z;
    }
}
