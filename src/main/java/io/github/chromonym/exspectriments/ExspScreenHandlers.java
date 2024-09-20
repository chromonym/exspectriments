package io.github.chromonym.exspectriments;

import io.github.chromonym.exspectriments.screenhandlers.PrinterScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ExspScreenHandlers {
    public static final ScreenHandlerType<PrinterScreenHandler> PRINTER_SCREEN_HANDLER = Registry.register(Registries.SCREEN_HANDLER, new Identifier(Exspectriments.MOD_ID, "printer"), new ExtendedScreenHandlerType<PrinterScreenHandler>(PrinterScreenHandler::new));

    public static void initialize() {
    }
}
