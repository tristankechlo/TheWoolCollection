package com.tristankechlo.wool_collection.recipe;

import com.google.gson.JsonObject;
import com.tristankechlo.wool_collection.init.ModRegistry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public class WoolProcessorRecipe implements Recipe<Container> {

    private final ResourceLocation id;
    private final Ingredient input_top;
    private final Ingredient input_bottom;
    private final ItemStack result;

    public WoolProcessorRecipe(ResourceLocation id, Ingredient input_top, Ingredient input_bottom, ItemStack result) {
        this.id = id;
        this.input_top = input_top;
        this.input_bottom = input_bottom;
        this.result = result;
    }

    @Override
    public boolean matches(Container container, Level level) {
        if (container.getContainerSize() < 2) {
            return false;
        }
        return this.input_top.test(container.getItem(0)) && this.input_bottom.test(container.getItem(1));
    }

    @Override
    public ItemStack assemble(Container container, RegistryAccess access) {
        return this.result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int i, int j) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess access) {
        return this.result;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRegistry.WOOL_PROCESSOR_RECIPE_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRegistry.WOOL_PROCESSOR_RECIPE_TYPE.get();
    }

    public Ingredient getInputTop() {
        return input_top;
    }

    public Ingredient getInputBottom() {
        return input_bottom;
    }

    public static class Serializer implements RecipeSerializer<WoolProcessorRecipe> {

        @Override
        public WoolProcessorRecipe fromJson(ResourceLocation id, JsonObject json) {
            Ingredient input_top = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "input_top"), false);
            Ingredient input_bottom = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "input_bottom"), false);
            ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
            return new WoolProcessorRecipe(id, input_top, input_bottom, result);
        }

        @Override
        public WoolProcessorRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
            Ingredient input_top = Ingredient.fromNetwork(buffer);
            Ingredient input_bottom = Ingredient.fromNetwork(buffer);
            ItemStack result = buffer.readItem();
            return new WoolProcessorRecipe(id, input_top, input_bottom, result);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, WoolProcessorRecipe recipe) {
            recipe.input_top.toNetwork(buffer);
            recipe.input_bottom.toNetwork(buffer);
            buffer.writeItem(recipe.result);
        }

    }

}
