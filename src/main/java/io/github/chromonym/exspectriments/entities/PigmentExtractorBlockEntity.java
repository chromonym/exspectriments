package io.github.chromonym.exspectriments.entities;

import java.util.Iterator;

import de.dafuqs.spectrum.helpers.InventoryHelper;
import io.github.chromonym.exspectriments.ExspBlockEntities;
import io.github.chromonym.exspectriments.ExspBlocks;
import io.github.chromonym.exspectriments.ExspItemTags;
import io.github.chromonym.exspectriments.ExspRecipes;
import io.github.chromonym.exspectriments.recipes.PigmentExtractorRecipe;
import io.github.chromonym.exspectriments.screenhandlers.PigmentExtractorScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.recipe.RecipeInputProvider;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.recipe.RecipeMatcher;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class PigmentExtractorBlockEntity extends LockableContainerBlockEntity implements SidedInventory, RecipeInputProvider {
    
    protected DefaultedList<ItemStack> inventory;
    protected static final int TREE_SLOT_INDEX = 0;
    protected static final int FUEL_SLOT_INDEX = 1;
    private static final int[] BOTTOM_SLOTS = new int[]{2, 3, 4, 5, 6, 7, 8, 9};
    private static final int[] INPUT_SLOTS = new int[]{1};
    protected final PropertyDelegate propertyDelegate;
    int growthTime;
    int growthTimeTotal;
    int fuelCooldownTime;
    private final RecipeManager.MatchGetter<Inventory, PigmentExtractorRecipe> matchGetter;

    public PigmentExtractorBlockEntity() {
        this(BlockPos.ORIGIN, ExspBlocks.PIGMENT_EXTRACTOR.getDefaultState());
    }

    public PigmentExtractorBlockEntity(BlockPos pos, BlockState state) {
        super(ExspBlockEntities.PIGMENT_EXTRACTOR_BLOCK_ENTITY, pos, state);
        this.inventory = DefaultedList.ofSize(10, ItemStack.EMPTY);
        this.matchGetter = RecipeManager.createCachedMatchGetter(ExspRecipes.PIGMENT_EXTRACTOR_RECIPE);
        this.propertyDelegate = new PropertyDelegate() {
			@Override
			public int get(int index) {
				if (index == 0) {
					return PigmentExtractorBlockEntity.this.growthTime;
				} else if (index == 1) {
                    return PigmentExtractorBlockEntity.this.growthTimeTotal;
                }
				return PigmentExtractorBlockEntity.this.fuelCooldownTime;
			}
			
			@Override
			public void set(int index, int value) {
				switch (index) {
					case 0 -> PigmentExtractorBlockEntity.this.growthTime = value;
					case 1 -> PigmentExtractorBlockEntity.this.growthTimeTotal = value;
                    case 2 -> PigmentExtractorBlockEntity.this.fuelCooldownTime = value;
				}
			}
			
			@Override
			public int size() {
				return 3;
			}
		};
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return Inventory.canPlayerUse(this, player);
    }

    @Override
    public ItemStack getStack(int slot) {
        return (ItemStack)this.inventory.get(slot);
    }

    @Override
    public boolean isEmpty() {
        Iterator<ItemStack> var1 = this.inventory.iterator();

        ItemStack itemStack;
        do {
            if (!var1.hasNext()) {
                return true;
            }

            itemStack = (ItemStack)var1.next();
        } while(itemStack.isEmpty());

        return false;
    }

    @Override
    public ItemStack removeStack(int slot) {
        return Inventories.removeStack(this.inventory, slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        return Inventories.splitStack(this.inventory, slot, amount);
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        ItemStack itemStack = (ItemStack)this.inventory.get(slot);
        boolean bl = !stack.isEmpty() && ItemStack.canCombine(itemStack, stack);
        this.inventory.set(slot, stack);
        if (stack.getCount() > this.getMaxCountPerStack()) {
            stack.setCount(this.getMaxCountPerStack());
        }

        if (slot == 0 && !bl) {
            this.growthTimeTotal = getCookTime(this.world, this);
            this.growthTime = 0;
            this.markDirty();
            world.updateListeners(pos, getCachedState(), getCachedState(), 0);
        }
    }

    private static int getCookTime(World world, PigmentExtractorBlockEntity pigmentExtractorBlockEntity) {
        return (Integer)pigmentExtractorBlockEntity.matchGetter.getFirstMatch(pigmentExtractorBlockEntity, world).map(PigmentExtractorRecipe::getCookTime).orElse(0);
    }

    @Override
    public int size() {
        return this.inventory.size();
    }

    @Override
    public void clear() {
        this.inventory.clear();
    }

    @Override
    public void provideRecipeInputs(RecipeMatcher finder) {
        Iterator<ItemStack> var2 = this.inventory.iterator();

        while(var2.hasNext()) {
            ItemStack itemStack = (ItemStack)var2.next();
            finder.addInput(itemStack);
        }
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return slot != 0 && slot != 1;
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, Direction direction) {
        return this.isValid(slot, stack);
    }

    @Override
    public boolean isValid(int slot, ItemStack stack) {
        if (slot >= 2) {
            return false;
        } else if (slot != 1) {
            return true;
        } else {
            //ItemStack itemStack = (ItemStack)this.inventory.get(1);
            return canUseAsFuel(stack);
        }
    }

    private static boolean canUseAsFuel(ItemStack stack) {
        return stack.isIn(ExspItemTags.PIGMENT_EXTRACTOR_FUEL) || stack.isIn(ExspItemTags.PIGMENT_EXTRACTOR_DOUBLE_FUEL);
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        if (side == Direction.DOWN) {
            return BOTTOM_SLOTS;
        } else {
            return INPUT_SLOTS;
        }
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new PigmentExtractorScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    protected Text getContainerName() {
        return Text.translatable("block.exspectriments.pigment_extractor");
    }

        public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.inventory = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
        Inventories.readNbt(nbt, this.inventory);
        this.growthTime = nbt.getShort("GrowthTime");
        this.growthTimeTotal = nbt.getShort("GrowthTimeTotal");
        this.fuelCooldownTime = nbt.getShort("FuelCooldownTime");

    }

    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("GrowthTime", this.growthTime);
        nbt.putInt("GrowthTimeTotal", this.growthTimeTotal);
        nbt.putInt("FuelCooldownTime", this.fuelCooldownTime);
        Inventories.writeNbt(nbt, this.inventory);
    }

    public static void tick(World world, BlockPos blockPos, BlockState blockState, PigmentExtractorBlockEntity blockEntity) {
        ItemStack fuel = blockEntity.getStack(1);
        if (blockEntity.growthTimeTotal > 0) { // if item is a valid craft
            if (blockEntity.fuelCooldownTime > 0) {
                blockEntity.fuelCooldownTime--;
            }
            if (blockEntity.fuelCooldownTime == 0 && canUseAsFuel(fuel)) {
                if (fuel.isIn(ExspItemTags.PIGMENT_EXTRACTOR_DOUBLE_FUEL)) {
                    blockEntity.craft(world);
                }
                blockEntity.craft(world);
                blockEntity.removeStack(1, 1);
                blockEntity.growthTime = 0;
                blockEntity.fuelCooldownTime = 10;
            } else {
                if (canUseAsFuel(fuel)) {
                    blockEntity.growthTime += Math.max((int)(blockEntity.growthTimeTotal/10),1); // just for the visuals
                } else {
                    blockEntity.growthTime++;
                }
                if (blockEntity.growthTime >= blockEntity.growthTimeTotal) {
                    blockEntity.craft(world);
                    blockEntity.growthTime = 0;
                }
            }
        } else {
            blockEntity.growthTime = 0;
        }
    }

    public void craft(World world) {
        ItemStack recipeInput = this.getStack(0);
        PigmentExtractorRecipe recipe = this.matchGetter.getFirstMatch(this, world).orElse(null);
        if (recipe != null) {
            ItemStack recipeOutput = recipe.getOutput(null);
            InventoryHelper.addToInventory(this, recipeOutput.copy(), 2, 10);
            if (Math.random() < recipe.getReduplicationChance() && Math.signum(recipe.getReduplicationChance()) == 1) {
                InventoryHelper.addToInventory(this, recipeInput.getItem().getDefaultStack(), 2, 10);
            }
        }
    }

    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }
}
