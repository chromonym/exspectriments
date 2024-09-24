package io.github.chromonym.exspectriments;

import io.github.chromonym.exspectriments.screens.PigmentExtractorScreen;
import io.github.chromonym.exspectriments.screens.PrinterScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class ExspScreens {
    public static void register() {
        HandledScreens.register(ExspScreenHandlers.PRINTER_SCREEN_HANDLER, PrinterScreen::new);
        HandledScreens.register(ExspScreenHandlers.PIGMENT_EXTRACTOR_SCREEN_HANDLER, PigmentExtractorScreen::new);
    }
}
