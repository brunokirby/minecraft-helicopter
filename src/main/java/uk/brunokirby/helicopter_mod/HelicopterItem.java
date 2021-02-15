package uk.brunokirby.helicopter_mod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.List;

import static uk.brunokirby.helicopter_mod.HelicopterModInitializer.HELICOPTER;

public class HelicopterItem extends Item {
    public HelicopterItem(Item.Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        HitResult hitResult = raycast(world, user, RaycastContext.FluidHandling.ANY);
        if (hitResult.getType() == net.minecraft.util.hit.HitResult.Type.MISS) {
            return TypedActionResult.pass(itemStack);
        } else {
//            Vec3d vec3d = user.getRotationVec(1.0F);
//            double d = 5.0D;
//            List<Entity> list = world.getOtherEntities(user, user.getBoundingBox().stretch(vec3d.multiply(5.0D)).expand(1.0D), RIDERS);
//            if (!list.isEmpty()) {
//                Vec3d vec3d2 = user.getCameraPosVec(1.0F);
//                Iterator var11 = list.iterator();
//
//                while(var11.hasNext()) {
//                    Entity entity = (Entity)var11.next();
//                    Box box = entity.getBoundingBox().expand((double)entity.getTargetingMargin());
//                    if (box.contains(vec3d2)) {
//                        return TypedActionResult.pass(itemStack);
//                    }
//                }
//            }

            if (hitResult.getType() == net.minecraft.util.hit.HitResult.Type.BLOCK) {
//                BoatEntity boatEntity = new BoatEntity(world, hitResult.getPos().x, hitResult.getPos().y, hitResult.getPos().z);
//                boatEntity.setBoatType(this.type);
//                boatEntity.yaw = user.yaw;
                HelicopterEntity helicopterEntity = new HelicopterEntity(HELICOPTER, world);

//                System.out.println("hitresult x="+hitResult.getPos().x+" y="+hitResult.getPos().y+"z="+hitResult.getPos().z);
//                System.out.println("helicopter x="+helicopterEntity.getX()+" y="+helicopterEntity.getY()+"z="+helicopterEntity.getZ());
                helicopterEntity.moveTo(hitResult.getPos());
//                System.out.println("helicopter moved x="+helicopterEntity.getX()+" y="+helicopterEntity.getY()+"z="+helicopterEntity.getZ());

                helicopterEntity.yaw = user.yaw;
                if (!world.isSpaceEmpty(helicopterEntity, helicopterEntity.getBoundingBox().expand(-0.1D))) {
                    return TypedActionResult.fail(itemStack);
                } else {
                    if (!world.isClient) {
                        world.spawnEntity(helicopterEntity);
                        if (!user.abilities.creativeMode) {
                            itemStack.decrement(1);
                        }
                    }

                    user.incrementStat(Stats.USED.getOrCreateStat(this));
                    return TypedActionResult.success(itemStack, world.isClient());
                }
            } else {
                return TypedActionResult.pass(itemStack);
            }
        }
    }

}

