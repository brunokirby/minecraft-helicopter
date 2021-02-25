package uk.brunokirby.helicopter_mod;

import com.google.common.collect.ImmutableSet;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;

public class HelicopterEntityType extends EntityType<HelicopterEntity> {
    public HelicopterEntityType() {
        // TODO we might want to tweak these, if we can work out what they mean
        super(HelicopterEntity::new, SpawnGroup.MISC,
                true, true, false, true,
                ImmutableSet.of(), EntityDimensions.changing(2.0f, 0.5f),
                5, 3);  // maxTrackDistance, trackTickInterval
    }
}