package io.github.chromonym.exspectriments.screens;

import com.mojang.blaze3d.systems.RenderSystem;

import io.github.chromonym.exspectriments.Exspectriments;
import io.github.chromonym.exspectriments.screenhandlers.PigmentExtractorScreenHandler;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class PigmentExtractorScreen extends HandledScreen<PigmentExtractorScreenHandler> {

    private static final Identifier TEXTURE = new Identifier(Exspectriments.MOD_ID, "textures/gui/container/pigment_extractor.png");

    public PigmentExtractorScreen(PigmentExtractorScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);
        int growthTimeTotal = ((PigmentExtractorScreenHandler)this.handler).getGrowthTimeTotal();
        int growthTime = ((PigmentExtractorScreenHandler)this.handler).getGrowthTime();
        if (growthTimeTotal > 0) {
            drawTexture(matrices, this.x + 55, this.y + 26, 176, 0, growthTime * 22 / growthTimeTotal, 16); //TEXTURE,
         }
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }
}
