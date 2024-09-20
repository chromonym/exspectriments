package io.github.chromonym.exspectriments.screens;

import de.dafuqs.spectrum.energy.color.InkColors;
import io.github.chromonym.exspectriments.Exspectriments;
import io.github.chromonym.exspectriments.screenhandlers.PrinterScreenHandler;
import io.github.chromonym.exspectriments.screens.widgets.SingleInkMeterWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class PrinterScreen extends HandledScreen<PrinterScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(Exspectriments.MOD_ID, "textures/gui/container/printer.png");
    protected SingleInkMeterWidget whiteMeter;
    protected SingleInkMeterWidget cyanMeter;
    protected SingleInkMeterWidget magentaMeter;
    protected SingleInkMeterWidget yellowMeter;
    protected SingleInkMeterWidget blackMeter;

    public PrinterScreen(PrinterScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.playerInventoryTitleY += 2;
        this.titleX += 6;
        this.titleY += 1;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        //RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        //RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        //RenderSystem.setShaderTexture(0, TEXTURE);
        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);
        this.whiteMeter.draw(context);
        this.cyanMeter.draw(context);
        this.magentaMeter.draw(context);
        this.yellowMeter.draw(context);
        this.blackMeter.draw(context);
    }

    @Override
    protected void drawMouseoverTooltip(DrawContext drawContext, int x, int y) {
        if (this.whiteMeter.isMouseOver((double)x, (double)y)) {
           this.whiteMeter.drawMouseoverTooltip(drawContext, x, y);
        } else if (this.cyanMeter.isMouseOver((double)x, (double)y)) {
            this.cyanMeter.drawMouseoverTooltip(drawContext, x, y);
        } else if (this.magentaMeter.isMouseOver((double)x, (double)y)) {
            this.magentaMeter.drawMouseoverTooltip(drawContext, x, y);
        } else if (this.yellowMeter.isMouseOver((double)x, (double)y)) {
            this.yellowMeter.drawMouseoverTooltip(drawContext, x, y);
        } else if (this.blackMeter.isMouseOver((double)x, (double)y)) {
            this.blackMeter.drawMouseoverTooltip(drawContext, x, y);
        } else {
           super.drawMouseoverTooltip(drawContext, x, y);
        }
  
     }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    @Override
    protected void init() {
        super.init();
        int startX = (this.width - this.backgroundWidth) / 2;
		int startY = (this.height - this.backgroundHeight) / 2;
        this.whiteMeter = new SingleInkMeterWidget(startX+90, startY+17, 5, 16, this, this.handler.getBlockEntity(), InkColors.WHITE);
        this.cyanMeter = new SingleInkMeterWidget(startX+40, startY+49, 5, 16, this, this.handler.getBlockEntity(), InkColors.CYAN);
        this.magentaMeter = new SingleInkMeterWidget(startX+90, startY+49, 5, 16, this, this.handler.getBlockEntity(), InkColors.MAGENTA);
        this.yellowMeter = new SingleInkMeterWidget(startX+141, startY+49, 5, 16, this, this.handler.getBlockEntity(), InkColors.YELLOW);
        this.blackMeter = new SingleInkMeterWidget(startX+141, startY+17, 5, 16, this, this.handler.getBlockEntity(), InkColors.BLACK);
    }
}
