package io.github.chromonym.exspectriments.screenhandlers.slots;

import io.github.chromonym.exspectriments.entities.PrinterBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class PrinterOutputSlot extends Slot {
    private PrinterBlockEntity input;
    public PrinterOutputSlot(PrinterBlockEntity input, Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
        this.input = input;
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return false;
    }

    @Override
    public void onTakeItem(PlayerEntity player, ItemStack stack) {
        super.onTakeItem(player, stack);
        this.input.useInk();
        this.input.removeStack(0, stack.getCount());
    }
}
