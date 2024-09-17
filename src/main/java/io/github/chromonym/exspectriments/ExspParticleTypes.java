package io.github.chromonym.exspectriments;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ExspParticleTypes {
    
    public static final DefaultParticleType LIQUID_TOPAZ_SPLASH = register("liquid_topaz_splash", false);
    public static final DefaultParticleType LIQUID_TOPAZ_FISHING = register("liquid_topaz_fishing", false);
    public static final DefaultParticleType LIQUID_TOPAZ_SPARKLE = register("liquid_topaz_sparkle", false);

    public static final DefaultParticleType LIQUID_AMETHYST_SPLASH = register("liquid_amethyst_splash", false);
    public static final DefaultParticleType LIQUID_AMETHYST_FISHING = register("liquid_amethyst_fishing", false);
    public static final DefaultParticleType LIQUID_AMETHYST_SPARKLE = register("liquid_amethyst_sparkle", false);

    public static final DefaultParticleType LIQUID_CITRINE_SPLASH = register("liquid_citrine_splash", false);
    public static final DefaultParticleType LIQUID_CITRINE_FISHING = register("liquid_citrine_fishing", false);
    public static final DefaultParticleType LIQUID_CITRINE_SPARKLE = register("liquid_citrine_sparkle", false);

    public static final DefaultParticleType LIQUID_ONYX_SPLASH = register("liquid_onyx_splash", false);
    public static final DefaultParticleType LIQUID_ONYX_FISHING = register("liquid_onyx_fishing", false);
    public static final DefaultParticleType LIQUID_ONYX_SPARKLE = register("liquid_onyx_sparkle", false);

    public static final DefaultParticleType LIQUID_MOONSTONE_SPLASH = register("liquid_moonstone_splash", false);
    public static final DefaultParticleType LIQUID_MOONSTONE_FISHING = register("liquid_moonstone_fishing", false);
    public static final DefaultParticleType LIQUID_MOONSTONE_SPARKLE = register("liquid_moonstone_sparkle", false);

    public static void initialize() {}

    private static DefaultParticleType register(String name, boolean alwaysShow) {
        return (DefaultParticleType)Registry.register(Registries.PARTICLE_TYPE, new Identifier(Exspectriments.MOD_ID, name), FabricParticleTypes.simple(alwaysShow));
   }
}
