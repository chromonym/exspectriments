package io.github.chromonym.exspectriments.armor;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class LabCoatArmorMaterial implements ArmorMaterial {

    @Override
    public int getDurability(EquipmentSlot type) {
        return 100;
    }

    @Override
    public int getEnchantability() {
        return 10;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_LEATHER;
    }

    @Override
    public float getKnockbackResistance() {
        return 0;
    }

    @Override
    public String getName() {
        return "exsp_lab";
    }

    @Override
    public int getProtectionAmount(EquipmentSlot type) {
        return 2;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.fromTag(TagKey.of(Registry.ITEM_KEY, new Identifier("wool")));
    }

    @Override
    public float getToughness() {
        return 0;
    }

    public static final LabCoatArmorMaterial INSTANCE = new LabCoatArmorMaterial();
    
}
