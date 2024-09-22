package io.github.chromonym.exspectriments.screenhandlers;

import de.dafuqs.spectrum.inventories.slots.InkStorageSlot;
import de.dafuqs.spectrum.networking.SpectrumS2CPacketSender;
import io.github.chromonym.exspectriments.ExspScreenHandlers;
import io.github.chromonym.exspectriments.entities.PrinterBlockEntity;
import io.github.chromonym.exspectriments.screenhandlers.slots.DyeableSlot;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PrinterScreenHandler extends ScreenHandler {
    protected PrinterBlockEntity printerBlockEntity;
    public final ServerPlayerEntity player;
    protected final World world;

    @Override
    public void sendContentUpdates() {
        super.sendContentUpdates();
        if (this.player != null && this.printerBlockEntity.getInkDirty()) {
           SpectrumS2CPacketSender.updateBlockEntityInk(this.printerBlockEntity.getPos(), this.printerBlockEntity.getEnergyStorage(), this.player);
        }
  
     }

    public PrinterScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        this(syncId, playerInventory, buf.readBlockPos());
    }

    public PrinterScreenHandler(int syncId, PlayerInventory playerInventory, BlockPos readBlockPos) {
        super(ExspScreenHandlers.PRINTER_SCREEN_HANDLER, syncId);
        PlayerEntity var6 = playerInventory.player;
        ServerPlayerEntity var10001;
        if (var6 instanceof ServerPlayerEntity serverPlayerEntity) {
            var10001 = serverPlayerEntity;
        } else {
            var10001 = null;
        }
        this.player = var10001;
        this.world = playerInventory.player.getWorld();
        BlockEntity blockEntity = playerInventory.player.getWorld().getBlockEntity(readBlockPos);
        if (blockEntity instanceof PrinterBlockEntity PblockEntity) {
            this.printerBlockEntity = PblockEntity;
        } else {
            throw new IllegalArgumentException("GUI called with a position where no valid BlockEntity exists");
        }
        checkSize(printerBlockEntity, 2);
        printerBlockEntity.onOpen(playerInventory.player);
        int m;
        int l;

        this.addSlot(new DyeableSlot(printerBlockEntity, 0, 43, 17));
        this.addSlot(new InkStorageSlot(printerBlockEntity, 1, 146, 13));
        /*this.addSlot(new InkStorageSlot(printerBlockEntity, 2, 122, 17));
        this.addSlot(new InkStorageSlot(printerBlockEntity, 3, 21, 49));
        this.addSlot(new InkStorageSlot(printerBlockEntity, 4, 71, 49));
        this.addSlot(new InkStorageSlot(printerBlockEntity, 5, 122, 49));*/

        // The player inventory
        for (m = 0; m < 3; ++m) {
            for (l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 84 + m * 18));
            }
        }
        // The player Hotbar
        for (m = 0; m < 9; ++m) {
            this.addSlot(new Slot(playerInventory, m, 8 + m * 18, 142));
        }

        if (this.player != null) {
            SpectrumS2CPacketSender.updateBlockEntityInk(printerBlockEntity.getPos(), this.printerBlockEntity.getEnergyStorage(), this.player);
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.printerBlockEntity.canPlayerUse(player);
    }

    public PrinterBlockEntity getBlockEntity() {
        return this.printerBlockEntity;
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.printerBlockEntity.size()) {
                if (!this.insertItem(originalStack, this.printerBlockEntity.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.printerBlockEntity.size(), false)) {
                return ItemStack.EMPTY;
            }
 
            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }
 
        return newStack;
    }
}
