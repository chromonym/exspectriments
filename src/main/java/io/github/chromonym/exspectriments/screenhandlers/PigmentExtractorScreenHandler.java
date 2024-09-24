package io.github.chromonym.exspectriments.screenhandlers;

import io.github.chromonym.exspectriments.ExspScreenHandlers;
import io.github.chromonym.exspectriments.screenhandlers.slots.PigmentExtractorFuelSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.world.World;

public class PigmentExtractorScreenHandler extends ScreenHandler {

    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate; // 0 = current growth time, 1 = required growth time, 2 = fuel cooldown time
    protected final World world;

    public PigmentExtractorScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(10), new ArrayPropertyDelegate(3));
    }

    public PigmentExtractorScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(ExspScreenHandlers.PIGMENT_EXTRACTOR_SCREEN_HANDLER, syncId);
        checkSize(inventory, 10);
        checkDataCount(propertyDelegate, 3);
        this.inventory = inventory;
        this.world = playerInventory.player.getWorld();
        this.propertyDelegate = propertyDelegate;
        
        this.addSlot(new Slot(inventory, 0, 26, 26));
        this.addSlot(new PigmentExtractorFuelSlot(inventory, 1, 57, 53)); // fuel slot

        int j;
        int k;

        for (j = 0; j < 2 ; ++j) {
            for (k = 0; k < 4; ++k) {
                this.addSlot(new Slot(inventory, 2 + k + j * 4, 89 + k * 18, 17 + j * 18));
            }
        }

        for(j = 0; j < 3; ++j) {
            for(k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventory, k + j * 9 + 9, 8 + k * 18, 84 + j * 18));
            }
        }

        for(j = 0; j < 9; ++j) {
            this.addSlot(new Slot(playerInventory, j, 8 + j * 18, 142));
        }

        this.addProperties(propertyDelegate);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = (Slot)this.slots.get(index);
        if (slot.hasStack()) {
            ItemStack itemStack2 = slot.getStack();
            itemStack = itemStack2.copy();
            if (index < 11) {
                if (!this.insertItem(itemStack2, 11, this.slots.size(), true)) {
                return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(itemStack2, 0, 11, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack2.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return itemStack;
    }

    public int getGrowthTime() {
        return propertyDelegate.get(0);
    }

    public int getGrowthTimeTotal() {
        return propertyDelegate.get(1);
    }

    public int getFuelCooldownTime() {
        return propertyDelegate.get(2);
    }
    
}
