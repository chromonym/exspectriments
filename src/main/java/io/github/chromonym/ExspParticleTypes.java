package io.github.chromonym;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ExspParticleTypes {
    
    public static final DefaultParticleType LIQUID_TOPAZ_SPLASH = register("liquid_topaz_splash", false);
    public static final DefaultParticleType LIQUID_TOPAZ_FISHING = register("liquid_topaz_fishing", false);
    public static final DefaultParticleType LIQUID_TOPAZ_SPARKLE = register("liquid_topaz_sparkle", false);


    public static void initialize() {}

    private static DefaultParticleType register(String name, boolean alwaysShow) {
        return (DefaultParticleType)Registry.register(Registries.PARTICLE_TYPE, new Identifier(Exspectriments.MOD_ID, name), FabricParticleTypes.simple(alwaysShow));
   }
}
