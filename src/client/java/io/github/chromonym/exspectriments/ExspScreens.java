package io.github.chromonym.exspectriments;

import io.github.chromonym.exspectriments.screens.PrinterScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class ExspScreens {
    public static void register() {
        HandledScreens.register(ExspScreenHandlers.PRINTER_SCREEN_HANDLER, PrinterScreen::new);
    }
}
