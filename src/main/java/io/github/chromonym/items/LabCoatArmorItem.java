package io.github.chromonym.items;

import org.jetbrains.annotations.NotNull;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class LabCoatArmorItem extends ArmorItem {
    @Environment(EnvType.CLIENT)
    //private BipedEntityModel<LivingEntity> model;
    private Identifier texture;

    public LabCoatArmorItem(ArmorMaterial material, Type type, Settings settings, Identifier texture) {
        super(material, type, settings);
        this.texture = texture;
    }

    /*@Environment(EnvType.CLIENT)
    public BipedEntityModel<LivingEntity> getArmorModel() {
        EntityModelLoader models = MinecraftClient.getInstance().getEntityModelLoader();
        if (this.model == null) {
            this.model = new LabCoatModel(models.getModelPart(ExspModelLayers.LAB_COAT), this.getSlotType());
        }

        return this.model;
    }

    @Environment(EnvType.CLIENT)
    public RenderLayer getRenderLayer(ItemStack stack) {
        return RenderLayer.getEntityCutout(this.texture);
    }*/

    public @NotNull Identifier getArmorTexture(ItemStack stack, EquipmentSlot slot) {
        return texture;
    }
    
}
