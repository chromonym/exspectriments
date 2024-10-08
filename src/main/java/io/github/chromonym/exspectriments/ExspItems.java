package io.github.chromonym.exspectriments;

import io.github.chromonym.exspectriments.armor.*;
import io.github.chromonym.exspectriments.items.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ExspItems {

    public static final Item HOSTILE_APPROXIMATOR = register(new HostileApproximator(), "hostile_approximator");

    public static final Item LIQUID_TOPAZ_BUCKET = register(new BucketItem(ExspFluids.LIQUID_TOPAZ, new FabricItemSettings().recipeRemainder(Items.BUCKET).maxCount(1)), "liquid_topaz_bucket");
    public static final Item LIQUID_AMETHYST_BUCKET = register(new BucketItem(ExspFluids.LIQUID_AMETHYST, new FabricItemSettings().recipeRemainder(Items.BUCKET).maxCount(1)), "liquid_amethyst_bucket");
    public static final Item LIQUID_CITRINE_BUCKET = register(new BucketItem(ExspFluids.LIQUID_CITRINE, new FabricItemSettings().recipeRemainder(Items.BUCKET).maxCount(1)), "liquid_citrine_bucket");
    public static final Item LIQUID_ONYX_BUCKET = register(new BucketItem(ExspFluids.LIQUID_ONYX, new FabricItemSettings().recipeRemainder(Items.BUCKET).maxCount(1)), "liquid_onyx_bucket");
    public static final Item LIQUID_MOONSTONE_BUCKET = register(new BucketItem(ExspFluids.LIQUID_MOONSTONE, new FabricItemSettings().recipeRemainder(Items.BUCKET).maxCount(1)), "liquid_moonstone_bucket");

    public static final Item INVISIBLE_HELMET = register(new ArmorItem(InvisibleArmorMaterial.INSTANCE, ArmorItem.Type.HELMET, new FabricItemSettings()), "invisible_helmet");
    public static final Item INVISIBLE_CHESTPLATE = register(new ArmorItem(InvisibleArmorMaterial.INSTANCE, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()), "invisible_chestplate");
    public static final Item INVISIBLE_LEGGINGS = register(new ArmorItem(InvisibleArmorMaterial.INSTANCE, ArmorItem.Type.LEGGINGS, new FabricItemSettings()), "invisible_leggings");
    public static final Item INVISIBLE_BOOTS = register(new ArmorItem(InvisibleArmorMaterial.INSTANCE, ArmorItem.Type.BOOTS, new FabricItemSettings()), "invisible_boots");

    public static final Item LAB_COAT = register(new LabCoatArmorItem(LabCoatArmorMaterial.INSTANCE, ArmorItem.Type.CHESTPLATE, new FabricItemSettings(), new Identifier(Exspectriments.MOD_ID, "textures/armor/lab_coat.png")), "lab_coat");
    public static final Item LAB_COAT_TAIL = register(new LabCoatTailArmorItem(LabCoatArmorMaterial.INSTANCE, ArmorItem.Type.CHESTPLATE, new FabricItemSettings(), new Identifier(Exspectriments.MOD_ID, "textures/armor/lab_coat_tail.png")), "lab_coat_tail");
    public static final Item LAB_COAT_CMY = register(new LabCoatArmorItem(LabCoatArmorMaterial.INSTANCE, ArmorItem.Type.CHESTPLATE, new FabricItemSettings(), new Identifier(Exspectriments.MOD_ID, "textures/armor/lab_coat_cmy.png")), "lab_coat_cmy");
    public static final Item LAB_COAT_CMY_TAIL = register(new LabCoatTailArmorItem(LabCoatArmorMaterial.INSTANCE, ArmorItem.Type.CHESTPLATE, new FabricItemSettings(), new Identifier(Exspectriments.MOD_ID, "textures/armor/lab_coat_cmy_tail.png")), "lab_coat_cmy_tail");
    public static final Item LAB_COAT_ROSE = register(new LabCoatArmorItem(LabCoatArmorMaterial.INSTANCE, ArmorItem.Type.CHESTPLATE, new FabricItemSettings(), new Identifier(Exspectriments.MOD_ID, "textures/armor/lab_coat_rose.png")), "lab_coat_rose");
    public static final Item LAB_COAT_ROSE_TAIL = register(new LabCoatTailArmorItem(LabCoatArmorMaterial.INSTANCE, ArmorItem.Type.CHESTPLATE, new FabricItemSettings(), new Identifier(Exspectriments.MOD_ID, "textures/armor/lab_coat_rose_tail.png")), "lab_coat_rose_tail");

    public static final Item PRINTER_BLOCK_ITEM = register(new BlockItem(ExspBlocks.PRINTER_BLOCK, new FabricItemSettings()),"printer");
    public static final Item PIGMENT_EXTRACTOR_BLOCK_ITEM = register(new BlockItem(ExspBlocks.PIGMENT_EXTRACTOR, new FabricItemSettings()), "pigment_extractor");

    public static void initialize() {
    }

    public static Item register(Item item, String id) {
        Identifier itemID = new Identifier(Exspectriments.MOD_ID, id);
        Item registeredItem = Registry.register(Registries.ITEM, itemID, item);
        return registeredItem;
    }
}
