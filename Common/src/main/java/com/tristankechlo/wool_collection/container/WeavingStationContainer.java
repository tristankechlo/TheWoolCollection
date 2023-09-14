package com.tristankechlo.wool_collection.container;

import com.google.common.collect.Lists;
import com.tristankechlo.wool_collection.init.ModRegistry;
import com.tristankechlo.wool_collection.recipe.WeavingStationRecipe;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class WeavingStationContainer extends AbstractContainerMenu {

    private final ContainerLevelAccess worldPos;
    private final DataSlot selectedRecipe = DataSlot.standalone();
    private final Level level;
    private Runnable changeListener = () -> {};
    private List<WeavingStationRecipe> recipes;
    private final Slot inputSlotTop;
    private final Slot inputSlotBottom;
    private final Slot resultSlot;
    public final Container container;
    private final ResultContainer resultContainer = new ResultContainer();
    private NonNullList<ItemStack> inputs = NonNullList.withSize(2, ItemStack.EMPTY);

    public WeavingStationContainer(int id, Inventory playerInventory, FriendlyByteBuf extraData) {
        this(id, playerInventory, ContainerLevelAccess.NULL);
    }

    public WeavingStationContainer(int id, Inventory playerInventory) {
        this(id, playerInventory, ContainerLevelAccess.NULL);
    }

    public WeavingStationContainer(int id, Inventory playerInventory, final ContainerLevelAccess worldCallable) {
        super(ModRegistry.WEAVING_STATION_CONTAINER.get(), id);
        this.worldPos = worldCallable;
        this.level = playerInventory.player.level;
        this.recipes = Lists.newArrayList();

        this.container = new SimpleContainer(2) {
            @Override
            public void setChanged() {
                super.setChanged();
                WeavingStationContainer.this.slotsChanged(this);
                WeavingStationContainer.this.changeListener.run();
            }
        };

        this.inputSlotTop = this.addSlot(new Slot(this.container, 0, 13, 24));
        this.inputSlotBottom = this.addSlot(new Slot(this.container, 1, 13, 46));
        this.resultSlot = this.addSlot(new Slot(this.resultContainer, 0, 143, 35) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }

            @Override
            public void onTake(Player player, ItemStack stack) {
                stack.onCraftedBy(player.level, player, stack.getCount());
                WeavingStationContainer.this.resultContainer.awardUsedRecipes(player);
                ItemStack $$$1 = WeavingStationContainer.this.inputSlotTop.remove(1);
                ItemStack $$$2 = WeavingStationContainer.this.inputSlotBottom.remove(1);
                if (!$$$1.isEmpty() && !$$$2.isEmpty()) {
                    WeavingStationContainer.this.setupResultSlot();
                }
                //TODO play sound
                super.onTake(player, stack);
            }
        });

        // player inv
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        // player hotbar
        for (int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
        }

        this.addDataSlot(this.selectedRecipe);
    }

    public List<WeavingStationRecipe> getRecipes() {
        return recipes;
    }

    public int getNumRecipes() {
        return recipes.size();
    }

    public boolean hasInputItem() {
        return this.inputSlotTop.hasItem() /*&& this.inputSlotBottom.hasItem()*/ && !this.recipes.isEmpty();
    }

    public int getSelectedRecipe() {
        return this.selectedRecipe.get();
    }

    public void setInventoryChangeListener(Runnable run) {
        this.changeListener = run;
    }

    @Override
    public boolean clickMenuButton(Player player, int index) {
        if (this.isValidRecipeIndex(index)) {
            this.selectedRecipe.set(index);
            this.setupResultSlot();
        }
        return true;
    }

    private boolean isValidRecipeIndex(int index) {
        return index >= 0 && index < this.recipes.size();
    }

    @Override
    public void slotsChanged(Container container) {
        ItemStack $$1 = this.inputSlotTop.getItem();
        ItemStack $$2 = this.inputSlotBottom.getItem();
        if (!$$1.is(this.inputs.get(0).getItem()) || !$$2.is(this.inputs.get(1).getItem())) {
            this.inputs.set(0, $$1.copy());
            this.inputs.set(1, $$2.copy());
            this.setupRecipeList(container, $$1, $$2);
        }
    }

    private void setupRecipeList(Container container, ItemStack stack1, ItemStack stack2) {
        this.recipes.clear();
        this.selectedRecipe.set(-1);
        this.resultSlot.set(ItemStack.EMPTY);
        if (!stack1.isEmpty() /*&& !stack2.isEmpty()*/) {
            this.recipes = this.level.getRecipeManager().getRecipesFor(ModRegistry.WEAVING_STATION_RECIPE_TYPE.get(), container, this.level);
        }
    }

    private void setupResultSlot() {
        if (!this.recipes.isEmpty() && this.isValidRecipeIndex(this.selectedRecipe.get())) {
            WeavingStationRecipe recipe = this.recipes.get(this.selectedRecipe.get());
            ItemStack $$1 = recipe.assemble(this.container);
            this.resultContainer.setRecipeUsed(recipe);
            this.resultSlot.set($$1);
        } else {
            this.resultSlot.set(ItemStack.EMPTY);
        }
        this.broadcastChanges();
    }

    @Override
    public boolean canTakeItemForPickAll(ItemStack stack, Slot slot) {
        return slot.container != this.resultContainer && super.canTakeItemForPickAll(stack, slot);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            Item item = itemstack1.getItem();
            itemstack = itemstack1.copy();
            if (index == 0 || index == 1) {
                if (!this.moveItemStackTo(itemstack1, 3, 39, false)) { // move from inputs to player inv
                    return ItemStack.EMPTY;
                }
            } else if (index == 2) {
                item.onCraftedBy(itemstack1, player.level, player);
                if (!this.moveItemStackTo(itemstack1, 3, 39, true)) { // move from result to player inv
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(itemstack1, itemstack);
            } else if (this.hasRecipeTop(itemstack1)) {
                if (!this.moveItemStackTo(itemstack1, 0, 1, false)) { // move from player inv to top input slot
                    return ItemStack.EMPTY;
                }
            } else if (this.hasRecipeBottom(itemstack1)) {
                if (!this.moveItemStackTo(itemstack1, 1, 2, false)) { // move from player inv to bottom input slot
                    return ItemStack.EMPTY;
                }
            } else if (index >= 3 && index < 30) {
                if (!this.moveItemStackTo(itemstack1, 30, 39, false)) { // move from player inv to hotbar
                    return ItemStack.EMPTY;
                }
            } else if (index >= 30 && index < 39 && !this.moveItemStackTo(itemstack1, 3, 30, false)) { // move from hotbar to player inv
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            }

            slot.setChanged();
            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack1);
            this.broadcastChanges();
        }

        return itemstack;
    }

    @Override
    public boolean stillValid(Player var1) {
        return stillValid(worldPos, var1, ModRegistry.WEAVING_STATION_BLOCK.get());
    }

    private boolean hasRecipeTop(ItemStack stack) {
        return this.level.getRecipeManager().getAllRecipesFor(ModRegistry.WEAVING_STATION_RECIPE_TYPE.get()).stream().anyMatch((recipe) -> recipe.getInputTop().test(stack));
    }

    private boolean hasRecipeBottom(ItemStack stack) {
        return this.level.getRecipeManager().getAllRecipesFor(ModRegistry.WEAVING_STATION_RECIPE_TYPE.get()).stream().anyMatch((recipe) -> recipe.getInputBottom().test(stack));
    }

    public void removed(Player player) {
        super.removed(player);
        this.resultContainer.removeItemNoUpdate(1);
        this.worldPos.execute((level, pos) -> {
            this.clearContainer(player, this.container);
        });
    }

}