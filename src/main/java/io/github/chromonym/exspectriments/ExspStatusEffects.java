package io.github.chromonym.exspectriments;

import io.github.chromonym.exspectriments.effects.*;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ExspStatusEffects {

    public static final StatusEffect CLOAKING = register(new Cloaking(), "cloaking");

    public static void initialize() {}

    public static StatusEffect register(StatusEffect effect, String id) {
        Identifier itemID = new Identifier(Exspectriments.MOD_ID, id);
        StatusEffect registeredItem = Registry.register(Registries.STATUS_EFFECT, itemID, effect);
        return registeredItem;
    }
}
