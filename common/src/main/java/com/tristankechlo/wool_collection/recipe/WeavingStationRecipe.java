package com.tristankechlo.wool_collection.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.tristankechlo.wool_collection.init.ModRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import java.util.Optional;

public record WeavingStationRecipe(Ingredient input_top, Optional<Ingredient> input_bottom, ItemStack result)
        implements Recipe<WeavingStationRecipeInput> {

    @Override
    public boolean matches(WeavingStationRecipeInput input, Level level) {
        if (input.size() < 2) {
            return false;
        }
        if (this.getInputBottom().isEmpty() && input.getItem(1).isEmpty()) {
            return this.input_top.test(input.getItem(0));
        }
        return this.input_top.test(input.getItem(0)) && this.getInputBottom().test(input.getItem(1));
    }

    @Override
    public ItemStack assemble(WeavingStationRecipeInput input, HolderLookup.Provider provider) {
        return this.result.copy();
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return this.result;
    }

    @Override
    public boolean canCraftInDimensions(int i, int j) {
        return true;
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
        return input_bottom.orElse(Ingredient.EMPTY);
    }

    public static class Serializer implements RecipeSerializer<WeavingStationRecipe> {

        public static final MapCodec<WeavingStationRecipe> CODEC = RecordCodecBuilder.mapCodec(
                builder -> builder.group(
                        Ingredient.CODEC_NONEMPTY.fieldOf("input_top").forGetter(WeavingStationRecipe::input_top),
                        Ingredient.CODEC.optionalFieldOf("input_bottom").forGetter(WeavingStationRecipe::input_bottom),
                        ItemStack.CODEC.fieldOf("result").forGetter(WeavingStationRecipe::result)
                ).apply(builder, WeavingStationRecipe::new)
        );
        public static final StreamCodec<RegistryFriendlyByteBuf, WeavingStationRecipe> STREAM_CODEC = StreamCodec.of(Serializer::toNetwork, Serializer::fromNetwork);

        @Override
        public MapCodec<WeavingStationRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, WeavingStationRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        public static WeavingStationRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            Ingredient input_top = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);

            Ingredient input_bottom = Ingredient.EMPTY;
            boolean hasBottom = buffer.readBoolean();
            if (hasBottom) {
                input_bottom = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
            }
            ItemStack result = ItemStack.STREAM_CODEC.decode(buffer);
            return new WeavingStationRecipe(input_top, Optional.of(input_bottom), result);
        }

        public static void toNetwork(RegistryFriendlyByteBuf buffer, WeavingStationRecipe recipe) {
            Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.input_top);
            if (recipe.getInputBottom().isEmpty()) {
                buffer.writeBoolean(false);
            } else {
                buffer.writeBoolean(true);
                Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.getInputBottom());
            }
            ItemStack.STREAM_CODEC.encode(buffer, recipe.result);
        }

    }

}
