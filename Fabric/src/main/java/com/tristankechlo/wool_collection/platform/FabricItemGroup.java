package com.tristankechlo.wool_collection.platform;

import com.tristankechlo.wool_collection.TheWoolCollection;
import com.tristankechlo.wool_collection.init.ModBlocks;
import com.tristankechlo.wool_collection.init.ModRegistry;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public final class FabricItemGroup {

    public static void register() {
        FabricItemGroupBuilder.create(new ResourceLocation(TheWoolCollection.MOD_ID, "general"))
                .icon(() -> ModRegistry.WEAVING_STATION_ITEM.get().getDefaultInstance())
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
                    addEmptyStacks(stacks);
                    stacks.add(ModRegistry.WEAVING_STATION_ITEM.get().getDefaultInstance());
                })
                .build();
    }

    private static void addEmptyStacks(List<ItemStack> stacks) {
        stacks.add(ItemStack.EMPTY);
        stacks.add(ItemStack.EMPTY);
    }

}
