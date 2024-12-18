package com.tristankechlo.wool_collection.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public record WeavingStationRecipeInput(ItemStack input_top, ItemStack input_bottom) implements RecipeInput {

    @Override
    public ItemStack getItem(int i) {
        if (i == 0) {
            return this.input_top();
        } else if (i == 1) {
            return this.input_bottom();
        }
        throw new IllegalArgumentException("Recipe does not contain slot " + i);
    }

    @Override
    public int size() {
        return 2;
    }

    @Override
    public boolean isEmpty() {
        return this.input_top().isEmpty() && this.input_bottom().isEmpty();
    }

}
