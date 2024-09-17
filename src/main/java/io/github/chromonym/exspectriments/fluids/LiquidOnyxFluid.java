package io.github.chromonym.exspectriments.fluids;

import de.dafuqs.spectrum.blocks.fluid.LiquidCrystalFluid;
import io.github.chromonym.exspectriments.ExspBlocks;
import io.github.chromonym.exspectriments.ExspFluids;
import io.github.chromonym.exspectriments.ExspItems;
import io.github.chromonym.exspectriments.ExspParticleTypes;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;

public abstract class LiquidOnyxFluid extends LiquidCrystalFluid {
    @Override
    public Fluid getStill() {
        return ExspFluids.LIQUID_ONYX;
    }

    @Override
    public Fluid getFlowing() {
        return ExspFluids.FLOWING_LIQUID_ONYX;
    }

    @Override
    public Item getBucketItem() {
        return ExspItems.LIQUID_ONYX_BUCKET;
    }

    @Override
    protected BlockState toBlockState(FluidState fluidState) {
        // TO BE UPDATED
        return (BlockState)ExspBlocks.LIQUID_ONYX.getDefaultState().with(Properties.LEVEL_15, getBlockStateLevel(fluidState));
    }

    @Override
    public boolean matchesType(Fluid fluid) {
        return fluid == ExspFluids.LIQUID_ONYX || fluid == ExspFluids.FLOWING_LIQUID_ONYX;
    }

    @Override
    public ParticleEffect getSplashParticle() {
        return ExspParticleTypes.LIQUID_ONYX_SPLASH;
    }

    protected boolean isInfinite() {
        return false;
    }

    public static class Flowing extends LiquidOnyxFluid {
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(new Property[]{LEVEL});
        }

        public int getLevel(FluidState fluidState) {
            return (Integer)fluidState.get(LEVEL);
        }

        public boolean isStill(FluidState fluidState) {
            return false;
        }
    }

    public static class Still extends LiquidOnyxFluid {
        @Override
        public int getLevel(FluidState state) {
            return 8;
        }

        @Override
        public boolean isStill(FluidState state) {
            return true;
        }
    }
}
