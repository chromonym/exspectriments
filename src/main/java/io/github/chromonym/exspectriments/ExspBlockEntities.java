package io.github.chromonym.exspectriments;

import io.github.chromonym.exspectriments.entities.PigmentExtractorBlockEntity;
import io.github.chromonym.exspectriments.entities.PrinterBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ExspBlockEntities {

    public static final BlockEntityType<PrinterBlockEntity> PRINTER_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(Exspectriments.MOD_ID, "printer"),
        //BlockEntityType.Builder.create(PrinterBlockEntity::new, ExspBlocks.PRINTER_BLOCK).build());
        FabricBlockEntityTypeBuilder.create(PrinterBlockEntity::new, ExspBlocks.PRINTER_BLOCK).build());

    public static final BlockEntityType<PigmentExtractorBlockEntity> PIGMENT_EXTRACTOR_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(Exspectriments.MOD_ID, "pigment_extractor"),
        FabricBlockEntityTypeBuilder.create(PigmentExtractorBlockEntity::new, ExspBlocks.PIGMENT_EXTRACTOR).build());

    public static void initialize() {}
}
