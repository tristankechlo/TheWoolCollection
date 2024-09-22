package com.tristankechlo.wool_collection.platform;

import com.tristankechlo.wool_collection.TheWoolCollection;
import com.tristankechlo.wool_collection.init.ModBlocks;
import com.tristankechlo.wool_collection.init.ModRegistry;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public final class ForgeItemGroup extends CreativeModeTab {

    public ForgeItemGroup() {
        super(TheWoolCollection.MOD_ID + ".general");
    }

    @Override
    public ItemStack makeIcon() {
        return ModRegistry.WEAVING_STATION_ITEM.get().getDefaultInstance();
    }

    @Override
    public void fillItemList(NonNullList<ItemStack> stacks) {
        stacks.add(ModRegistry.WEAVING_STATION_ITEM.get().getDefaultInstance());
        TheWoolCollection.sortedListByColor(ModBlocks.FENCES).forEach(stack -> stacks.add(new ItemStack(stack)));
        TheWoolCollection.sortedListByColor(ModBlocks.FENCE_GATES).forEach(stack -> stacks.add(new ItemStack(stack)));
        TheWoolCollection.sortedListByColor(ModBlocks.STAIRS).forEach(stack -> stacks.add(new ItemStack(stack)));
        TheWoolCollection.sortedListByColor(ModBlocks.SLABS).forEach(stack -> stacks.add(new ItemStack(stack)));
        TheWoolCollection.sortedListByColor(ModBlocks.WALLS).forEach(stack -> stacks.add(new ItemStack(stack)));
        TheWoolCollection.sortedListByColor(ModBlocks.PRESSURE_PLATES).forEach(stack -> stacks.add(new ItemStack(stack)));
        TheWoolCollection.sortedListByColor(ModBlocks.BUTTONS).forEach(stack -> stacks.add(new ItemStack(stack)));
    }

}
