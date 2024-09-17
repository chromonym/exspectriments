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

public abstract class LiquidMoonstoneFluid extends LiquidCrystalFluid {
    @Override
    public Fluid getStill() {
        return ExspFluids.LIQUID_MOONSTONE;
    }

    @Override
    public Fluid getFlowing() {
        return ExspFluids.FLOWING_LIQUID_MOONSTONE;
    }

    @Override
    public Item getBucketItem() {
        return ExspItems.LIQUID_MOONSTONE_BUCKET;
    }

    @Override
    protected BlockState toBlockState(FluidState fluidState) {
        // TO BE UPDATED
        return (BlockState)ExspBlocks.LIQUID_MOONSTONE.getDefaultState().with(Properties.LEVEL_15, getBlockStateLevel(fluidState));
    }

    @Override
    public boolean matchesType(Fluid fluid) {
        return fluid == ExspFluids.LIQUID_MOONSTONE || fluid == ExspFluids.FLOWING_LIQUID_MOONSTONE;
    }

    @Override
    public ParticleEffect getSplashParticle() {
        return ExspParticleTypes.LIQUID_MOONSTONE_SPLASH;
    }

    protected boolean isInfinite() {
        return false;
    }

    public static class Flowing extends LiquidMoonstoneFluid {
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

    public static class Still extends LiquidMoonstoneFluid {
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
