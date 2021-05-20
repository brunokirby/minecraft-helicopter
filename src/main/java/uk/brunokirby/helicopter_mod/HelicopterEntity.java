package uk.brunokirby.helicopter_mod;

import com.google.common.collect.UnmodifiableIterator;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.LilyPadBlock;
import net.minecraft.class_5459;
import net.minecraft.client.input.Input;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import net.minecraft.entity.Dismounting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.system.CallbackI;

import static uk.brunokirby.helicopter_mod.HelicopterControls.KeyPress.KEY_DOWN_ARROW;
import static uk.brunokirby.helicopter_mod.HelicopterControls.KeyPress.KEY_UP_ARROW;


public class HelicopterEntity extends Entity {
    private static final TrackedData<Integer> DAMAGE_WOBBLE_TICKS;
    private static final TrackedData<Integer> DAMAGE_WOBBLE_SIDE;
    private static final TrackedData<Float> DAMAGE_WOBBLE_STRENGTH;
    private static final TrackedData<Integer> BUBBLE_WOBBLE_TICKS;
    private float ticksUnderwater;
    private float yawVelocity;
    private int field_7708;
    private double x;
    private double y;
    private double z;
    private double boatYaw;
    private double boatPitch;
    private boolean pressingLeft;
    private boolean pressingRight;
    private boolean pressingForward;
    private boolean pressingBack;
    private double waterLevel;
    private float field_7714;
    private HelicopterEntity.Location location;
    private HelicopterEntity.Location lastLocation;
    private HelicopterEntity.Flying flying = Flying.NOT_FLYING;
    private HelicopterEntity.Flying lastFlying = Flying.NOT_FLYING;
    private double fallVelocity;
    private boolean onBubbleColumnSurface;
    private boolean bubbleColumnIsDrag;
    private float bubbleWobbleStrength;
    private float bubbleWobble;
    private float lastBubbleWobble;

    public HelicopterEntity(EntityType<? extends Entity> entityType, World world) {
        super(entityType, world);
        this.inanimate = true;
        System.out.println("I'm a new Helicopter!!!");
    }

    public HelicopterEntity.Flying getFlying() { return flying; }

    public void moveTo (Vec3d position) {
        updatePosition(position.x, position.y, position.z);
        setVelocity(Vec3d.ZERO);
        prevX = position.x;
        prevY = position.y;
        prevZ = position.z;
    }

    protected float getEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return dimensions.height;
    }

    protected boolean canClimb() {
        return false;
    }

    protected void initDataTracker() {
        this.dataTracker.startTracking(DAMAGE_WOBBLE_TICKS, 0);
        this.dataTracker.startTracking(DAMAGE_WOBBLE_SIDE, 1);
        this.dataTracker.startTracking(DAMAGE_WOBBLE_STRENGTH, 0.0F);
        this.dataTracker.startTracking(BUBBLE_WOBBLE_TICKS, 0);
    }

    public boolean collidesWith(Entity other) {
        return method_30959(this, other);
    }

    public static boolean method_30959(Entity entity, Entity entity2) {
        return (entity2.isCollidable() || entity2.isPushable()) && !entity.isConnectedThroughVehicle(entity2);
    }

    public boolean isCollidable() {
        return false;
    }

    public boolean isPushable() {
        return false;
    }

    protected Vec3d method_30633(Direction.Axis axis, class_5459.class_5460 arg) {
        return LivingEntity.method_31079(super.method_30633(axis, arg));
    }

    public double getMountedHeightOffset() {
//        return 0D;
        // number is to make feet just about not visible
        return 0.111D;
    }

    public boolean damage(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else if (!this.world.isClient && !this.removed) {
            this.setDamageWobbleSide(-this.getDamageWobbleSide());
            this.setDamageWobbleTicks(10);
            this.setDamageWobbleStrength(this.getDamageWobbleStrength() + amount * 10.0F);
            this.scheduleVelocityUpdate();
            boolean bl = source.getAttacker() instanceof PlayerEntity && ((PlayerEntity)source.getAttacker()).abilities.creativeMode;
            if (bl || this.getDamageWobbleStrength() > 40.0F) {
                if (!bl && this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
                    this.dropItem(asItem());
                }

                this.remove();
            }

            return true;
        } else {
            return true;
        }
    }

    public void onBubbleColumnSurfaceCollision(boolean drag) {
        if (!this.world.isClient) {
            this.onBubbleColumnSurface = true;
            this.bubbleColumnIsDrag = drag;
            if (this.getBubbleWobbleTicks() == 0) {
                this.setBubbleWobbleTicks(60);
            }
        }

        this.world.addParticle(ParticleTypes.SPLASH, this.getX() + (double)this.random.nextFloat(), this.getY() + 0.7D, this.getZ() + (double)this.random.nextFloat(), 0.0D, 0.0D, 0.0D);
        if (this.random.nextInt(20) == 0) {
            this.world.playSound(this.getX(), this.getY(), this.getZ(), this.getSplashSound(), this.getSoundCategory(), 1.0F, 0.8F + 0.4F * this.random.nextFloat(), false);
        }

    }

    public void pushAwayFrom(Entity entity) {
        if (entity instanceof HelicopterEntity) {
            if (entity.getBoundingBox().minY < this.getBoundingBox().maxY) {
                super.pushAwayFrom(entity);
            }
        } //else if (entity.getBoundingBox().minY <= this.getBoundingBox().minY) {
//            super.pushAwayFrom(entity);
//        }

    }


    @Environment(EnvType.CLIENT)
    public void animateDamage() {
        this.setDamageWobbleSide(-this.getDamageWobbleSide());
        this.setDamageWobbleTicks(10);
        this.setDamageWobbleStrength(this.getDamageWobbleStrength() * 11.0F);
    }

    public boolean collides() {
        return !this.removed;
    }

    // ?? client guessing where things are on the server ??
    @Environment(EnvType.CLIENT)
    public void updateTrackedPositionAndAngles(double x, double y, double z, float yaw, float pitch, int interpolationSteps, boolean interpolate) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.boatYaw = yaw;
        this.boatPitch = pitch;
        this.field_7708 = 10;
    }

    public Direction getMovementDirection() {
        return this.getHorizontalFacing().rotateYClockwise();
    }

    public void tick() {
        this.lastLocation = this.location;
        this.lastFlying = this.flying;
        this.location = this.checkLocation();
        if (this.location != HelicopterEntity.Location.UNDER_WATER && this.location != HelicopterEntity.Location.UNDER_FLOWING_WATER) {
            this.ticksUnderwater = 0.0F;
        } else {
            ++this.ticksUnderwater;
        }

        if (this.location == Location.IN_AIR && this.getPrimaryPassenger() instanceof PlayerEntity) {
            this.flying = Flying.IS_FLYING;
        } else {
            this.flying = Flying.NOT_FLYING;
        }

        if (lastFlying != flying) {
            if (flying == Flying.IS_FLYING) {
                System.out.println("take-off!!!");
            } else {
                System.out.println("landing!!!");
            }
        }

        if (!this.world.isClient && this.ticksUnderwater >= 60.0F) {
            this.removeAllPassengers();
        }

        if (this.getDamageWobbleTicks() > 0) {
            this.setDamageWobbleTicks(this.getDamageWobbleTicks() - 1);
        }

        if (this.getDamageWobbleStrength() > 0.0F) {
            this.setDamageWobbleStrength(this.getDamageWobbleStrength() - 1.0F);
        }

        super.tick();
        this.method_7555();
        if (this.isLogicalSideForUpdatingMovement()) {
            this.updateVelocity();
            if (this.world.isClient) {
                this.updateMotion();
//                this.world.sendPacket(new BoatPaddleStateC2SPacket(this.isPaddleMoving(0), this.isPaddleMoving(1)));
            }

            this.move(MovementType.SELF, this.getVelocity());
        } else {
            this.setVelocity(Vec3d.ZERO);
        }

        this.handleBubbleColumn();

//        for(int i = 0; i <= 1; ++i) {
//            if (this.isPaddleMoving(i)) {
//                if (!this.isSilent() && (double)(this.paddlePhases[i] % 6.2831855F) <= 0.7853981852531433D && ((double)this.paddlePhases[i] + 0.39269909262657166D) % 6.2831854820251465D >= 0.7853981852531433D) {
//                    SoundEvent soundEvent = this.getPaddleSoundEvent();
//                    if (soundEvent != null) {
//                        Vec3d vec3d = this.getRotationVec(1.0F);
//                        double d = i == 1 ? -vec3d.z : vec3d.z;
//                        double e = i == 1 ? vec3d.x : -vec3d.x;
//                        this.world.playSound((PlayerEntity)null, this.getX() + d, this.getY(), this.getZ() + e, soundEvent, this.getSoundCategory(), 1.0F, 0.8F + 0.4F * this.random.nextFloat());
//                    }
//                }
//
//                float[] var10000 = this.paddlePhases;
//                var10000[i] = (float)((double)var10000[i] + 0.39269909262657166D);
//            } else {
//                this.paddlePhases[i] = 0.0F;
//            }
//        }

        this.checkBlockCollision();
        List<Entity> list = this.world.getOtherEntities(this, this.getBoundingBox().expand(0.2D, -0.01D, 0.2D), EntityPredicates.canBePushedBy(this));
        if (!list.isEmpty()) {
            boolean bl = !this.world.isClient && !(this.getPrimaryPassenger() instanceof PlayerEntity);

            for (Entity entity : list) {
                if (!entity.hasPassenger(this)) {
                    if (bl && this.getPassengerList().size() < 2 && !entity.hasVehicle() && entity.getWidth() < this.getWidth() && entity instanceof LivingEntity && !(entity instanceof WaterCreatureEntity) && !(entity instanceof PlayerEntity)) {
                        entity.startRiding(this);
                    } else {
                        this.pushAwayFrom(entity);
                    }
                }
            }
        }

    }

    private void handleBubbleColumn() {
        int j;
        if (this.world.isClient) {
            j = this.getBubbleWobbleTicks();
            if (j > 0) {
                this.bubbleWobbleStrength += 0.05F;
            } else {
                this.bubbleWobbleStrength -= 0.1F;
            }

            this.bubbleWobbleStrength = MathHelper.clamp(this.bubbleWobbleStrength, 0.0F, 1.0F);
            this.lastBubbleWobble = this.bubbleWobble;
            this.bubbleWobble = 10.0F * (float)Math.sin(0.5F * (float)this.world.getTime()) * this.bubbleWobbleStrength;
        } else {
            if (!this.onBubbleColumnSurface) {
                this.setBubbleWobbleTicks(0);
            }

            j = this.getBubbleWobbleTicks();
            if (j > 0) {
                --j;
                this.setBubbleWobbleTicks(j);
                int k = 60 - j - 1;
                if (k > 0 && j == 0) {
                    this.setBubbleWobbleTicks(0);
                    Vec3d vec3d = this.getVelocity();
                    if (this.bubbleColumnIsDrag) {
                        this.setVelocity(vec3d.add(0.0D, -0.7D, 0.0D));
                        this.removeAllPassengers();
                    } else {
                        this.setVelocity(vec3d.x, this.hasPassengerType(PlayerEntity.class) ? 2.7D : 0.6D, vec3d.z);
                    }
                }

                this.onBubbleColumnSurface = false;
            }
        }

    }

    @Nullable
    protected SoundEvent getPaddleSoundEvent() {
        switch(this.checkLocation()) {
            case IN_WATER:
            case UNDER_WATER:
            case UNDER_FLOWING_WATER:
                return SoundEvents.ENTITY_BOAT_PADDLE_WATER;
            case ON_LAND:
                return SoundEvents.ENTITY_BOAT_PADDLE_LAND;
            case IN_AIR:
            default:
                return null;
        }
    }

    // probably something to do with interpolation?
    private void method_7555() {
        if (this.isLogicalSideForUpdatingMovement()) {
            this.field_7708 = 0;
            this.updateTrackedPosition(this.getX(), this.getY(), this.getZ());
        }

        if (this.field_7708 > 0) {
            double d = this.getX() + (this.x - this.getX()) / (double)this.field_7708;
            double e = this.getY() + (this.y - this.getY()) / (double)this.field_7708;
            double f = this.getZ() + (this.z - this.getZ()) / (double)this.field_7708;
            double g = MathHelper.wrapDegrees(this.boatYaw - (double)this.yaw);
            this.yaw = (float)((double)this.yaw + g / (double)this.field_7708);
            this.pitch = (float)((double)this.pitch + (this.boatPitch - (double)this.pitch) / (double)this.field_7708);
            --this.field_7708;
            this.updatePosition(d, e, f);
            this.setRotation(this.yaw, this.pitch);
        }
    }

//    @Environment(EnvType.CLIENT)
//    public float interpolatePaddlePhase(int paddle, float tickDelta) {
//        return this.isPaddleMoving(paddle) ? (float)MathHelper.clampedLerp((double)this.paddlePhases[paddle] - 0.39269909262657166D, (double)this.paddlePhases[paddle], (double)tickDelta) : 0.0F;
//    }

    private HelicopterEntity.Location checkLocation() {
        HelicopterEntity.Location location = this.getUnderWaterLocation();
        if (location != null) {
            this.waterLevel = this.getBoundingBox().maxY;
            return location;
        } else if (this.checkBoatInWater()) {
            return HelicopterEntity.Location.IN_WATER;
        } else {
            float f = this.method_7548();
            if (f > 0.0F) {
                this.field_7714 = f;
                return HelicopterEntity.Location.ON_LAND;
            } else {
                return HelicopterEntity.Location.IN_AIR;
            }
        }
    }

    // something to do with water
    public float method_7544() {
        Box box = this.getBoundingBox();
        int i = MathHelper.floor(box.minX);
        int j = MathHelper.ceil(box.maxX);
        int k = MathHelper.floor(box.maxY);
        int l = MathHelper.ceil(box.maxY - this.fallVelocity);
        int m = MathHelper.floor(box.minZ);
        int n = MathHelper.ceil(box.maxZ);
        BlockPos.Mutable mutable = new BlockPos.Mutable();

        label39:
        for(int o = k; o < l; ++o) {
            float f = 0.0F;

            for(int p = i; p < j; ++p) {
                for(int q = m; q < n; ++q) {
                    mutable.set(p, o, q);
                    FluidState fluidState = this.world.getFluidState(mutable);
                    if (fluidState.isIn(FluidTags.WATER)) {
                        f = Math.max(f, fluidState.getHeight(this.world, mutable));
                    }

                    if (f >= 1.0F) {
                        continue label39;
                    }
                }
            }

            if (f < 1.0F) {
                return (float)mutable.getY() + f;
            }
        }

        return (float)(l + 1);
    }

    // something to do with lily pads
    public float method_7548() {
        Box box = this.getBoundingBox();
        Box box2 = new Box(box.minX, box.minY - 0.001D, box.minZ, box.maxX, box.minY, box.maxZ);
        int i = MathHelper.floor(box2.minX) - 1;
        int j = MathHelper.ceil(box2.maxX) + 1;
        int k = MathHelper.floor(box2.minY) - 1;
        int l = MathHelper.ceil(box2.maxY) + 1;
        int m = MathHelper.floor(box2.minZ) - 1;
        int n = MathHelper.ceil(box2.maxZ) + 1;
        VoxelShape voxelShape = VoxelShapes.cuboid(box2);
        float f = 0.0F;
        int o = 0;
        BlockPos.Mutable mutable = new BlockPos.Mutable();

        for(int p = i; p < j; ++p) {
            for(int q = m; q < n; ++q) {
                int r = (p != i && p != j - 1 ? 0 : 1) + (q != m && q != n - 1 ? 0 : 1);
                if (r != 2) {
                    for(int s = k; s < l; ++s) {
                        if (r <= 0 || s != k && s != l - 1) {
                            mutable.set(p, s, q);
                            BlockState blockState = this.world.getBlockState(mutable);
                            if (!(blockState.getBlock() instanceof LilyPadBlock) && VoxelShapes.matchesAnywhere(blockState.getCollisionShape(this.world, mutable).offset(p, s, q), voxelShape, BooleanBiFunction.AND)) {
                                f += blockState.getBlock().getSlipperiness();
                                ++o;
                            }
                        }
                    }
                }
            }
        }

        return f / (float)o;
    }

    private boolean checkBoatInWater() {
        Box box = this.getBoundingBox();
        int i = MathHelper.floor(box.minX);
        int j = MathHelper.ceil(box.maxX);
        int k = MathHelper.floor(box.minY);
        int l = MathHelper.ceil(box.minY + 0.001D);
        int m = MathHelper.floor(box.minZ);
        int n = MathHelper.ceil(box.maxZ);
        boolean bl = false;
        this.waterLevel = 4.9E-324D;
        BlockPos.Mutable mutable = new BlockPos.Mutable();

        for(int o = i; o < j; ++o) {
            for(int p = k; p < l; ++p) {
                for(int q = m; q < n; ++q) {
                    mutable.set(o, p, q);
                    FluidState fluidState = this.world.getFluidState(mutable);
                    if (fluidState.isIn(FluidTags.WATER)) {
                        float f = (float)p + fluidState.getHeight(this.world, mutable);
                        this.waterLevel = Math.max(f, this.waterLevel);
                        bl |= box.minY < (double)f;
                    }
                }
            }
        }

        return bl;
    }

    @Nullable
    private HelicopterEntity.Location getUnderWaterLocation() {
        Box box = this.getBoundingBox();
        double d = box.maxY + 0.001D;
        int i = MathHelper.floor(box.minX);
        int j = MathHelper.ceil(box.maxX);
        int k = MathHelper.floor(box.maxY);
        int l = MathHelper.ceil(d);
        int m = MathHelper.floor(box.minZ);
        int n = MathHelper.ceil(box.maxZ);
        boolean bl = false;
        BlockPos.Mutable mutable = new BlockPos.Mutable();

        for(int o = i; o < j; ++o) {
            for(int p = k; p < l; ++p) {
                for(int q = m; q < n; ++q) {
                    mutable.set(o, p, q);
                    FluidState fluidState = this.world.getFluidState(mutable);
                    if (fluidState.isIn(FluidTags.WATER) && d < (double)((float)mutable.getY() + fluidState.getHeight(this.world, mutable))) {
                        if (!fluidState.isStill()) {
                            return HelicopterEntity.Location.UNDER_FLOWING_WATER;
                        }

                        bl = true;
                    }
                }
            }
        }

        return bl ? HelicopterEntity.Location.UNDER_WATER : null;
    }

    // doing it properly ;-)
    final static float VERTICAL_ACCELERATION = 0.05F;


    private void updateVelocity() {
        double d = -0.04D;
        double e = this.hasNoGravity() ? 0.0D : -0.04D;
        double f = 0.0D;
        float velocityDecay = 0.05F;
        if (this.lastLocation == HelicopterEntity.Location.IN_AIR && this.location != HelicopterEntity.Location.IN_AIR && this.location != HelicopterEntity.Location.ON_LAND) {
            // we plopped into the water
            this.waterLevel = this.getBodyY(1.0D);
            this.updatePosition(this.getX(), (double)(this.method_7544() - this.getHeight()) + 0.101D, this.getZ());
            this.setVelocity(this.getVelocity().multiply(1.0D, 0.0D, 1.0D));
            this.fallVelocity = 0.0D;
            this.location = HelicopterEntity.Location.IN_WATER;
        } else {
            if (this.location == HelicopterEntity.Location.IN_WATER) {
                f = (this.waterLevel - this.getY()) / (double)this.getHeight();
                velocityDecay = 0.9F;
            } else if (this.location == HelicopterEntity.Location.UNDER_FLOWING_WATER) {
                e = -7.0E-4D;
                velocityDecay = 0.9F;
            } else if (this.location == HelicopterEntity.Location.UNDER_WATER) {
                f = 0.01D;
                velocityDecay = 0.45F;
            } else if (this.location == HelicopterEntity.Location.IN_AIR) {
                velocityDecay = 0.9F;
            } else if (this.location == HelicopterEntity.Location.ON_LAND) {
                velocityDecay = this.field_7714;
                if (this.getPrimaryPassenger() instanceof PlayerEntity) {
                    this.field_7714 /= 2.0F;
                }
            }

            if (this.getPrimaryPassenger() instanceof PlayerEntity) {
                // we're in the helicopter
                e = 0.0D;
            } else {
                // wot no pilot!
                // leave variables as-is, and helicopter will gradually stop + fall
            }


                // temporary
//            velocityDecay = 1.0F;

            Vec3d vec3d = this.getVelocity();
            // gradually slow down ...
            this.setVelocity(vec3d.x * (double) velocityDecay, vec3d.y * (double) velocityDecay + e, vec3d.z * (double) velocityDecay);
            this.yawVelocity *= velocityDecay;
            if (f > 0.0D) {
                Vec3d vec3d2 = this.getVelocity();
                this.setVelocity(vec3d2.x, (vec3d2.y + f * 0.06153846016296973D) * 0.75D, vec3d2.z);
            }
        }

    }


    private void updateMotion() {
        if (this.hasPassengers()) {

//            if (keyPressed_R) {
//                System.out.println("inputs=" + pressingLeft + pressingRight + pressingForward + pressingBack
//                        + (keyPressed_R ? "R" : "_"));
//            }

            float f = 0.0F;
            if (this.pressingLeft) {
                --this.yawVelocity;
            }

            if (this.pressingRight) {
                ++this.yawVelocity;
            }

            if (this.pressingRight != this.pressingLeft && !this.pressingForward && !this.pressingBack) {
                f += 0.005F;
            }

            this.yaw += this.yawVelocity;
            if (this.pressingForward) {
                f += 0.04F;
            }

            if (this.pressingBack) {
                f -= 0.005F;
            }

            // implementing vertical take-off for now
            float v = 0.0F;
            if (helicopterControlsIsPressed(KEY_UP_ARROW)) {
                v += VERTICAL_ACCELERATION;
            }
            if (helicopterControlsIsPressed(KEY_DOWN_ARROW)) {
                v -= VERTICAL_ACCELERATION;
            }

            this.setVelocity(this.getVelocity().add(
                    MathHelper.sin(-this.yaw * 0.017453292F) * f,
                    v,  // vertical speed modifier
                    MathHelper.cos(this.yaw * 0.017453292F) * f));
//            this.setPaddleMovings(this.pressingRight && !this.pressingLeft || this.pressingForward, this.pressingLeft && !this.pressingRight || this.pressingForward);
        }
    }

    public void updatePassengerPosition(Entity passenger) {
        if (this.hasPassenger(passenger)) {
            float f = 0.0F;
            float g = (float)((this.removed ? 0.009999999776482582D : this.getMountedHeightOffset()) + passenger.getHeightOffset());
            if (this.getPassengerList().size() > 1) {
                int i = this.getPassengerList().indexOf(passenger);
                if (i == 0) {
                    f = 0.2F;
                } else {
                    f = -0.6F;
                }

                if (passenger instanceof AnimalEntity) {
                    f = (float)((double)f + 0.2D);
                }
            }

            Vec3d vec3d = (new Vec3d(f, 0.0D, 0.0D)).rotateY(-this.yaw * 0.017453292F - 1.5707964F);
            passenger.updatePosition(this.getX() + vec3d.x, this.getY() + (double)g, this.getZ() + vec3d.z);
            passenger.yaw += this.yawVelocity;
            passenger.setHeadYaw(passenger.getHeadYaw() + this.yawVelocity);
            this.copyEntityData(passenger);
            if (passenger instanceof AnimalEntity && this.getPassengerList().size() > 1) {
                int j = passenger.getEntityId() % 2 == 0 ? 90 : 270;
                passenger.setYaw(((AnimalEntity)passenger).bodyYaw + (float)j);
                passenger.setHeadYaw(passenger.getHeadYaw() + (float)j);
            }

        }
    }

    public Vec3d updatePassengerForDismount(LivingEntity passenger) {
        Vec3d vec3d = getPassengerDismountOffset(this.getWidth() * MathHelper.SQUARE_ROOT_OF_TWO, passenger.getWidth(), this.yaw);
        double d = this.getX() + vec3d.x;
        double e = this.getZ() + vec3d.z;
        BlockPos blockPos = new BlockPos(d, this.getBoundingBox().maxY, e);
        BlockPos blockPos2 = blockPos.down();
        if (!this.world.isWater(blockPos2)) {
            double f = (double)blockPos.getY() + this.world.getDismountHeight(blockPos);
            double g = (double)blockPos.getY() + this.world.getDismountHeight(blockPos2);
            UnmodifiableIterator var13 = passenger.getPoses().iterator();

            while(var13.hasNext()) {
                EntityPose entityPose = (EntityPose)var13.next();
                Vec3d vec3d2 = Dismounting.findDismountPos(this.world, d, f, e, passenger, entityPose);
                if (vec3d2 != null) {
                    passenger.setPose(entityPose);
                    return vec3d2;
                }

                Vec3d vec3d3 = Dismounting.findDismountPos(this.world, d, g, e, passenger, entityPose);
                if (vec3d3 != null) {
                    passenger.setPose(entityPose);
                    return vec3d3;
                }
            }
        }

        return super.updatePassengerForDismount(passenger);
    }

    protected void copyEntityData(Entity entity) {
        entity.setYaw(this.yaw);
        float f = MathHelper.wrapDegrees(entity.yaw - this.yaw);
        float g = MathHelper.clamp(f, -105.0F, 105.0F);
        entity.prevYaw += g - f;
        entity.yaw += g - f;
        entity.setHeadYaw(entity.yaw);
    }

    @Environment(EnvType.CLIENT)
    public void onPassengerLookAround(Entity passenger) {
        this.copyEntityData(passenger);
    }

    protected void writeCustomDataToTag(CompoundTag tag) {
//        tag.putString("Type", this.getCanoeType().getName());
    }

    protected void readCustomDataFromTag(CompoundTag tag) {
//        if (tag.contains("Type", 8)) {
//            this.setCanoeType(HelicopterEntity.Type.getType(tag.getString("Type")));
//        }
    }

    public ActionResult interact(PlayerEntity player, Hand hand) {
        if (player.shouldCancelInteraction()) {
            return ActionResult.PASS;
        } else if (this.ticksUnderwater < 60.0F) {
            if (!this.world.isClient) {
                return player.startRiding(this) ? ActionResult.CONSUME : ActionResult.PASS;
            } else {
                return ActionResult.SUCCESS;
            }
        } else {
            return ActionResult.PASS;
        }
    }

    protected void fall(double heightDifference, boolean onGround, BlockState landedState, BlockPos landedPosition) {
        this.fallVelocity = this.getVelocity().y;
        if (!this.hasVehicle()) {
            if (onGround) {
                if (this.fallDistance > 3.0F) {
                    if (this.location != Location.ON_LAND) {
                        this.fallDistance = 0.0F;
                        return;
                    }

                    this.handleFallDamage(this.fallDistance, 1.0F);
                    if (!this.world.isClient && !this.removed) {
                        this.remove();
                        if (this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
                            int j;
                            // TODO drop something more interesting?
                            for(j = 0; j < 2; ++j) {
                                this.dropItem(Items.STICK);
                            }
                        }
                    }
                }

                this.fallDistance = 0.0F;
            } else if (!this.world.getFluidState(this.getBlockPos().down()).isIn(FluidTags.WATER) && heightDifference < 0.0D) {
                this.fallDistance = (float)((double)this.fallDistance - heightDifference);
            }

        }
    }

    public void setDamageWobbleStrength(float wobbleStrength) {
        this.dataTracker.set(DAMAGE_WOBBLE_STRENGTH, wobbleStrength);
    }

    public float getDamageWobbleStrength() {
        return this.dataTracker.get(DAMAGE_WOBBLE_STRENGTH);
    }

    public void setDamageWobbleTicks(int wobbleTicks) {
        this.dataTracker.set(DAMAGE_WOBBLE_TICKS, wobbleTicks);
    }

    public int getDamageWobbleTicks() {
        return this.dataTracker.get(DAMAGE_WOBBLE_TICKS);
    }

    private void setBubbleWobbleTicks(int wobbleTicks) {
        this.dataTracker.set(BUBBLE_WOBBLE_TICKS, wobbleTicks);
    }

    private int getBubbleWobbleTicks() {
        return this.dataTracker.get(BUBBLE_WOBBLE_TICKS);
    }

//    @Environment(EnvType.CLIENT)
//    public float interpolateBubbleWobble(float tickDelta) {
//        return MathHelper.lerp(tickDelta, this.lastBubbleWobble, this.bubbleWobble);
//    }

    public void setDamageWobbleSide(int side) {
        this.dataTracker.set(DAMAGE_WOBBLE_SIDE, side);
    }

    public int getDamageWobbleSide() {
        return this.dataTracker.get(DAMAGE_WOBBLE_SIDE);
    }

    protected boolean canAddPassenger(Entity passenger) {
        return this.getPassengerList().size() < 2 && !this.isSubmergedIn(FluidTags.WATER);
    }

    @Nullable
    public Entity getPrimaryPassenger() {
        List<Entity> list = this.getPassengerList();
        return list.isEmpty() ? null : list.get(0);
    }

    @Environment(EnvType.CLIENT)
    public void setInputs(boolean pressingLeft, boolean pressingRight, boolean pressingForward, boolean pressingBack) {
        this.pressingLeft = pressingLeft;
        this.pressingRight = pressingRight;
        this.pressingForward = pressingForward;
        this.pressingBack = pressingBack;
//        System.out.println("pressingLeft"+pressingLeft);
    }

    public Packet<?> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }

    public boolean isSubmergedInWater() {
        return this.location == HelicopterEntity.Location.UNDER_WATER || this.location == HelicopterEntity.Location.UNDER_FLOWING_WATER;
    }

    public boolean playerTickRiding(Input input) {
        setInputs(input.pressingLeft, input.pressingRight, input.pressingForward, input.pressingBack);

        return false;
    }

    boolean helicopterControlsIsPressed(HelicopterControls.KeyPress keyPress) {
        return HelicopterModClientInitializer.getHelicopterControls().isPressed(keyPress);
    }


    static {
        DAMAGE_WOBBLE_TICKS = DataTracker.registerData(HelicopterEntity.class, TrackedDataHandlerRegistry.INTEGER);
        DAMAGE_WOBBLE_SIDE = DataTracker.registerData(HelicopterEntity.class, TrackedDataHandlerRegistry.INTEGER);
        DAMAGE_WOBBLE_STRENGTH = DataTracker.registerData(HelicopterEntity.class, TrackedDataHandlerRegistry.FLOAT);
        BUBBLE_WOBBLE_TICKS = DataTracker.registerData(HelicopterEntity.class, TrackedDataHandlerRegistry.INTEGER);
    }


    public Item asItem() {
        return HelicopterItem.getPrototype();
    }


    public enum Location {
        IN_WATER,
        UNDER_WATER,
        UNDER_FLOWING_WATER,
        ON_LAND,
        IN_AIR
    }

    public enum Flying {
        IS_FLYING,
        NOT_FLYING
    }
}
