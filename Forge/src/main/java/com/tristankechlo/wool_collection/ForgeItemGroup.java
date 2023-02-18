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
        ModBlocks.FENCES.values().forEach(stack -> stacks.add(new ItemStack(stack)));
        addEmptyStacks(stacks);
        ModBlocks.FENCE_GATES.values().forEach(stack -> stacks.add(new ItemStack(stack)));
        addEmptyStacks(stacks);
        ModBlocks.STAIRS.values().forEach(stack -> stacks.add(new ItemStack(stack)));
        addEmptyStacks(stacks);
        ModBlocks.SLABS.values().forEach(stack -> stacks.add(new ItemStack(stack)));
        addEmptyStacks(stacks);
        ModBlocks.WALLS.values().forEach(stack -> stacks.add(new ItemStack(stack)));
        addEmptyStacks(stacks);
        ModBlocks.BUTTONS.values().forEach(stack -> stacks.add(new ItemStack(stack)));
        addEmptyStacks(stacks);
        ModBlocks.PRESSURE_PLATES.values().forEach(stack -> stacks.add(new ItemStack(stack)));
    }

    private static void addEmptyStacks(List<ItemStack> stacks) {
        stacks.add(ItemStack.EMPTY);
        stacks.add(ItemStack.EMPTY);
    }

}
