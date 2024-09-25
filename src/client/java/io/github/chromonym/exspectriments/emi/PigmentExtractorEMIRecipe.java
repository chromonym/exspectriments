package io.github.chromonym.exspectriments.emi;

import java.util.List;

import de.dafuqs.spectrum.compat.emi.SpectrumEmiRecipe;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import dev.emi.emi.api.widget.TextWidget.Alignment;
import io.github.chromonym.exspectriments.ExspectrimentsEMI;
import io.github.chromonym.exspectriments.recipes.PigmentExtractorRecipe;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class PigmentExtractorEMIRecipe extends SpectrumEmiRecipe {

    public int time;
    public float reduplicationChance;
    public Text reduplicationText;

    public PigmentExtractorEMIRecipe(PigmentExtractorRecipe recipe) {
        super(ExspectrimentsEMI.PIGMENT_EXTRACTION_CATEGORY, recipe.getUnlockAdvancement(), recipe.getId(), 84, 26);
        this.inputs = List.of(EmiIngredient.of(recipe.getIngredients().get(0)));
        this.outputs = List.of(EmiStack.of(recipe.getOutput(null)));
        this.time = recipe.getCookTime();
        this.reduplicationChance = recipe.getReduplicationChance();
        this.reduplicationText = Text.translatable("emi.text.exspectriments.pigment_extraction", (reduplicationChance*100));
    }

    @Override
    public void addUnlockedWidgets(WidgetHolder widgets) {
        widgets.addSlot(inputs.get(0), 4, 4);
        widgets.addFillingArrow(26, 5, this.time*50);
        widgets.addSlot(outputs.get(0), 54, 0).recipeContext(this).large(true);
        if (Math.signum(this.reduplicationChance) == 1) {
            widgets.addText(this.reduplicationText, this.getDisplayWidth() / 2, 28, 4144959, false).horizontalAlign(Alignment.CENTER);
        }
    }

    @Override
    public int getDisplayHeight() {
        return super.getDisplayHeight() + (Math.signum(this.reduplicationChance) == 1 && this.isUnlocked() ? 10 : 0);
    }
    
    @Override
    public int getDisplayWidth() {
        if (Math.signum(this.reduplicationChance) == 1 && this.isUnlocked()) {
            MinecraftClient client = MinecraftClient.getInstance();
            return Math.max(super.getDisplayWidth(), client.textRenderer.getWidth(this.reduplicationText));
        }
        return super.getDisplayWidth();
    }
}
