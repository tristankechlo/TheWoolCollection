package com.tristankechlo.wool_collection;

import com.tristankechlo.wool_collection.init.ModBlocks;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public final class ForgeItemGroup extends CreativeModeTab {

    public ForgeItemGroup() {
        super(TheWoolCollection.MOD_ID + ".general");
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(ModBlocks.ALL_ITEMS.get(TheWoolCollection.ICON_LOCATION));
    }

    @Override
    public void fillItemList(NonNullList<ItemStack> stacks) {
        TheWoolCollection.sortedListByColor(ModBlocks.FENCES).forEach(stack -> stacks.add(new ItemStack(stack)));
        addEmptyStacks(stacks);
        TheWoolCollection.sortedListByColor(ModBlocks.FENCE_GATES).forEach(stack -> stacks.add(new ItemStack(stack)));
        addEmptyStacks(stacks);
        TheWoolCollection.sortedListByColor(ModBlocks.STAIRS).forEach(stack -> stacks.add(new ItemStack(stack)));
        addEmptyStacks(stacks);
        TheWoolCollection.sortedListByColor(ModBlocks.SLABS).forEach(stack -> stacks.add(new ItemStack(stack)));
        addEmptyStacks(stacks);
        TheWoolCollection.sortedListByColor(ModBlocks.WALLS).forEach(stack -> stacks.add(new ItemStack(stack)));
        addEmptyStacks(stacks);
        TheWoolCollection.sortedListByColor(ModBlocks.PRESSURE_PLATES).forEach(stack -> stacks.add(new ItemStack(stack)));
        addEmptyStacks(stacks);
        TheWoolCollection.sortedListByColor(ModBlocks.BUTTONS).forEach(stack -> stacks.add(new ItemStack(stack)));
    }

    private static void addEmptyStacks(List<ItemStack> stacks) {
        stacks.add(ItemStack.EMPTY);
        stacks.add(ItemStack.EMPTY);
    }

}