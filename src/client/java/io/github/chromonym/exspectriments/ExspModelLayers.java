package io.github.chromonym.exspectriments;

import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import io.github.chromonym.exspectriments.armor.LabCoatModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ExspModelLayers {
    public static final EntityModelLayer LAB_COAT = new EntityModelLayer(new Identifier(Exspectriments.MOD_ID, "lab_coat"), "main");

    public ExspModelLayers() {}

    public static void register() {
        EntityModelLayerRegistry.register(LAB_COAT, LabCoatModel::getTexturedModelData);
    }
}
