package io.github.chromonym;

import io.github.chromonym.items.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ExspItems {

    public static final Item HOSTILE_APPROXIMATOR = register(new HostileApproximator(), "hostile_approximator");

    public static final Item LIQUID_TOPAZ_BUCKET = register(new BucketItem(ExspFluids.LIQUID_TOPAZ, new FabricItemSettings().recipeRemainder(Items.BUCKET).maxCount(1)), "liquid_topaz_bucket");
    public static final Item LIQUID_AMETHYST_BUCKET = register(new BucketItem(ExspFluids.LIQUID_AMETHYST, new FabricItemSettings().recipeRemainder(Items.BUCKET).maxCount(1)), "liquid_amethyst_bucket");
    public static final Item LIQUID_CITRINE_BUCKET = register(new BucketItem(ExspFluids.LIQUID_CITRINE, new FabricItemSettings().recipeRemainder(Items.BUCKET).maxCount(1)), "liquid_citrine_bucket");

    public static void initialize() {}

    public static Item register(Item item, String id) {
        Identifier itemID = new Identifier(Exspectriments.MOD_ID, id);
        Item registeredItem = Registry.register(Registries.ITEM, itemID, item);
        return registeredItem;
    }
}
