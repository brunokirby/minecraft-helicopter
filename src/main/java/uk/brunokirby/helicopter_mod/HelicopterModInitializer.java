package uk.brunokirby.helicopter_mod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

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
	public static final Item HELICOPTER_ITEM_ROTOR = new Item(new FabricItemSettings().group(ItemGroup.MISC));
	public static final Item ALUMINIUM_INGOT = new Item(new FabricItemSettings().group(ItemGroup.MISC));
 	public static final Block ALUMINIUM_ORE = new Block(FabricBlockSettings.of(Material.METAL).strength(4.0f));

	public static final Identifier FIRE_ROCKET_MESSAGE =
			new Identifier(HELICOPTER_MOD_NAMESPACE, "boom");



	// NB don't register an Item for Helicopter: we create a custom class

	private static ConfiguredFeature<?, ?> ORE_ALUMINIUM_OVERWORLD = Feature.ORE
			.configure(new OreFeatureConfig(
					OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
					ALUMINIUM_ORE.getDefaultState(),
					9)) // vein size
			.decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(
			0,0,64)))
			.spreadHorizontally()
			.repeat(10); // number of veins per chunk

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
				"helicopter_rotor"), HELICOPTER_ITEM_ROTOR);
		Registry.register(Registry.BLOCK, new Identifier(HELICOPTER_MOD_NAMESPACE,
				"aluminium_ore"), ALUMINIUM_ORE);
		Registry.register(Registry.ITEM, new Identifier(HELICOPTER_MOD_NAMESPACE,
				"aluminium_ore"), new BlockItem(
						ALUMINIUM_ORE,
						new FabricItemSettings().group(ItemGroup.MISC)
		));
		Registry.register(Registry.ITEM,
				new Identifier(HELICOPTER_MOD_NAMESPACE, "aluminium_ingot"),
				ALUMINIUM_INGOT);

		RegistryKey<ConfiguredFeature<?, ?>> oreWoolOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
				new Identifier("helicopter_mod", "ore_aluminium_overworld"));
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreWoolOverworld.getValue(), ORE_ALUMINIUM_OVERWORLD);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreWoolOverworld);


		// handler for implementing "helicopter fired a rocket"
		ServerPlayNetworking.registerGlobalReceiver(FIRE_ROCKET_MESSAGE,
				(server, player, handler, buf, responseSender) -> {

			HelicopterEntity.HelicopterRocketPacket hrp = new HelicopterEntity.HelicopterRocketPacket(buf);

			// work out the world
			// TODO this is horrid; surely there's a better way to pass the world from C->S?
			RegistryKey worldKey = null;
			if (hrp.getWorldIdentifier().equals(World.OVERWORLD.getValue())) {
				worldKey = World.OVERWORLD;
			} else if (hrp.getWorldIdentifier().equals(World.NETHER.getValue())) {
				worldKey = World.NETHER;
			} else if (hrp.getWorldIdentifier().equals(World.END.getValue())) {
				worldKey = World.END;
			}
			RegistryKey finalWorldKey = worldKey;

			System.out.println("hrp.getWorldIdentifier()="+hrp.getWorldIdentifier());
			System.out.println("worldKey.getValue()="+worldKey.getValue());

			server.execute(() -> {
				System.out.println("received C2S");

				ProjectileEntity projectileEntity = new FireworkRocketEntity(
						server.getWorld(finalWorldKey),
						ItemStack.EMPTY, // projectile,
						hrp.getPosition().x, hrp.getPosition().y, hrp.getPosition().z,
						true);

				projectileEntity.setVelocity(hrp.getDirection().x, hrp.getDirection().y, hrp.getDirection().z,
						hrp.getSpeed(), 0.0F);

		        server.getWorld(World.OVERWORLD).spawnEntity(projectileEntity);

			});
		});
	}


}
