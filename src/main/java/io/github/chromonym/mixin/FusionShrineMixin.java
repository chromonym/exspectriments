package io.github.chromonym.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import de.dafuqs.spectrum.blocks.fusion_shrine.FusionShrineBlock;
import de.dafuqs.spectrum.blocks.fusion_shrine.FusionShrineBlockEntity;
import de.dafuqs.spectrum.recipe.fusion_shrine.FusionShrineRecipe;
import de.dafuqs.spectrum.registries.SpectrumFluids;
import io.github.chromonym.ExspFluids;

import static net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants.BUCKET;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(FusionShrineBlockEntity.class) // this is extremely dumb but here we gooo
public class FusionShrineMixin {
    @Inject(at = @At("TAIL"), method = "craft")
    private static void afterCraft(World world, BlockPos blockPos, FusionShrineBlockEntity fusionShrineBlockEntity, FusionShrineRecipe recipe, CallbackInfo info) {
        String recipeID = recipe.getId().toString();
        if (recipeID.equals("exspectriments:fusion_shrine/crystal_to_topaz")) {
            setCrystalInFusionShrine(world, blockPos, fusionShrineBlockEntity, ExspFluids.LIQUID_TOPAZ);
        } else if (recipeID.equals("exspectriments:fusion_shrine/crystal_to_amethyst")) {
            setCrystalInFusionShrine(world, blockPos, fusionShrineBlockEntity, ExspFluids.LIQUID_AMETHYST);
        } else if (recipeID.equals("exspectriments:fusion_shrine/crystal_to_citrine")) {
            setCrystalInFusionShrine(world, blockPos, fusionShrineBlockEntity, ExspFluids.LIQUID_CITRINE);
        } else if (recipeID.equals("exspectriments:fusion_shrine/crystal_to_onyx")) {
            setCrystalInFusionShrine(world, blockPos, fusionShrineBlockEntity, ExspFluids.LIQUID_ONYX);
        } else if (recipeID.equals("exspectriments:fusion_shrine/crystal_to_moonstone")) {
            setCrystalInFusionShrine(world, blockPos, fusionShrineBlockEntity, ExspFluids.LIQUID_MOONSTONE);
        } else if (recipeID.startsWith("exspectriments:fusion_shrine/to_crystal/")) {
            setCrystalInFusionShrine(world, blockPos, fusionShrineBlockEntity, SpectrumFluids.LIQUID_CRYSTAL);
        }
        // i do also now realise that i could have done this with commands. oops.
    }

    private static void setCrystalInFusionShrine(World world, BlockPos blockPos, FusionShrineBlockEntity fusionShrineBlockEntity, Fluid fluid) {
        fusionShrineBlockEntity.fluidStorage.variant = FluidVariant.of(fluid);
        fusionShrineBlockEntity.fluidStorage.amount = BUCKET;
        world.setBlockState(blockPos, (BlockState)world.getBlockState(blockPos).with(FusionShrineBlock.LIGHT_LEVEL, 11), 3);
    }
}
