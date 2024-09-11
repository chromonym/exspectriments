package io.github.chromonym.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import de.dafuqs.spectrum.blocks.fusion_shrine.FusionShrineBlock;
import de.dafuqs.spectrum.blocks.fusion_shrine.FusionShrineBlockEntity;
import de.dafuqs.spectrum.recipe.fusion_shrine.FusionShrineRecipe;
import io.github.chromonym.ExspFluids;
import io.github.chromonym.Exspectriments;

import static net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants.BUCKET;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(FusionShrineBlockEntity.class) // this is extremely dumb but here we gooo
public class FusionShrineMixin {
    @Inject(at = @At("TAIL"), method = "craft")
    private static void afterCraft(World world, BlockPos blockPos, FusionShrineBlockEntity fusionShrineBlockEntity, FusionShrineRecipe recipe, CallbackInfo info) {
        Exspectriments.LOGGER.info(recipe.getId().toString());
        if (recipe.getId().toString().equals("exspectriments:fusion_shrine/crystal_to_topaz")) {
            fusionShrineBlockEntity.fluidStorage.variant = FluidVariant.of(ExspFluids.LIQUID_TOPAZ);
            fusionShrineBlockEntity.fluidStorage.amount = BUCKET;
            world.setBlockState(blockPos, (BlockState)world.getBlockState(blockPos).with(FusionShrineBlock.LIGHT_LEVEL, 11), 3);
        }
    }
}
