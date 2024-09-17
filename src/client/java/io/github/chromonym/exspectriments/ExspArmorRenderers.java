package io.github.chromonym.exspectriments;

import io.github.chromonym.exspectriments.armor.LabCoatModel;
import io.github.chromonym.exspectriments.items.LabCoatArmorItem;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

public class ExspArmorRenderers {
    public ExspArmorRenderers() {
    }

    public static void register() {
        ArmorRenderer renderer = (matrices, vertexConsumers, stack, entity, slot, light, contextModel) -> {
            LabCoatArmorItem armor = (LabCoatArmorItem)stack.getItem();
            BipedEntityModel<LivingEntity> model = new LabCoatModel(MinecraftClient.getInstance().getEntityModelLoader().getModelPart(ExspModelLayers.LAB_COAT), armor.getSlotType());
            Identifier texture = armor.getArmorTexture(stack, slot);
            contextModel.copyBipedStateTo(model);
            ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, model, texture);
        };
        ArmorRenderer.register(renderer, ExspItems.LAB_COAT);
        ArmorRenderer.register(renderer, ExspItems.LAB_COAT_TAIL);
        ArmorRenderer.register(renderer, ExspItems.LAB_COAT_CMY);
        ArmorRenderer.register(renderer, ExspItems.LAB_COAT_CMY_TAIL);
        ArmorRenderer.register(renderer, ExspItems.LAB_COAT_ROSE);
        ArmorRenderer.register(renderer, ExspItems.LAB_COAT_ROSE_TAIL);
    }
}
