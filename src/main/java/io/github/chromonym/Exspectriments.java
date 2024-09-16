package io.github.chromonym;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.mcreator.rosesutilityandloremod.init.RosesUtilityAndLoreModModItems;
import net.mcreator.rosesutilityandloremod.init.RosesUtilityAndLoreModModTabs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dafuqs.fractal.api.ItemSubGroupEvents;
import de.dafuqs.spectrum.api.item_group.ItemGroupIDs;
import de.dafuqs.spectrum.registries.SpectrumBlocks;
import de.dafuqs.spectrum.registries.SpectrumItems;

public class Exspectriments implements ModInitializer {
	public static final String MOD_ID = "exspectriments";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		ExspParticleTypes.initialize();
		ExspStatusEffects.initialize();
		ExspFluids.initialize();
		ExspItems.initialize();
		ExspBlocks.initialize();
		
		ItemSubGroupEvents.modifyEntriesEvent(ItemGroupIDs.SUBTAB_RESOURCES).register(content -> {
			content.addAfter(SpectrumBlocks.RADIATING_ENDER, ExspItems.HOSTILE_APPROXIMATOR);
			content.addAfter(SpectrumItems.LIQUID_CRYSTAL_BUCKET,
				ExspItems.LIQUID_TOPAZ_BUCKET,
				ExspItems.LIQUID_AMETHYST_BUCKET,
				ExspItems.LIQUID_CITRINE_BUCKET,
				ExspItems.LIQUID_ONYX_BUCKET,
				ExspItems.LIQUID_MOONSTONE_BUCKET
			);
		});
		ItemSubGroupEvents.modifyEntriesEvent(ItemGroupIDs.SUBTAB_EQUIPMENT).register(content -> {
			content.addAfter(SpectrumItems.NEAT_RING,
				ExspItems.INVISIBLE_HELMET,
				ExspItems.INVISIBLE_CHESTPLATE,
				ExspItems.INVISIBLE_LEGGINGS,
				ExspItems.INVISIBLE_BOOTS
			);
		});
		ItemSubGroupEvents.modifyEntriesEvent(ItemGroupIDs.SUBTAB_DECORATION).register(content -> {
			content.addAfter(SpectrumItems.MUSIC_DISC_EVERREFLECTIVE, ExspItems.LAB_COAT, ExspItems.LAB_COAT_CMY);
		});

		if (FabricLoader.getInstance().isModLoaded("roses_utility_and_lore_mod")) {
			ItemGroupEvents.modifyEntriesEvent(RosesUtilityAndLoreModModTabs.TAB_ROSES_THORNTILITIES).register(content -> {
				content.addAfter(RosesUtilityAndLoreModModItems.BUDDINGVOIDSTONE, ExspItems.LAB_COAT_ROSE);
			});
		}

		LOGGER.info("Hello Fabric world!");
	}
}