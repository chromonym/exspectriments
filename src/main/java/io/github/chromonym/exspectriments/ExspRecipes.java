package io.github.chromonym.exspectriments;

import io.github.chromonym.exspectriments.recipes.PigmentExtractorRecipe;
import io.github.chromonym.exspectriments.recipes.PigmentExtractorRecipeSerializer;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ExspRecipes {

    public static final RecipeType<PigmentExtractorRecipe> PIGMENT_EXTRACTOR_RECIPE = Registry.register(Registry.RECIPE_TYPE, new Identifier(Exspectriments.MOD_ID, "pigment_extraction"), PigmentExtractorRecipe.Type.INSTANCE);

    public static final RecipeSerializer<PigmentExtractorRecipe> PIGMENT_EXTRACTOR_RECIPE_SERIALIZER = Registry.register(Registry.RECIPE_SERIALIZER, PigmentExtractorRecipeSerializer.ID, PigmentExtractorRecipeSerializer.INSTANCE);
    
    public static void initialize() {}

}
