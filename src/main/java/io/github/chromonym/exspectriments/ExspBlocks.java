package io.github.chromonym.exspectriments;

import de.dafuqs.spectrum.registries.SpectrumBlockMaterials;
import de.dafuqs.spectrum.registries.SpectrumBlocks;
import io.github.chromonym.exspectriments.blocks.*;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ExspBlocks {

    //public static final PigmentPrinter PIGMENT_PRINTER = new PigmentPrinter(FabricBlockSettings.copyOf(SpectrumBlocks.POTION_WORKSHOP));
    public static final Block PRINTER_BLOCK = register(new PrinterBlock(FabricBlockSettings.copyOf(SpectrumBlocks.POTION_WORKSHOP)), "printer");

    public static final Block PIGMENT_EXTRACTOR = register(new PigmentExtractorBlock(FabricBlockSettings.copyOf(SpectrumBlocks.POTION_WORKSHOP)), "pigment_extractor");

    public static final Block LIQUID_TOPAZ = register(
        new LiquidTopazFluidBlock(ExspFluids.LIQUID_TOPAZ, SpectrumBlocks.BLAZING_CRYSTAL.getDefaultState(), fluid(MapColor.CYAN, SpectrumBlockMaterials.LIQUID_CRYSTAL).luminance((state) -> {return 11;})),
        "liquid_topaz"
    );
    public static final Block LIQUID_AMETHYST = register(
        new LiquidAmethystFluidBlock(ExspFluids.LIQUID_AMETHYST, SpectrumBlocks.BLAZING_CRYSTAL.getDefaultState(), fluid(MapColor.MAGENTA, SpectrumBlockMaterials.LIQUID_CRYSTAL).luminance((state) -> {return 11;})),
        "liquid_amethyst"
    );
    public static final Block LIQUID_CITRINE = register(
        new LiquidCitrineFluidBlock(ExspFluids.LIQUID_CITRINE, SpectrumBlocks.BLAZING_CRYSTAL.getDefaultState(), fluid(MapColor.YELLOW, SpectrumBlockMaterials.LIQUID_CRYSTAL).luminance((state) -> {return 11;})),
        "liquid_citrine"
    );
    public static final Block LIQUID_ONYX = register(
        new LiquidOnyxFluidBlock(ExspFluids.LIQUID_ONYX, SpectrumBlocks.BLAZING_CRYSTAL.getDefaultState(), fluid(MapColor.BLACK, SpectrumBlockMaterials.LIQUID_CRYSTAL).luminance((state) -> {return 11;})),
        "liquid_onyx"
    );
    public static final Block LIQUID_MOONSTONE = register(
        new LiquidMoonstoneFluidBlock(ExspFluids.LIQUID_MOONSTONE, SpectrumBlocks.BLAZING_CRYSTAL.getDefaultState(), fluid(MapColor.WHITE, SpectrumBlockMaterials.LIQUID_CRYSTAL).luminance((state) -> {return 11;})),
        "liquid_moonstone"
    );

    // code taken from spectrum - will write more compactly later
    private static AbstractBlock.Settings fluid(MapColor mapColor, Material material) {
        return settings(mapColor, 100.0F, material).breakInstantly().noCollision().dropsNothing(); //.replaceable().pistonBehavior(PistonBehavior.DESTROY).liquid()
    }

    // code taken from spectrum - will write more compactly later
    private static AbstractBlock.Settings settings(MapColor mapColor, float strength, Material material) {
        return FabricBlockSettings.of(material).mapColor(mapColor).strength(strength);
    }

    public static void initialize() {}

    public static Block register(Block item, String id) {
        Identifier itemID = new Identifier(Exspectriments.MOD_ID, id);
        Block registeredItem = Registry.register(Registry.BLOCK, itemID, item);
        return registeredItem;
    }
    
}
