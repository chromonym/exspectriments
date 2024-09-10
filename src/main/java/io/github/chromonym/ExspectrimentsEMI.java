package io.github.chromonym;

import de.dafuqs.spectrum.compat.emi.SpectrumEmiRecipeCategories;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.stack.EmiIngredient;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ExspectrimentsEMI implements EmiPlugin {

    @Override
    public void register(EmiRegistry registry) {
        EmiIngredient liquidgems = EmiIngredient.of(TagKey.of(RegistryKeys.ITEM, new Identifier(Exspectriments.MOD_ID, "liquid_gems")));
        registry.addWorkstation(SpectrumEmiRecipeCategories.LIQUID_CRYSTAL_CONVERTING, liquidgems);
    }
    
}
