package io.github.chromonym;

import de.dafuqs.spectrum.blocks.fluid.LiquidCrystalFluidBlock;
import de.dafuqs.spectrum.registries.SpectrumBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ExspBlocks {

    public static final Block LIQUID_TOPAZ = register(
        new LiquidCrystalFluidBlock(ExspFluids.LIQUID_TOPAZ, SpectrumBlocks.BLAZING_CRYSTAL.getDefaultState(), fluid(MapColor.CYAN).luminance((state) -> {return 11;}).replaceable()),
        "liquid_topaz"
    );

    // code taken from spectrum - will write more compactly later
    private static AbstractBlock.Settings fluid(MapColor mapColor) {
        return settings(mapColor, BlockSoundGroup.INTENTIONALLY_EMPTY, 100.0F).replaceable().noCollision().pistonBehavior(PistonBehavior.DESTROY).dropsNothing().liquid();
    }

    // code taken from spectrum - will write more compactly later
    private static AbstractBlock.Settings settings(MapColor mapColor, BlockSoundGroup blockSoundGroup, float strength) {
        return FabricBlockSettings.create().mapColor(mapColor).sounds(blockSoundGroup).strength(strength);
    }

    public static void initialize() {}

    public static Block register(Block item, String id) {
        Identifier itemID = new Identifier(Exspectriments.MOD_ID, id);
        Block registeredItem = Registry.register(Registries.BLOCK, itemID, item);
        return registeredItem;
    }
    
}
