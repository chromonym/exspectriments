package io.github.chromonym.exspectriments;

import io.github.chromonym.exspectriments.entities.PigmentExtractorBlockEntity;
import io.github.chromonym.exspectriments.entities.PrinterBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ExspBlockEntities {

    public static final BlockEntityType<PrinterBlockEntity> PRINTER_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(Exspectriments.MOD_ID, "printer"),
        //BlockEntityType.Builder.create(PrinterBlockEntity::new, ExspBlocks.PRINTER_BLOCK).build());
        FabricBlockEntityTypeBuilder.create(PrinterBlockEntity::new, ExspBlocks.PRINTER_BLOCK).build());

    public static final BlockEntityType<PigmentExtractorBlockEntity> PIGMENT_EXTRACTOR_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(Exspectriments.MOD_ID, "pigment_extractor"),
        FabricBlockEntityTypeBuilder.create(PigmentExtractorBlockEntity::new, ExspBlocks.PIGMENT_EXTRACTOR).build());

    public static void initialize() {}
}
