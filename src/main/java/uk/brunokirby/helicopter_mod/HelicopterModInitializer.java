package uk.brunokirby.helicopter_mod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

/*
 * "main" entrypoint e.g. in fabric.mod.json:
```
  "entrypoints": {
    "main": [
      "org.anthony.gorilla_mod.GorillaMod"
    ]
  }
```
 */
public class HelicopterModInitializer implements ModInitializer {

	public static final String HELICOPTER_MOD_NAMESPACE ="helicopter_mod";

	// summon with "/summon gorilla_mod.gorilla" (in a world with cheats enabled)
	// or use the spawn egg
	// (TODO spawn automatically in rainforest biomes?)
	public static final EntityType<HelicopterEntity> HELICOPTER = Registry.register(
			Registry.ENTITY_TYPE,
			new Identifier(HELICOPTER_MOD_NAMESPACE, "helicopter"),
			FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, HelicopterEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
	);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		System.out.println("Hello Fabric world with a Gorilla!");

		FabricDefaultAttributeRegistry.register(HELICOPTER, HelicopterEntity.createHelicopterAttributes());



		// register a spawn egg
		// (NB based on template_spawn_egg; see assets/gorilla_mod/models/item/gorilla_spawn_egg.json)
//		Registry.register(Registry.ITEM,
//				new Identifier(HELICOPTER_MOD_NAMESPACE, "gorilla_spawn_egg"),
//				new SpawnEggItem(GORILLA, 0x231709, 0x481F01, new Item.Settings().group(ItemGroup.MISC)));

	}
}

//