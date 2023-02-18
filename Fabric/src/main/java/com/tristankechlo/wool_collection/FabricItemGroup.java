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
                })
                .build();
    }

    private static void addEmptyStacks(List<ItemStack> stacks) {
        stacks.add(ItemStack.EMPTY);
        stacks.add(ItemStack.EMPTY);
    }

}
