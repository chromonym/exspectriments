package io.github.chromonym.blocks;

import de.dafuqs.spectrum.blocks.fluid.LiquidCrystalFluidBlock;
import de.dafuqs.spectrum.blocks.fluid.SpectrumFluid;
import io.github.chromonym.ExspParticleTypes;
import net.minecraft.block.BlockState;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Pair;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class LiquidOnyxFluidBlock extends LiquidCrystalFluidBlock {

    public LiquidOnyxFluidBlock(SpectrumFluid fluid, BlockState ultrawarmReplacementBlockState, Settings settings) {
        super(fluid, ultrawarmReplacementBlockState, settings);
    }

    @Override
    public DefaultParticleType getSplashParticle() {
        return ExspParticleTypes.LIQUID_ONYX_FISHING;
    }

    @Override
    public Pair<DefaultParticleType, DefaultParticleType> getFishingParticles() {
        return new Pair(ExspParticleTypes.LIQUID_ONYX_SPARKLE, ExspParticleTypes.LIQUID_ONYX_FISHING);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        //super.randomDisplayTick(state, world, pos, random);
        if (random.nextFloat() < 0.1F) {
            world.addParticle(ExspParticleTypes.LIQUID_ONYX_SPARKLE, (double)pos.getX() + random.nextDouble(), (double)pos.getY() + random.nextDouble(), (double)pos.getZ() + random.nextDouble(), 0.0, random.nextDouble() * 0.1, 0.0);
        }

   }
    
}
