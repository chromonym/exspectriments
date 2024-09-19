package io.github.chromonym.exspectriments;

import de.dafuqs.spectrum.registries.SpectrumBlocks;
import io.github.chromonym.exspectriments.blocks.*;
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

    //public static final PigmentPrinter PIGMENT_PRINTER = new PigmentPrinter(FabricBlockSettings.copyOf(SpectrumBlocks.POTION_WORKSHOP));
    public static final Block PRINTER_BLOCK = register(new PrinterBlock(FabricBlockSettings.copyOf(SpectrumBlocks.POTION_WORKSHOP)), "printer");

    public static final Block LIQUID_TOPAZ = register(
        new LiquidTopazFluidBlock(ExspFluids.LIQUID_TOPAZ, SpectrumBlocks.BLAZING_CRYSTAL.getDefaultState(), fluid(MapColor.CYAN).luminance((state) -> {return 11;}).replaceable()),
        "liquid_topaz"
    );
    public static final Block LIQUID_AMETHYST = register(
        new LiquidAmethystFluidBlock(ExspFluids.LIQUID_AMETHYST, SpectrumBlocks.BLAZING_CRYSTAL.getDefaultState(), fluid(MapColor.MAGENTA).luminance((state) -> {return 11;}).replaceable()),
        "liquid_amethyst"
    );
    public static final Block LIQUID_CITRINE = register(
        new LiquidCitrineFluidBlock(ExspFluids.LIQUID_CITRINE, SpectrumBlocks.BLAZING_CRYSTAL.getDefaultState(), fluid(MapColor.YELLOW).luminance((state) -> {return 11;}).replaceable()),
        "liquid_citrine"
    );
    public static final Block LIQUID_ONYX = register(
        new LiquidOnyxFluidBlock(ExspFluids.LIQUID_ONYX, SpectrumBlocks.BLAZING_CRYSTAL.getDefaultState(), fluid(MapColor.BLACK).luminance((state) -> {return 11;}).replaceable()),
        "liquid_onyx"
    );
    public static final Block LIQUID_MOONSTONE = register(
        new LiquidMoonstoneFluidBlock(ExspFluids.LIQUID_MOONSTONE, SpectrumBlocks.BLAZING_CRYSTAL.getDefaultState(), fluid(MapColor.WHITE).luminance((state) -> {return 11;}).replaceable()),
        "liquid_moonstone"
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
