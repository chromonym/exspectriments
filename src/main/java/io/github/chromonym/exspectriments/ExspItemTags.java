package io.github.chromonym.exspectriments;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ExspItemTags {

    public static final TagKey<Item> PIGMENT_EXTRACTOR_FUEL = TagKey.of(RegistryKeys.ITEM, new Identifier(Exspectriments.MOD_ID, "pigment_extractor_fuel"));
    public static final TagKey<Item> PIGMENT_EXTRACTOR_DOUBLE_FUEL = TagKey.of(RegistryKeys.ITEM, new Identifier(Exspectriments.MOD_ID, "pigment_extractor_double_fuel"));
    
    public static void initialize() {
    }

}
