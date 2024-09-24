package io.github.chromonym.exspectriments.recipes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import de.dafuqs.spectrum.recipe.RecipeUtils;
import io.github.chromonym.exspectriments.Exspectriments;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;

public class PigmentExtractorRecipeSerializer implements RecipeSerializer<PigmentExtractorRecipe> {

    @Override
    public PigmentExtractorRecipe read(Identifier id, JsonObject json) {
        PigmentExtractorJsonFormat recipeJson = new Gson().fromJson(json, PigmentExtractorJsonFormat.class);

        if (recipeJson.input == null || recipeJson.output == null) {
            throw new JsonSyntaxException("A required attribute is missing!");
        }
        if (recipeJson.growth_time <= 0) {
            recipeJson.growth_time = 300;
        }
        if (recipeJson.reduplication_chance < 0) {
            recipeJson.reduplication_chance = 0;
        }
        if (recipeJson.reduplication_chance > 1) {
            recipeJson.reduplication_chance = 1;
        }

        Ingredient input = Ingredient.fromJson(recipeJson.input);
        ItemStack output = RecipeUtils.itemStackWithNbtFromJson(recipeJson.output);

        return new PigmentExtractorRecipe(id, input, output, recipeJson.growth_time, recipeJson.reduplication_chance);
        
    }

    @Override
    public PigmentExtractorRecipe read(Identifier id, PacketByteBuf buf) {
        Ingredient input = Ingredient.fromPacket(buf);
        int growthTime = buf.readInt();
        float reduplicationChance = buf.readFloat();
        ItemStack output = buf.readItemStack();
        return new PigmentExtractorRecipe(id, input, output, growthTime, reduplicationChance);
    }

    @Override
    public void write(PacketByteBuf buf, PigmentExtractorRecipe recipe) {
        recipe.getIngredients().get(0).write(buf);
        buf.writeInt(recipe.getCookTime());
        buf.writeFloat(recipe.getReduplicationChance());
        buf.writeItemStack(recipe.getOutput(null));
    }
    
    public class PigmentExtractorJsonFormat {
        JsonObject input;
        int growth_time;
        float reduplication_chance;
        JsonObject output;
    }

    public static final PigmentExtractorRecipeSerializer INSTANCE = new PigmentExtractorRecipeSerializer();

    // This will be the "type" field in the json
    public static final Identifier ID = new Identifier(Exspectriments.MOD_ID, "pigment_extraction");

}
