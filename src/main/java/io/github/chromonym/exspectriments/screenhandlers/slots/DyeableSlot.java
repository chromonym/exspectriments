package io.github.chromonym.exspectriments.screenhandlers.slots;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class DyeableSlot extends Slot {
    public DyeableSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return super.canInsert(stack) && stack.getItem() instanceof DyeableItem;
    }
}
