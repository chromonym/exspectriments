package io.github.chromonym.exspectriments.recipes;

import io.github.chromonym.exspectriments.ExspRecipes;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.book.CookingRecipeCategory;
import net.minecraft.util.Identifier;

public class PigmentExtractorRecipe extends AbstractCookingRecipe {

    protected final float reduplication_chance;

    public PigmentExtractorRecipe(Identifier id, Ingredient input, ItemStack output, int growthTime, float reduplication_chance) {
        super(ExspRecipes.PIGMENT_EXTRACTOR_RECIPE, id, "", CookingRecipeCategory.MISC, input, output, 0.0f, growthTime);
        this.reduplication_chance = reduplication_chance;
    }

    public float getReduplicationChance() {
        return this.reduplication_chance;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return PigmentExtractorRecipeSerializer.INSTANCE;
    }

    public static class Type implements RecipeType<PigmentExtractorRecipe> {
        private Type() {}
        public static final Type INSTANCE = new Type();
        public static final String ID = "pigment_extraction";
    }
    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }
    
}
