package io.github.chromonym.exspectriments;

import io.github.chromonym.exspectriments.effects.*;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ExspStatusEffects {

    public static final StatusEffect CLOAKING = register(new Cloaking(), "cloaking");

    public static void initialize() {}

    public static StatusEffect register(StatusEffect effect, String id) {
        Identifier itemID = new Identifier(Exspectriments.MOD_ID, id);
        StatusEffect registeredItem = Registry.register(Registry.STATUS_EFFECT, itemID, effect);
        return registeredItem;
    }
}
