package io.github.chromonym.exspectriments;

import de.dafuqs.spectrum.compat.emi.SpectrumEmiRecipeCategories;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import io.github.chromonym.exspectriments.emi.PigmentExtractorEMIRecipe;
import io.github.chromonym.exspectriments.recipes.PigmentExtractorRecipe;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ExspectrimentsEMI implements EmiPlugin {

    public static final Identifier SPRITESHEET = new Identifier(Exspectriments.MOD_ID, "textures/gui/emi_simplified_spritesheet");
    public static final EmiStack PIGMENT_EXTRACTION_WORKSTATION = EmiStack.of(ExspItems.PIGMENT_EXTRACTOR_BLOCK_ITEM);
    public static final EmiRecipeCategory PIGMENT_EXTRACTION_CATEGORY
        = new EmiRecipeCategory(new Identifier(Exspectriments.MOD_ID, "pigment_extraction"), PIGMENT_EXTRACTION_WORKSTATION, new EmiTexture(SPRITESHEET, 0, 0, 16, 16));
    public static final EmiIngredient LIQUID_GEMS = EmiIngredient.of(TagKey.of(RegistryKeys.ITEM, new Identifier(Exspectriments.MOD_ID, "liquid_gems")));

    @Override
    public void register(EmiRegistry registry) {
        registry.addWorkstation(SpectrumEmiRecipeCategories.LIQUID_CRYSTAL_CONVERTING, LIQUID_GEMS);
        registry.addCategory(PIGMENT_EXTRACTION_CATEGORY);
        registry.addWorkstation(PIGMENT_EXTRACTION_CATEGORY, PIGMENT_EXTRACTION_WORKSTATION);

        RecipeManager manager = registry.getRecipeManager();

        for (PigmentExtractorRecipe recipe : manager.listAllOfType(ExspRecipes.PIGMENT_EXTRACTOR_RECIPE)) {
            registry.addRecipe(new PigmentExtractorEMIRecipe(recipe));
        }
    }
    
}
