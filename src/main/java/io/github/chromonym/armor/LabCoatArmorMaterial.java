package io.github.chromonym.armor;

import net.minecraft.item.ArmorItem.Type;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class LabCoatArmorMaterial implements ArmorMaterial {

    @Override
    public int getDurability(Type type) {
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
    public int getProtection(Type type) {
        return 2;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.fromTag(TagKey.of(RegistryKeys.ITEM, new Identifier("wool")));
    }

    @Override
    public float getToughness() {
        return 0;
    }

    public static final LabCoatArmorMaterial INSTANCE = new LabCoatArmorMaterial();
    
}
