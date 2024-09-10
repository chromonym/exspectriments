package io.github.chromonym;

import io.github.chromonym.items.HostileApproximator;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ExspItems {

    public static final Item HOSTILE_APPROXIMATOR = register(new HostileApproximator(), "hostile_approximator");

    public static void initialize() {}

    public static Item register(Item item, String id) {
        Identifier itemID = new Identifier(Exspectriments.MOD_ID, id);
        Item registeredItem = Registry.register(Registries.ITEM, itemID, item);
        return registeredItem;
    }
}
