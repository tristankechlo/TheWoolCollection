package com.tristankechlo.wool_collection.platform;

import com.google.gson.JsonObject;
import com.tristankechlo.wool_collection.recipe.WeavingStationRecipe;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.ForgeRegistryEntry;
import org.jetbrains.annotations.Nullable;

public class ForgeWeavingStationRecipeSerializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<WeavingStationRecipe> {

    @Override
    public WeavingStationRecipe fromJson(ResourceLocation id, JsonObject json) {
        return WeavingStationRecipe.Serializer.fromJson(id, json);
    }

    @Nullable
    @Override
    public WeavingStationRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
        return WeavingStationRecipe.Serializer.fromNetwork(id, buffer);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buffer, WeavingStationRecipe recipe) {
        WeavingStationRecipe.Serializer.toNetwork(buffer, recipe);
    }

}
