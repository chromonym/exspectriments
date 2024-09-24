package io.github.chromonym.exspectriments.screenhandlers.slots;

import net.minecraft.inventory.Inventory;
import net.minecraft.screen.slot.Slot;

public class PigmentExtractorInputSlot extends Slot {

    public PigmentExtractorInputSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public int getMaxItemCount() {
        return 1;
    }
    
}
