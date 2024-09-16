package io.github.chromonym.items;

import java.util.List;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class LabCoatTailArmorItem extends LabCoatArmorItem {
    public LabCoatTailArmorItem(ArmorMaterial material, Type type, Settings settings, Identifier texture) {
        super(material, type, settings, texture);
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("itemTooltip.exspectriments.tail").formatted(Formatting.DARK_AQUA, Formatting.ITALIC));
    }
}
