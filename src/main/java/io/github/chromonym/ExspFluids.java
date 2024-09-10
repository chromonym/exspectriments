package io.github.chromonym;

import de.dafuqs.spectrum.blocks.fluid.SpectrumFluid;
import io.github.chromonym.fluids.*;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ExspFluids {

    public static final SpectrumFluid LIQUID_TOPAZ = register(new LiquidTopazFluid.Still(), "liquid_topaz");
    public static final SpectrumFluid FLOWING_LIQUID_TOPAZ = register(new LiquidTopazFluid.Flowing(), "flowing_liquid_topaz");

    public static void initialize() {}

    public static FlowableFluid register(FlowableFluid item, String id) {
        Identifier itemID = new Identifier(Exspectriments.MOD_ID, id);
        FlowableFluid registeredItem = Registry.register(Registries.FLUID, itemID, item);
        return registeredItem;
    }

    public static SpectrumFluid register(SpectrumFluid item, String id) {
        Identifier itemID = new Identifier(Exspectriments.MOD_ID, id);
        SpectrumFluid registeredItem = Registry.register(Registries.FLUID, itemID, item);
        return registeredItem;
    }
}
