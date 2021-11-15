package uk.brunokirby.helicopter_mod;

import com.google.common.collect.ImmutableSet;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;

public class HelicopterMissileEntityOriginalType extends EntityType<HelicopterMissileOriginalEntity> {
    public HelicopterMissileEntityOriginalType() {
        // TODO we might want to tweak these, if we can work out what they mean
        super( HelicopterMissileOriginalEntity::new, SpawnGroup.MISC,
                true, true, false, true,
                ImmutableSet.of(), EntityDimensions.changing(0.25F, 0.25F),
                4, 10);  // maxTrackDistance, trackTickInterval
//??? helicopter has  5, 3);  // maxTrackDistance, trackTickInterval
    }
}