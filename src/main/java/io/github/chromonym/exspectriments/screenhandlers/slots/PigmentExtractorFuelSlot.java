package io.github.chromonym.exspectriments.screenhandlers.slots;

import io.github.chromonym.exspectriments.ExspItemTags;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class PigmentExtractorFuelSlot extends Slot {
    public PigmentExtractorFuelSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return super.canInsert(stack) && (stack.isIn(ExspItemTags.PIGMENT_EXTRACTOR_FUEL) || stack.isIn(ExspItemTags.PIGMENT_EXTRACTOR_DOUBLE_FUEL));
    }
}
