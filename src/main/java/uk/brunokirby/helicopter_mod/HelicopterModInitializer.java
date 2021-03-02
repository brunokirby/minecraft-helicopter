package uk.brunokirby.helicopter_mod;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
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

	public static final String HELICOPTER_MOD_NAMESPACE = "helicopter_mod";

	// create using our EntityType (which we already need for server/client interaction)
	public static final EntityType<HelicopterEntity> HELICOPTER = Registry.register(
			Registry.ENTITY_TYPE,
			new Identifier(HELICOPTER_MOD_NAMESPACE, "helicopter"),
			new HelicopterEntityType()
	);


	// NB don't register an Item for Helicopter: we create a custom class

//	public static final Item CANOE_ITEM = new Item(new FabricItemSettings().group(ItemGroup.MISC));


	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		System.out.println("Hello Fabric world with a Helicopter!");

		// NB we don't need to register "Attributes" because they're specific to LivingEntity

		// register the item
		Registry.register(Registry.ITEM,
				new Identifier(HELICOPTER_MOD_NAMESPACE, "helicopter"),
				new HelicopterItem(new Item.Settings().group(ItemGroup.MISC)));
	}
}

//