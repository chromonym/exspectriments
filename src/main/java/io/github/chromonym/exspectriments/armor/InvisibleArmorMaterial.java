package io.github.chromonym.exspectriments.armor;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class InvisibleArmorMaterial implements ArmorMaterial {
    @Override
    public int getDurability(EquipmentSlot type) {
        int DURABILITY_MULTIPLIER = 5;
        return switch (type) {
            case FEET -> 13 * DURABILITY_MULTIPLIER;
            case LEGS -> 15 * DURABILITY_MULTIPLIER;
            case CHEST -> 16 * DURABILITY_MULTIPLIER;
            case HEAD -> 11 * DURABILITY_MULTIPLIER;
            default -> 0;
        };
    }

    @Override
    public int getProtectionAmount(EquipmentSlot type) {
        return 1;
    }

    @Override
    public int getEnchantability() {
        return 0;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_GENERIC;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.fromTag(TagKey.of(Registry.ITEM_KEY, new Identifier("c","glass_blocks")));
    }

    @Override
    public String getName() {
        return "exsp_invisible";
    }

    @Override
    public float getToughness() {
        return 0;
    }

    public float getKnockbackResistance() {
        return 0;
    }

    public static final InvisibleArmorMaterial INSTANCE = new InvisibleArmorMaterial();
}
