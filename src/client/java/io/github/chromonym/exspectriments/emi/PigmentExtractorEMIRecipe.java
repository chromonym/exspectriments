package io.github.chromonym.exspectriments.emi;

import java.util.List;

import de.dafuqs.spectrum.compat.emi.SpectrumEmiRecipe;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import io.github.chromonym.exspectriments.ExspectrimentsEMI;
import io.github.chromonym.exspectriments.recipes.PigmentExtractorRecipe;

public class PigmentExtractorEMIRecipe extends SpectrumEmiRecipe {

    public PigmentExtractorEMIRecipe(PigmentExtractorRecipe recipe) {
        super(ExspectrimentsEMI.PIGMENT_EXTRACTION_CATEGORY, recipe.getUnlockAdvancement(), recipe.getId(), 76, 18);
        this.inputs = List.of(EmiIngredient.of(recipe.getIngredients().get(0)));
        this.outputs = List.of(EmiStack.of(recipe.getOutput(null)));
    }

    @Override
    public void addUnlockedWidgets(WidgetHolder widgets) {
        widgets.addSlot(inputs.get(0), 0, 0);
        widgets.addSlot(outputs.get(0), 58, 0).recipeContext(this);
    }
    
}
