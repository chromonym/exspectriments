package io.github.chromonym.exspectriments.mixin;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.chromonym.exspectriments.ExspStatusEffects;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;

@Mixin(TargetPredicate.class)
public abstract class CloakingMixin {
    @Inject(at = @At("HEAD"), method = "test", cancellable = true)
    public void cloakingOverride(@Nullable LivingEntity baseEntity, LivingEntity targetEntity, CallbackInfoReturnable<Boolean> info) {
        if (targetEntity.hasStatusEffect(ExspStatusEffects.CLOAKING)) {
            if (baseEntity != null) {
                EntityType<?> entityType = baseEntity.getType();
                if (entityType != EntityType.WITHER && entityType != EntityType.ENDER_DRAGON && entityType != EntityType.WARDEN) {
                    info.setReturnValue(false);
                }
            } else {
                info.setReturnValue(false);
            }
        }
    }
}
