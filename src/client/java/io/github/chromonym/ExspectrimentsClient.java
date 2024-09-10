package io.github.chromonym;

import de.dafuqs.spectrum.particle.client.LitParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.minecraft.client.particle.WaterSplashParticle;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.Identifier;

public class ExspectrimentsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.

		setupFluidRendering(ExspFluids.LIQUID_TOPAZ, ExspFluids.FLOWING_LIQUID_TOPAZ, "liquid_topaz", 0x00EEEE);
		ParticleFactoryRegistry.getInstance().register(ExspParticleTypes.LIQUID_TOPAZ_SPARKLE, LitParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(ExspParticleTypes.LIQUID_TOPAZ_SPLASH, WaterSplashParticle.SplashFactory::new);
		ParticleFactoryRegistry.getInstance().register(ExspParticleTypes.LIQUID_TOPAZ_FISHING, WaterSplashParticle.SplashFactory::new);

		setupFluidRendering(ExspFluids.LIQUID_AMETHYST, ExspFluids.FLOWING_LIQUID_AMETHYST, "liquid_amethyst", 0xEE33EE);
		ParticleFactoryRegistry.getInstance().register(ExspParticleTypes.LIQUID_AMETHYST_SPARKLE, LitParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(ExspParticleTypes.LIQUID_AMETHYST_SPLASH, WaterSplashParticle.SplashFactory::new);
		ParticleFactoryRegistry.getInstance().register(ExspParticleTypes.LIQUID_AMETHYST_FISHING, WaterSplashParticle.SplashFactory::new);

		setupFluidRendering(ExspFluids.LIQUID_CITRINE, ExspFluids.FLOWING_LIQUID_CITRINE, "liquid_citrine", 0xFFFF22);
		ParticleFactoryRegistry.getInstance().register(ExspParticleTypes.LIQUID_CITRINE_SPARKLE, LitParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(ExspParticleTypes.LIQUID_CITRINE_SPLASH, WaterSplashParticle.SplashFactory::new);
		ParticleFactoryRegistry.getInstance().register(ExspParticleTypes.LIQUID_CITRINE_FISHING, WaterSplashParticle.SplashFactory::new);
	}

	// code more or less copied from spectrum
	private static void setupFluidRendering(Fluid stillFluid, Fluid flowingFluid, String name, int tint) {
		FluidRenderHandlerRegistry.INSTANCE.register(stillFluid, flowingFluid, new SimpleFluidRenderHandler(new Identifier(Exspectriments.MOD_ID, "block/" + name + "_still"), new Identifier(Exspectriments.MOD_ID, "block/" + name + "_flow"), tint));
		BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), new Fluid[]{stillFluid, flowingFluid});
	}


}