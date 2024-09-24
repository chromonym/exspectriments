package io.github.chromonym.exspectriments;

import io.github.chromonym.exspectriments.screenhandlers.*;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ExspScreenHandlers {
    public static final ScreenHandlerType<PrinterScreenHandler> PRINTER_SCREEN_HANDLER = Registry.register(Registries.SCREEN_HANDLER, new Identifier(Exspectriments.MOD_ID, "printer"), new ExtendedScreenHandlerType<PrinterScreenHandler>(PrinterScreenHandler::new));
    public static final ScreenHandlerType<PigmentExtractorScreenHandler> PIGMENT_EXTRACTOR_SCREEN_HANDLER = Registry.register(Registries.SCREEN_HANDLER, new Identifier(Exspectriments.MOD_ID, "pigment_extractor"), new ScreenHandlerType<PigmentExtractorScreenHandler>(PigmentExtractorScreenHandler::new, FeatureSet.empty()));

    public static void initialize() {
    }
}
