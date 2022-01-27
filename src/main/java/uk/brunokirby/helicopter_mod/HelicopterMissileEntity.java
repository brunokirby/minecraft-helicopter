package uk.brunokirby.helicopter_mod;

import net.minecraft.entity.MovementType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;


import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.explosion.Explosion;

import static uk.brunokirby.helicopter_mod.HelicopterModInitializer.HELICOPTER_MISSILE;

public class HelicopterMissileEntity extends Entity  {
    private static final TrackedData<ItemStack> ITEM;
    private int life;
    private int lifeTime;

    public HelicopterMissileEntity(EntityType<? extends Entity> entityType, World world) {
        super(entityType, world);
    }

    public HelicopterMissileEntity(World world, double x, double y, double z) {
        super(HELICOPTER_MISSILE, world);
        this.life = 0;
        this.updatePosition(x, y, z);
        this.setVelocity(this.random.nextGaussian() * 0.001D, 0.05D, this.random.nextGaussian() * 0.001D);
        this.lifeTime = 50;    // ticks
    }

    public void setVelocity(double x, double y, double z, float speed, float divergence) {
        Vec3d vec3d = (new Vec3d(x, y, z)).normalize().add(
                this.random.nextGaussian() * 0.007499999832361937D * (double)divergence,
                this.random.nextGaussian() * 0.007499999832361937D * (double)divergence,
                this.random.nextGaussian() * 0.007499999832361937D * (double)divergence)
                    .multiply(speed);
        this.setVelocity(vec3d);
        float f = MathHelper.sqrt(squaredHorizontalLength(vec3d));
        this.yaw = (float)(MathHelper.atan2(vec3d.x, vec3d.z) * 57.2957763671875D);
        this.pitch = (float)(MathHelper.atan2(vec3d.y, f) * 57.2957763671875D);
        this.prevYaw = this.yaw;
        this.prevPitch = this.pitch;
    }

    protected void initDataTracker() {
        this.dataTracker.startTracking(ITEM, ItemStack.EMPTY);
    }


    public void tick() {
        super.tick();

        Vec3d vec3d = this.getVelocity();
        this.move(MovementType.SELF, vec3d);
        this.setVelocity(vec3d);
        HitResult hitResult = ProjectileUtil.getCollision(this, this::entityCollisionPredicate);
        if (!this.noClip) {
            this.onCollision(hitResult);
            this.velocityDirty = true;
        }

// TODO sounds
//        this.method_26962();
//        if (this.life == 0 && !this.isSilent()) {
//            this.world.playSound((PlayerEntity)null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_FIREWORK_ROCKET_LAUNCH, SoundCategory.AMBIENT, 3.0F, 1.0F);
//        }

        ++this.life;
        if (this.world.isClient) {
            this.world.addParticle(ParticleTypes.FIREWORK, this.getX(), this.getY() - 0.3D, this.getZ(), this.random.nextGaussian() * 0.05D, -this.getVelocity().y * 0.5D, this.random.nextGaussian() * 0.05D);
        }

        ++this.life;
        if (!this.world.isClient && this.life > this.lifeTime) {
            this.explodeAndRemove();
        }

    }

    protected boolean entityCollisionPredicate(Entity entity) {
        return (!entity.isSpectator() && entity.isAlive() && entity.collides());
    }

    private void explodeAndRemove() {
        this.world.sendEntityStatus(this, (byte)17);
        this.explode();
        this.remove();
    }

    protected void onCollision(HitResult hitResult) {
        HitResult.Type type = hitResult.getType();
        if (type == HitResult.Type.ENTITY) {
            this.onEntityHit();
        } else if (type == HitResult.Type.BLOCK) {
            this.onBlockHit((BlockHitResult)hitResult);
        }
    }

    protected void onEntityHit() {
        if (!this.world.isClient) {
            this.explodeAndRemove();
        }
    }

    protected void onBlockHit(BlockHitResult blockHitResult) {
        BlockPos blockPos = new BlockPos(blockHitResult.getBlockPos());
        this.world.getBlockState(blockPos).onEntityCollision(this.world, blockPos, this);
        if (!this.world.isClient()) {
            this.explodeAndRemove();
        }
    }

    private void explode() {
        this.world.createExplosion(this, this.getX(), this.getBodyY(0.0625D), this.getZ(), 4.0F, Explosion.DestructionType.BREAK);
    }


    public void writeCustomDataToTag(CompoundTag tag) {
        tag.putInt("Life", this.life);
        tag.putInt("LifeTime", this.lifeTime);
    }

    public void readCustomDataFromTag(CompoundTag tag) {
        this.life = tag.getInt("Life");
        this.lifeTime = tag.getInt("LifeTime");
    }

    public boolean isAttackable() {
        return false;
    }

    public Packet<?> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }

    static {
        ITEM = DataTracker.registerData(net.minecraft.entity.projectile.FireworkRocketEntity.class, TrackedDataHandlerRegistry.ITEM_STACK);
    }
}
