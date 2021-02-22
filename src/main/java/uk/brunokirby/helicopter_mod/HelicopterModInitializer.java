package uk.brunokirby.helicopter_mod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
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

	public static final EntityType<HelicopterEntity> HELICOPTER = Registry.register(
			Registry.ENTITY_TYPE,
			new Identifier(HELICOPTER_MOD_NAMESPACE, "helicopter"),
			FabricEntityTypeBuilder.create(SpawnGroup.MISC, HelicopterEntity::new)
					.dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
	);

	// an instance of our new item
	// TODO fix group type
    public static final Item HELICOPTER_ITEM = new Item(new FabricItemSettings().group(ItemGroup.MISC));

    @Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		System.out.println("Hello Fabric world with a Helicopter!");

		// register the entity
		FabricDefaultAttributeRegistry.register(HELICOPTER, HelicopterEntity.createHelicopterAttributes());

		// register the item
		Registry.register(Registry.ITEM,
				new Identifier(HELICOPTER_MOD_NAMESPACE, "helicopter"),
				new HelicopterItem(new Item.Settings().group(ItemGroup.MISC)));
	}
}

//