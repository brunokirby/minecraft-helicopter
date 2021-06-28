package uk.brunokirby.helicopter_mod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

/*
 * "main" entrypoint e.g. in fabric.mod.json:
```
  "entrypoints": {
    "main": [
      "uk.brunokirby.helicopter_mod.HelicopterModInitializer"
    ],
    ...
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

	public static final Item HELICOPTER_ITEM_PART = new Item(new FabricItemSettings().group(ItemGroup.MISC));
	public static final Item HELICOPTER_ITEM_ROTORS = new Item(new FabricItemSettings().group(ItemGroup.MISC));
	public static final Block ALUMINIUM_ORE = new Block(FabricBlockSettings.of(Material.METAL).strength(4.0f));



	// NB don't register an Item for Helicopter: we create a custom class

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		// NB we don't need to register "Attributes" because they're specific to LivingEntity

		// register the item
		Registry.register(Registry.ITEM,
				new Identifier(HELICOPTER_MOD_NAMESPACE, "helicopter"),
				HelicopterItem.getPrototype());

		Registry.register(Registry.ITEM,new Identifier(HELICOPTER_MOD_NAMESPACE,
				"helicopter_part"), HELICOPTER_ITEM_PART);
		Registry.register(Registry.ITEM,new Identifier(HELICOPTER_MOD_NAMESPACE,
				"helicopter_rotors"), HELICOPTER_ITEM_ROTORS);
		Registry.register(Registry.BLOCK, new Identifier(HELICOPTER_MOD_NAMESPACE,
				"aluminium_ore"), ALUMINIUM_ORE);
		Registry.register(Registry.ITEM, new Identifier(HELICOPTER_MOD_NAMESPACE,
				"aluminium_ore"), new BlockItem(
						ALUMINIUM_ORE,
						new FabricItemSettings().group(ItemGroup.MISC)
		));
	}
}
