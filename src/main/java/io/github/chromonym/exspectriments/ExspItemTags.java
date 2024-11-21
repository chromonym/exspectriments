package io.github.chromonym.exspectriments;

import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ExspItemTags {

    public static final TagKey<Item> PIGMENT_EXTRACTOR_FUEL = TagKey.of(Registry.ITEM_KEY, new Identifier(Exspectriments.MOD_ID, "pigment_extractor_fuel"));
    public static final TagKey<Item> PIGMENT_EXTRACTOR_DOUBLE_FUEL = TagKey.of(Registry.ITEM_KEY, new Identifier(Exspectriments.MOD_ID, "pigment_extractor_double_fuel"));
    
    public static void initialize() {
    }

}
