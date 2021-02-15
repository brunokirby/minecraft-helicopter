package uk.brunokirby.helicopter_mod;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class HelicopterEntity extends AnimalEntity {
    public HelicopterEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
        System.out.println("I'm a new Helicopter!!!");
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
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

//    public static DefaultAttributeContainer.Builder createMobAttributes() {
//        return LivingEntity.createLivingAttributes().add(EntityAttributes.GENERIC_FOLLOW_RANGE, 16.0D).add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK);
//        return super.createMobAttributes();
//    }
//EntityAttributes.GENERIC_FOLLOW_RANGE, 16.0D

    public static DefaultAttributeContainer.Builder createHelicopterAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2D);
    }

    protected void initGoals() {
//        this.goalSelector.add(0, new TemptGoal(this, 1.25D, Ingredient.ofItems(Items.WHEAT), false));
//        this.goalSelector.add(0, new FarTemptGoal(this, 1.25D, Ingredient.ofItems(Items.WHEAT), false, 100));
//        this.goalSelector.add(1, new WanderAroundGoal(this, 1.0D));

        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 2.0D));
//        this.goalSelector.add(2, new FarTemptGoal(this, 2.0D, Ingredient.ofItems(Items.WHEAT), false, 100));
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 1.0D));
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(5, new LookAroundGoal(this));
    }

}
