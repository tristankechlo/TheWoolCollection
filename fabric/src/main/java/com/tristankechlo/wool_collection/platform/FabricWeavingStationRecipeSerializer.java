package com.tristankechlo.wool_collection.platform;

import com.google.gson.JsonObject;
import com.tristankechlo.wool_collection.recipe.WeavingStationRecipe;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class FabricWeavingStationRecipeSerializer implements RecipeSerializer<WeavingStationRecipe> {

    @Override
    public WeavingStationRecipe fromJson(ResourceLocation resourceLocation, JsonObject jsonObject) {
        return WeavingStationRecipe.Serializer.fromJson(resourceLocation, jsonObject);
    }

    @Override
    public WeavingStationRecipe fromNetwork(ResourceLocation resourceLocation, FriendlyByteBuf friendlyByteBuf) {
        return WeavingStationRecipe.Serializer.fromNetwork(resourceLocation, friendlyByteBuf);
    }

    @Override
    public void toNetwork(FriendlyByteBuf friendlyByteBuf, WeavingStationRecipe recipe) {
        WeavingStationRecipe.Serializer.toNetwork(friendlyByteBuf, recipe);
    }

}
