package io.github.chromonym.exspectriments.screens.widgets;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import de.dafuqs.spectrum.energy.InkStorage;
import de.dafuqs.spectrum.energy.InkStorageBlockEntity;
import de.dafuqs.spectrum.energy.color.InkColor;
import de.dafuqs.spectrum.helpers.RenderHelper;
import de.dafuqs.spectrum.helpers.Support;
import de.dafuqs.spectrum.inventories.widgets.StackedInkMeterWidget;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class SingleInkMeterWidget extends StackedInkMeterWidget {

   private InkColor inkColor;

   public SingleInkMeterWidget(int x, int y, int width, int height, Screen screen, InkStorageBlockEntity<?> blockEntity, InkColor color) {
      super(x, y, width, height, screen, blockEntity);
      this.inkColor = color;
   }

   @Override
   public void drawMouseoverTooltip(DrawContext drawContext, int x, int y) {
      MinecraftClient client = MinecraftClient.getInstance();
      List<Text> tooltip = new ArrayList();
      //Iterator var3 = blockEntity.getEnergyStorage().storedEnergy.entrySet().iterator();
      //Map.Entry<InkColor, Long> color = (Map.Entry)var3.next();
      //((IndividualCappedInkStorage)this.blockEntity.getEnergyStorage()).addTooltip(tooltip, false);
      tooltip.add(Text.translatable("spectrum.tooltip.ink_powered.bullet." + inkColor.toString().toLowerCase(Locale.ROOT), new Object[]{Support.getShortenedNumberString(this.blockEntity.getEnergyStorage().getEnergy(inkColor))}));
      //tooltip.add(Text.translatable("spectrum.tooltip.ink_powered.bullet." + ((InkColor)color.getKey()).toString().toLowerCase(Locale.ROOT), new Object[]{Support.getShortenedNumberString((Long)color.getValue())}));
      drawContext.drawTooltip(client.textRenderer, tooltip, Optional.empty(), x, y);
   }

   @Override
   public void draw(DrawContext drawContext) {
      int startHeight = this.y + this.height;
      InkStorage inkStorage = this.blockEntity.getEnergyStorage();
      long total = inkStorage.getMaxPerColor();

      //for(Iterator var7 = inkStorage.getSupportedColors().iterator(); var7.hasNext(); currentXOffset = currentXOffset + 4 + 2) {
         //InkColor inkColor = (InkColor)var7.next();
      long amount = inkStorage.getEnergy(inkColor);
      if (amount > 0L) {
         int height = Math.max(1, Math.round((float)amount / ((float)total / (float)this.height)));
         RenderHelper.fillQuad(drawContext.getMatrices(), this.x, startHeight - height, height, width, inkColor.getColor());
      }
      //}

   }
    
}
