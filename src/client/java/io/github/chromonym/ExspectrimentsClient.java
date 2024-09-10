package io.github.chromonym;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.Identifier;

public class ExspectrimentsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		setupFluidRendering(ExspFluids.LIQUID_TOPAZ, ExspFluids.FLOWING_LIQUID_TOPAZ, "liquid_topaz", 0x00BBBB);
	}

	// code more or less copied from spectrum
	private static void setupFluidRendering(Fluid stillFluid, Fluid flowingFluid, String name, int tint) {
		FluidRenderHandlerRegistry.INSTANCE.register(stillFluid, flowingFluid, new SimpleFluidRenderHandler(new Identifier(Exspectriments.MOD_ID, "block/" + name + "_still"), new Identifier(Exspectriments.MOD_ID, "block/" + name + "_flow"), tint));
		BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), new Fluid[]{stillFluid, flowingFluid});
	}
}