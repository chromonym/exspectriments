package io.github.chromonym.exspectriments;

import io.github.chromonym.exspectriments.screenhandlers.PrinterScreenHandler;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ExspScreenHandlers {
    public static final ScreenHandlerType<PrinterScreenHandler> PRINTER_SCREEN_HANDLER = Registry.register(Registries.SCREEN_HANDLER, new Identifier(Exspectriments.MOD_ID, "printer"), new ScreenHandlerType<>(PrinterScreenHandler::new, FeatureFlags.VANILLA_FEATURES));

    public static void initialize() {
    }
}
