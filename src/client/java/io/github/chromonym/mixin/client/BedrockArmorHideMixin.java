package io.github.chromonym.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import de.dafuqs.spectrum.render.armor.BedrockCapeRenderer;
import io.github.apace100.cosmetic_armor.CosmeticArmor;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

@Mixin(BedrockCapeRenderer.class)
public abstract class BedrockArmorHideMixin {
    @Inject(at = @At("HEAD"), method = "renderBedrockCapeAndCloth", cancellable = true)
    private static void overrideCosmeticRender(MatrixStack ms, VertexConsumerProvider vertices, int light, AbstractClientPlayerEntity player, float h, ItemStack stack, CallbackInfo ci) {
        if (!CosmeticArmor.getCosmeticArmor(player, EquipmentSlot.CHEST).isEmpty()) {
            ci.cancel();
        }
    }
}
