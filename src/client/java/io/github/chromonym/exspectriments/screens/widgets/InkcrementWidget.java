package io.github.chromonym.exspectriments.screens.widgets;

import de.dafuqs.spectrum.energy.color.InkColor;
import io.github.chromonym.exspectriments.Exspectriments;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class InkcrementWidget extends ClickableWidget {
    private boolean decrement;
    private InkColor inkColor;
    private int u;
    private int v;
    private boolean isClicked;
    private static final Identifier TEXTURE = new Identifier(Exspectriments.MOD_ID, "textures/gui/container/printer.png");

    public InkcrementWidget(int x, int y, boolean decrement, InkColor inkColor) {
        super(x, y, 9, 9, Text.empty());
        this.decrement = decrement;
        this.inkColor = inkColor;
        this.v = 0;
        if (decrement) {
            this.u = 185;
        } else {
            this.u = 176;
        }
    }

    @Override
    public void renderButton(DrawContext context, int mouseX, int mouseY, float delta) {
        int i = v;
        if (this.isClicked) {i += 9;}
        context.drawTexture(TEXTURE, getX(), getY(), this.u, i, 9, 9);
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        this.isClicked = true;
    }

    @Override
    public void onRelease(double mouseX, double mouseY) {
        this.isClicked = false; // not working for some reason???                                                     <-------------
    }

    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {
        return;
    }
}
