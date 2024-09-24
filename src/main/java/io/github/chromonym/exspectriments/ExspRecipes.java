package io.github.chromonym.exspectriments;

import io.github.chromonym.exspectriments.recipes.PigmentExtractorRecipe;
import io.github.chromonym.exspectriments.recipes.PigmentExtractorRecipeSerializer;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ExspRecipes {

    public static final RecipeType<PigmentExtractorRecipe> PIGMENT_EXTRACTOR_RECIPE = Registry.register(Registries.RECIPE_TYPE, new Identifier(Exspectriments.MOD_ID, "pigment_extraction"), PigmentExtractorRecipe.Type.INSTANCE);

    public static final RecipeSerializer<PigmentExtractorRecipe> PIGMENT_EXTRACTOR_RECIPE_SERIALIZER = Registry.register(Registries.RECIPE_SERIALIZER, PigmentExtractorRecipeSerializer.ID, PigmentExtractorRecipeSerializer.INSTANCE);
    
    public static void initialize() {}

}
