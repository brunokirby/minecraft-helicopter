package uk.brunokirby.helicopter_mod;

import net.fabricmc.loader.util.sat4j.core.Vec;
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
import org.lwjgl.system.CallbackI;

import static uk.brunokirby.helicopter_mod.HelicopterModInitializer.HELICOPTER_MISSILE;

public class HelicopterMissileEntity extends Entity  {
    private static final TrackedData<ItemStack> ITEM;
    private int life;
    private int lifeTime;
    private final static float speed = 3.0f;
    private final static float correctionFactor = 0.25f;

    // missile trajectory data
    // (NB also standard 'velocity' from parent)

    // direction missile is aimed towards
    Vec3d aimDirection;
    // point that missile was fired from
    Vec3d firedFrom;
    // direction that helicopter was pointing when missile was fired (i.e. initial direction of missile)
    Vec3d heliDirection;

    public HelicopterMissileEntity(EntityType<? extends Entity> entityType, World world) {
        super(entityType, world);
    }

    public HelicopterMissileEntity(World world, Vec3d position, Vec3d aimDirection, Vec3d heliDirection) {
        super(HELICOPTER_MISSILE, world);

        this.firedFrom = position;
        this.aimDirection = aimDirection.normalize();
        this.heliDirection = heliDirection.normalize();

        this.life = 0;
        this.updatePosition(position.x, position.y, position.z);
        //this.setVelocity(this.random.nextGaussian() * 0.001D, 0.05D, this.random.nextGaussian() * 0.001D);
        this.lifeTime = 50;    // ticks

        // HACK for testing
//        Vec3d velocity = aimDirection.normalize().multiply(this.speed);

        // rockets start off heading straight from helicopter
        Vec3d velocity = this.heliDirection.multiply(3.0f);
        this.setVelocity(velocity);

        this.recalculatePitchAndYaw();
    }

//    public void setVelocity(double x, double y, double z, float speed, float divergence) {
//        Vec3d vec3d = (new Vec3d(x, y, z)).normalize().add(
//                        this.random.nextGaussian() * 0.007499999832361937D * (double) divergence,
//                        this.random.nextGaussian() * 0.007499999832361937D * (double) divergence,
//                        this.random.nextGaussian() * 0.007499999832361937D * (double) divergence)
//                .multiply(speed);
//        this.setVelocity(vec3d);
//    }
    void recalculatePitchAndYaw() {
        Vec3d vec3d = getVelocity();
        float f = MathHelper.sqrt(squaredHorizontalLength(vec3d));
        this.yaw = (float)(MathHelper.atan2(vec3d.x, vec3d.z) * 57.2957763671875D);
        this.pitch = (float)(MathHelper.atan2(vec3d.y, f) * 57.2957763671875D);
        this.prevYaw = this.yaw;
        this.prevPitch = this.pitch;
    }


    protected void initDataTracker() {
        this.dataTracker.startTracking(ITEM, ItemStack.EMPTY);
    }

    Vec3d nearestPointOnTrajectory() {
        // point on line
        Vec3d hypotenuse=getPos().subtract(firedFrom);
        double dotprod = hypotenuse.dotProduct(aimDirection);
        return firedFrom.add(aimDirection.multiply(dotprod));
    }

    // rocket trajectory calculation
    void tickFlight() {
        // precondition check
        if (firedFrom == null) {
            System.out.println("WARNING no firedFrom");
            this.remove();
            return;
        }
        if (aimDirection == null) {
            System.out.println("WARNING no aimDirection");
            this.remove();
            return;
        }
        if (getPos() == null) {
            System.out.println("WARNING no getPos - what a POS");
            this.remove();
            return;
        }

        System.out.println("------");
        System.out.println("tickFlight: read Velocity="+getVelocity().toString());


        // calculate distance from aimed line
        Vec3d nearestPol = nearestPointOnTrajectory();
        double distanceFromTrajectory = getPos().subtract(nearestPol).length();
        System.out.println("distanceFromTrajectory ="+distanceFromTrajectory);

        // adjust to head towards aimed line
        Vec3d correctionDirection = nearestPol.subtract(getPos()).normalize();
        Vec3d distanceCorrection = correctionDirection.multiply(0.05 * distanceFromTrajectory);
        System.out.println("distanceCorrection ="+distanceCorrection.toString());

        // slow down if we're already heading towards the line
        double velocityTowardsLine = getVelocity().dotProduct(correctionDirection);
        System.out.println("velocityTowardsLine = "+velocityTowardsLine);
        Vec3d velocityCorrection = correctionDirection.multiply(-1 * velocityTowardsLine * 0.25);
        System.out.println("velocityCorrection ="+velocityCorrection.toString());

        Vec3d v = getVelocity();
        System.out.println("read Velocity="+v.toString());
        v = v.add(distanceCorrection).add(velocityCorrection);
        System.out.println("velocity ="+v.toString());
        // normalize velocity
        v = v.normalize().multiply(speed);
        System.out.println("velocity(normalised) ="+v.toString());
        setVelocity(v);

    }

    public void tick() {
        super.tick();

        if (!world.isClient) {
            tickFlight();
            recalculatePitchAndYaw();
            Vec3d vec3d = getVelocity();
            if (vec3d.lengthSquared() > 1.0E-7D) {
                this.setBoundingBox(this.getBoundingBox().offset(vec3d));
                this.moveToBoundingBoxCenter();
            }
       }

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
