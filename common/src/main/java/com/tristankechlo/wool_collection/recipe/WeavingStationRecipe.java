package com.tristankechlo.wool_collection.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.tristankechlo.wool_collection.init.ModRegistry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public class WeavingStationRecipe implements Recipe<Container> {

    private final ResourceLocation id;
    private final Ingredient input_top;
    private final Ingredient input_bottom;
    private final ItemStack result;

    public WeavingStationRecipe(ResourceLocation id, Ingredient input_top, Ingredient input_bottom, ItemStack result) {
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
        if (this.input_bottom.isEmpty() && container.getItem(1).isEmpty()) {
            return this.input_top.test(container.getItem(0));
        }
        return this.input_top.test(container.getItem(0)) && this.input_bottom.test(container.getItem(1));
    }

    @Override
    public ItemStack assemble(Container container) {
        return this.result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int i, int j) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return this.result;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRegistry.WEAVING_STATION_RECIPE_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRegistry.WEAVING_STATION_RECIPE_TYPE.get();
    }

    public Ingredient getInputTop() {
        return input_top;
    }

    public Ingredient getInputBottom() {
        return input_bottom;
    }

    public static class Serializer {

        public static WeavingStationRecipe fromJson(ResourceLocation id, JsonObject json) {
            Ingredient input_top = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "input_top"));
            ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));

            Ingredient input_bottom = Ingredient.EMPTY;
            JsonElement jsonElement = json.get("input_bottom");
            if (jsonElement != null && !jsonElement.isJsonNull()) {
                input_bottom = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "input_bottom"));
            }

            return new WeavingStationRecipe(id, input_top, input_bottom, result);
        }

        public static WeavingStationRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
            Ingredient input_top = Ingredient.fromNetwork(buffer);

            Ingredient input_bottom = Ingredient.EMPTY;
            boolean hasBottom = buffer.readBoolean();
            if (hasBottom) {
                input_bottom = Ingredient.fromNetwork(buffer);
            }
            ItemStack result = buffer.readItem();
            return new WeavingStationRecipe(id, input_top, input_bottom, result);
        }

        public static void toNetwork(FriendlyByteBuf buffer, WeavingStationRecipe recipe) {
            recipe.input_top.toNetwork(buffer);
            if (recipe.input_bottom.isEmpty()) {
                buffer.writeBoolean(false);
            } else {
                buffer.writeBoolean(true);
                recipe.input_bottom.toNetwork(buffer);
            }
            buffer.writeItem(recipe.result);
        }

    }

}