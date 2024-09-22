package io.github.chromonym.exspectriments.screens;

import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

import de.dafuqs.spectrum.inventories.widgets.InkMeterWidget;
import io.github.chromonym.exspectriments.ExspServerRecievers;
import io.github.chromonym.exspectriments.Exspectriments;
import io.github.chromonym.exspectriments.screenhandlers.PrinterScreenHandler;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class PrinterScreen extends HandledScreen<PrinterScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(Exspectriments.MOD_ID, "textures/gui/container/printer.png");
    /*protected SingleInkMeterWidget whiteMeter;
    protected SingleInkMeterWidget cyanMeter;
    protected SingleInkMeterWidget magentaMeter;
    protected SingleInkMeterWidget yellowMeter;
    protected SingleInkMeterWidget blackMeter;
    public InkcrementWidget whiteInc;
    public InkcrementWidget whiteDec;
    public InkcrementWidget cyanInc;
    public InkcrementWidget cyanDec;
    public InkcrementWidget magentaInc;
    public InkcrementWidget magentaDec;
    public InkcrementWidget yellowInc;
    public InkcrementWidget yellowDec;
    public InkcrementWidget blackInc;
    public InkcrementWidget blackDec;*/
    protected InkMeterWidget inkMeter;
    protected TextFieldWidget cyanField;
    protected TextFieldWidget magentaField;
    protected TextFieldWidget yellowField;
    protected TextFieldWidget blackField;
    protected int cyanAmount = 0;
    protected int magentaAmount = 0;
    protected int yellowAmount = 0;
    protected int blackAmount = 0;

    public PrinterScreen(PrinterScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        //RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        //RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        //RenderSystem.setShaderTexture(0, TEXTURE);
        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);
        /*this.whiteMeter.draw(context);
        this.cyanMeter.draw(context);
        this.magentaMeter.draw(context);
        this.yellowMeter.draw(context);
        this.blackMeter.draw(context);*/
        this.inkMeter.draw(context);
    }

    @Override
    protected void drawMouseoverTooltip(DrawContext drawContext, int x, int y) {
        /*if (this.whiteMeter.isMouseOver((double)x, (double)y)) {
           this.whiteMeter.drawMouseoverTooltip(drawContext, x, y);
        } else if (this.cyanMeter.isMouseOver((double)x, (double)y)) {
            this.cyanMeter.drawMouseoverTooltip(drawContext, x, y);
        } else if (this.magentaMeter.isMouseOver((double)x, (double)y)) {
            this.magentaMeter.drawMouseoverTooltip(drawContext, x, y);
        } else if (this.yellowMeter.isMouseOver((double)x, (double)y)) {
            this.yellowMeter.drawMouseoverTooltip(drawContext, x, y);
        } else if (this.blackMeter.isMouseOver((double)x, (double)y)) {
            this.blackMeter.drawMouseoverTooltip(drawContext, x, y);
        }*/
        if (this.inkMeter.isMouseOver((double)x, (double)y)) {
            this.inkMeter.drawMouseoverTooltip(drawContext, x, y);
        } else {
           super.drawMouseoverTooltip(drawContext, x, y);
        }
  
     }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        cyanField.render(context, mouseX, mouseY, delta);
        magentaField.render(context, mouseX, mouseY, delta);
        yellowField.render(context, mouseX, mouseY, delta);
        blackField.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    private @NotNull TextFieldWidget addTextFieldWidget(int x, int y, Text text, String defaultText, Predicate<String> textPredicate) {
        // copied from spectrum, as usual
        TextFieldWidget textFieldWidget = new TextFieldWidget(this.textRenderer, x, y, 31, 16, text);
        textFieldWidget.setTextPredicate(textPredicate);
        textFieldWidget.setFocusUnlocked(true);
        textFieldWidget.setEditableColor(-1);
        textFieldWidget.setUneditableColor(-1);
        textFieldWidget.setDrawsBackground(false);
        textFieldWidget.setMaxLength(6);
        textFieldWidget.setText(defaultText);
        textFieldWidget.setChangedListener(this::onValuesChanged);
        textFieldWidget.setEditable(true);
        this.addSelectableChild(textFieldWidget);
        return textFieldWidget;
   }

    @Override
    protected void init() {
        super.init();
        int startX = (this.width - this.backgroundWidth) / 2;
		int startY = (this.height - this.backgroundHeight) / 2;

        this.inkMeter = new InkMeterWidget(startX+140, startY+34, 40, this, this.handler.getBlockEntity());

        this.cyanField = addTextFieldWidget(startX+45, startY+42, Text.literal("Cyan"), String.valueOf(this.handler.getBlockEntity().cyanAmount), this::isPositiveWholeNumber);
        this.magentaField = addTextFieldWidget(startX+89, startY+42, Text.literal("Magenta"), String.valueOf(this.handler.getBlockEntity().magentaAmount), this::isPositiveWholeNumber);
        this.yellowField = addTextFieldWidget(startX+45, startY+59, Text.literal("Yellow"), String.valueOf(this.handler.getBlockEntity().yellowAmount), this::isPositiveWholeNumber);
        this.blackField = addTextFieldWidget(startX+89, startY+59, Text.literal("Black"), String.valueOf(this.handler.getBlockEntity().blackAmount), this::isPositiveWholeNumber);

        /*this.whiteMeter = new SingleInkMeterWidget(startX+90, startY+17, 5, 16, this, this.handler.getBlockEntity(), InkColors.WHITE);
        this.whiteInc = new InkcrementWidget(startX+97, startY+16, false, InkColors.WHITE);
        this.whiteDec = new InkcrementWidget(startX+97, startY+25, true, InkColors.WHITE);

        this.cyanMeter = new SingleInkMeterWidget(startX+40, startY+49, 5, 16, this, this.handler.getBlockEntity(), InkColors.CYAN);
        this.cyanInc = new InkcrementWidget(startX+47, startY+48, false, InkColors.CYAN);
        this.cyanDec = new InkcrementWidget(startX+47, startY+57, true, InkColors.CYAN);

        this.magentaMeter = new SingleInkMeterWidget(startX+90, startY+49, 5, 16, this, this.handler.getBlockEntity(), InkColors.MAGENTA);
        this.magentaInc = new InkcrementWidget(startX+97, startY+48, false, InkColors.MAGENTA);
        this.magentaDec = new InkcrementWidget(startX+97, startY+57, true, InkColors.MAGENTA);

        this.yellowMeter = new SingleInkMeterWidget(startX+141, startY+49, 5, 16, this, this.handler.getBlockEntity(), InkColors.YELLOW);
        this.yellowInc = new InkcrementWidget(startX+148, startY+48, false, InkColors.YELLOW);
        this.yellowDec = new InkcrementWidget(startX+148, startY+57, true, InkColors.YELLOW);

        this.blackMeter = new SingleInkMeterWidget(startX+141, startY+17, 5, 16, this, this.handler.getBlockEntity(), InkColors.BLACK);
        this.blackInc = new InkcrementWidget(startX+148, startY+16, false, InkColors.BLACK);
        this.blackDec = new InkcrementWidget(startX+148, startY+25, true, InkColors.BLACK);

        this.addDrawableChild(whiteInc);
        this.addDrawableChild(whiteDec);
        this.addDrawableChild(cyanInc);
        this.addDrawableChild(cyanDec);
        this.addDrawableChild(magentaInc);
        this.addDrawableChild(magentaDec);
        this.addDrawableChild(yellowInc);
        this.addDrawableChild(yellowDec);
        this.addDrawableChild(blackInc);
        this.addDrawableChild(blackDec);*/
    }

    protected boolean isPositiveWholeNumber(@NotNull String text) {
        return text.matches("^\\d*$");
    }

    private void onValuesChanged(String string) {
        try {
            this.cyanAmount = Integer.parseInt(this.cyanField.getText());
            if (this.cyanAmount > 100) {
                this.cyanAmount = 100;
                this.cyanField.setText("100");
            }
        } catch (NumberFormatException var3) {
            this.cyanAmount = 0;
            if (!this.cyanField.getText().isEmpty()) {this.cyanField.setText("0");}
        }

        try {
            this.magentaAmount = Integer.parseInt(this.magentaField.getText());
            if (this.magentaAmount > 100) {
                this.magentaAmount = 100;
                this.magentaField.setText("100");
            }
        } catch (NumberFormatException var3) {
            if (!this.magentaField.getText().isEmpty()) {this.magentaField.setText("0");}
            this.magentaAmount = 0;
        }

        try {
            this.yellowAmount = Integer.parseInt(this.yellowField.getText());
            if (this.yellowAmount > 100) {
                this.yellowAmount = 100;
                this.yellowField.setText("100");
            }
        } catch (NumberFormatException var3) {
            if (!this.yellowField.getText().isEmpty()) {this.yellowField.setText("0");}
            this.yellowAmount = 0;
        }

        try {
            this.blackAmount = Integer.parseInt(this.blackField.getText());
            if (this.blackAmount > 100) {
                this.blackAmount = 100;
                this.blackField.setText("100");
            }
        } catch (NumberFormatException var3) {
            if (!this.blackField.getText().isEmpty()) {this.blackField.setText("0");}
            this.blackAmount = 0;
        }

        try {
            PacketByteBuf buf = PacketByteBufs.create();
            //buf.writeBlockPos(this.handler.getBlockEntity().getPos());
            buf.writeInt(this.cyanAmount);
            buf.writeInt(this.magentaAmount);
            buf.writeInt(this.yellowAmount);
            buf.writeInt(this.blackAmount);
            ClientPlayNetworking.send(ExspServerRecievers.PRINTER_PACKET_RECIEVER, buf);
        } catch (Exception var3) {
        }
    }
}
