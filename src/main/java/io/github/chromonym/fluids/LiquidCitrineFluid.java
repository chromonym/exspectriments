package io.github.chromonym.fluids;

import de.dafuqs.spectrum.blocks.fluid.LiquidCrystalFluid;
import io.github.chromonym.ExspBlocks;
import io.github.chromonym.ExspFluids;
import io.github.chromonym.ExspItems;
import io.github.chromonym.ExspParticleTypes;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;

public abstract class LiquidCitrineFluid extends LiquidCrystalFluid {
    @Override
    public Fluid getStill() {
        return ExspFluids.LIQUID_CITRINE;
    }

    @Override
    public Fluid getFlowing() {
        return ExspFluids.FLOWING_LIQUID_CITRINE;
    }

    @Override
    public Item getBucketItem() {
        return ExspItems.LIQUID_CITRINE_BUCKET;
    }

    @Override
    protected BlockState toBlockState(FluidState fluidState) {
        // TO BE UPDATED
        return (BlockState)ExspBlocks.LIQUID_CITRINE.getDefaultState().with(Properties.LEVEL_15, getBlockStateLevel(fluidState));
    }

    @Override
    public boolean matchesType(Fluid fluid) {
        return fluid == ExspFluids.LIQUID_CITRINE || fluid == ExspFluids.FLOWING_LIQUID_CITRINE;
    }

    @Override
    public ParticleEffect getSplashParticle() {
        return ExspParticleTypes.LIQUID_CITRINE_SPLASH;
    }

    protected boolean isInfinite() {
        return false;
    }

    public static class Flowing extends LiquidCitrineFluid {
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

    public static class Still extends LiquidCitrineFluid {
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
