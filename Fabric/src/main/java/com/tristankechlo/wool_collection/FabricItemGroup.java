package com.tristankechlo.wool_collection;

import com.tristankechlo.wool_collection.init.ModBlocks;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public final class FabricItemGroup {

    public static void register() {
        FabricItemGroupBuilder.create(new ResourceLocation(TheWoolCollection.MOD_ID, "general"))
                .icon(() -> new ItemStack(ModBlocks.ALL_ITEMS.get(TheWoolCollection.ICON_LOCATION)))
                .appendItems(stacks -> {
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
                })
                .build();
    }

    private static void addEmptyStacks(List<ItemStack> stacks) {
        stacks.add(ItemStack.EMPTY);
        stacks.add(ItemStack.EMPTY);
    }

}