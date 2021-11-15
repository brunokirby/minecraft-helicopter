package uk.brunokirby.helicopter_mod;

import com.google.common.collect.ImmutableSet;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;

public class HelicopterMissileEntityType extends EntityType<HelicopterMissileEntity> {
    private HelicopterMissileEntityType() {
        // TODO we might want to tweak these, if we can work out what they mean
        super(HelicopterMissileEntity::new, SpawnGroup.MISC,
                true, true, false, true,
                ImmutableSet.of(), EntityDimensions.changing(2.5f, 2.5f),
                5, 3);  // maxTrackDistance, trackTickInterval
    }

    private static HelicopterMissileEntityType INSTANCE;
    public static HelicopterMissileEntityType getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new HelicopterMissileEntityType();
        }
        return INSTANCE;
    }

}