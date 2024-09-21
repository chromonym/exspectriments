package io.github.chromonym.exspectriments.entities;

import java.util.Set;

import de.dafuqs.spectrum.blocks.ImplementedInventory;
import de.dafuqs.spectrum.energy.InkStorage;
import de.dafuqs.spectrum.energy.InkStorageBlockEntity;
import de.dafuqs.spectrum.energy.InkStorageItem;
import de.dafuqs.spectrum.energy.color.InkColor;
import de.dafuqs.spectrum.energy.color.InkColors;
import de.dafuqs.spectrum.energy.storage.IndividualCappedInkStorage;
import io.github.chromonym.exspectriments.ExspBlockEntities;
import io.github.chromonym.exspectriments.ExspBlocks;
import io.github.chromonym.exspectriments.screenhandlers.PrinterScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PrinterBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory, InkStorageBlockEntity<IndividualCappedInkStorage> {
    public DefaultedList<ItemStack> inventory = DefaultedList.ofSize(6, ItemStack.EMPTY);
    public IndividualCappedInkStorage inkStorage = new IndividualCappedInkStorage(100L, Set.of(InkColors.WHITE, InkColors.CYAN, InkColors.MAGENTA, InkColors.YELLOW, InkColors.BLACK));
    protected boolean inkDirty;

    public PrinterBlockEntity() {
        this(BlockPos.ORIGIN, ExspBlocks.PRINTER_BLOCK.getDefaultState());
    }

    public PrinterBlockEntity(BlockPos pos, BlockState state) {
        super(ExspBlockEntities.PRINTER_BLOCK_ENTITY, pos, state);
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new PrinterScreenHandler(syncId, playerInventory, this.pos);
    }

    /*public static void tick(World world, BlockPos pos, BlockState state, PrinterBlockEntity blockEntity) { // ???????
        if (!world.isClient) {
            blockEntity.inkDirty = false;
            if (world.getTime() % 12L == 0L) {
                if (
                tryTransferInk(blockEntity.inventory.get(1), blockEntity, InkColors.WHITE)||
                tryTransferInk(blockEntity.inventory.get(2), blockEntity, InkColors.BLACK)||
                tryTransferInk(blockEntity.inventory.get(3), blockEntity, InkColors.CYAN)||
                tryTransferInk(blockEntity.inventory.get(4), blockEntity, InkColors.MAGENTA)||
                tryTransferInk(blockEntity.inventory.get(5), blockEntity, InkColors.YELLOW)) {
                    blockEntity.updateInClientWorld();
                    blockEntity.setInkDirty();
                    blockEntity.markDirty();
                }
            }
        }
    }*/

    public static boolean tryTransferInk(ItemStack source, PrinterBlockEntity destination, InkColor color) {
        if (source.getItem() instanceof InkStorageItem<?> inkStorageItem) {
            InkStorage itemStorage = inkStorageItem.getEnergyStorage(source);
            if (InkStorage.transferInk(itemStorage, destination.inkStorage, color) > 0L) {
                inkStorageItem.setEnergyStorage(source, itemStorage);
                return true;
            }
        }
        return false;
    }

    public boolean incrementInk(ItemStack itemStack, InkColor color) {
        if (itemStack.getItem() instanceof InkStorageItem<?> inkStorageItem) {
            InkStorage source = inkStorageItem.getEnergyStorage(itemStack);
            InkStorage destination = this.inkStorage;
            if (destination.accepts(color) && source.accepts(color) && destination.getEnergy(color) < destination.getMaxPerColor() && source.getEnergy(color) > 0L) {
                destination.addEnergy(color, 1L);
                source.drainEnergy(color, 1L);
                return true;
            }
        }
        return false; // could not successfully increase ink
    }

    public boolean decrementInk(ItemStack itemStack, InkColor color) {
        InkStorage source = this.inkStorage;
        if (itemStack.getItem() instanceof InkStorageItem<?> inkStorageItem) {
            InkStorage destination = inkStorageItem.getEnergyStorage(itemStack);
            if (destination.accepts(color) && source.accepts(color) && destination.getEnergy(color) < destination.getMaxPerColor() && source.getEnergy(color) > 0L) {
                destination.addEnergy(color, 1L);
                source.drainEnergy(color, 1L);
                return true;
            } else if (source.accepts(color) && source.getEnergy(color) > 0L) {
                source.drainEnergy(color, 1L);
                return true;
            }
        } else {
            if (source.accepts(color) && source.getEnergy(color) > 0L) {
                source.drainEnergy(color, 1L);
                return true;
            }
        }
        return false; // could not successfully increase ink
    }

    public void updateInClientWorld() {
        /*if (this.world != null) {
            this.world.updateListeners(this.pos, this.world.getBlockState(this.pos), this.world.getBlockState(this.pos), 4);
        }*/
        ((ServerWorld) world).getChunkManager().markForUpdate(pos);
    }
    
    @Override
    public IndividualCappedInkStorage getEnergyStorage() {
        return this.inkStorage;
    }

    @Override
    public boolean getInkDirty() {
        return this.inkDirty;
    }

    @Override
    public void setInkDirty() {
        this.inkDirty = true;
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable(getCachedState().getBlock().getTranslationKey());
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, this.inventory);
        nbt.put("InkStorage", this.inkStorage.toNbt());
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, this.inventory);
        this.inkStorage = IndividualCappedInkStorage.fromNbt(nbt.getCompound("InkStorage"));
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }
    
}
