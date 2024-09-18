package io.github.chromonym.exspectriments;

import com.unascribed.ears.api.EarsStateType;
import com.unascribed.ears.api.OverrideResult;
import com.unascribed.ears.api.registry.EarsStateOverriderRegistry;

import de.dafuqs.spectrum.registries.SpectrumItems;
import io.github.apace100.cosmetic_armor.CosmeticArmor;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;

public class EarsCompat {
    public static void register() {
        EarsStateOverriderRegistry.register("exspectriments", (state, peer) -> {
            PlayerEntity player = (PlayerEntity)peer;
            if (state == EarsStateType.WEARING_CHESTPLATE && CosmeticArmor.getCosmeticArmor(player, EquipmentSlot.CHEST).isOf(ExspItems.INVISIBLE_CHESTPLATE)) {
                return OverrideResult.FALSE;
            }
            if (state == EarsStateType.WEARING_CHESTPLATE && CosmeticArmor.getCosmeticArmor(player, EquipmentSlot.CHEST).isOf(ExspItems.LAB_COAT_TAIL)) {
                return OverrideResult.FALSE;
            }
            if (state == EarsStateType.WEARING_CHESTPLATE && CosmeticArmor.getCosmeticArmor(player, EquipmentSlot.CHEST).isOf(ExspItems.LAB_COAT_CMY_TAIL)) {
                return OverrideResult.FALSE;
            }
            if (state == EarsStateType.WEARING_CHESTPLATE && CosmeticArmor.getCosmeticArmor(player, EquipmentSlot.CHEST).isOf(ExspItems.LAB_COAT_ROSE_TAIL)) {
                return OverrideResult.FALSE;
            }
            if (state == EarsStateType.WEARING_HELMET && CosmeticArmor.getCosmeticArmor(player, EquipmentSlot.HEAD).isOf(ExspItems.INVISIBLE_HELMET)) {
                return OverrideResult.FALSE;
            }
            if (state == EarsStateType.WEARING_LEGGINGS && CosmeticArmor.getCosmeticArmor(player, EquipmentSlot.LEGS).isOf(ExspItems.INVISIBLE_LEGGINGS)) {
                return OverrideResult.FALSE;
            }
            if (state == EarsStateType.WEARING_BOOTS && CosmeticArmor.getCosmeticArmor(player, EquipmentSlot.FEET).isOf(ExspItems.INVISIBLE_BOOTS)) {
                return OverrideResult.FALSE;
            }
            if (state == EarsStateType.WEARING_CHESTPLATE && player.getEquippedStack(EquipmentSlot.CHEST).getItem() == SpectrumItems.BEDROCK_CHESTPLATE && !CosmeticArmor.getCosmeticArmor(player, EquipmentSlot.CHEST).isEmpty()) {
                return OverrideResult.FALSE;
            }
            if (state == EarsStateType.WEARING_CHESTPLATE && player.getEquippedStack(EquipmentSlot.CHEST).getItem() == SpectrumItems.FEROCIOUS_CHESTPLATE && !CosmeticArmor.getCosmeticArmor(player, EquipmentSlot.CHEST).isEmpty()) {
                return OverrideResult.FALSE;
            }
            return OverrideResult.DEFAULT;
        });
    }
}
