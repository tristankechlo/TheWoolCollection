package com.tristankechlo.wool_collection.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.tristankechlo.wool_collection.init.ModRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class WeavingStationRecipe implements Recipe<Container> {

    private final Ingredient input_top;
    private final Optional<Ingredient> input_bottom;
    private final ItemStack result;

    public WeavingStationRecipe(Ingredient input_top, Optional<Ingredient> input_bottom, ItemStack result) {
        this.input_top = input_top;
        this.input_bottom = input_bottom;
        this.result = result;
    }

    @Override
    public boolean matches(Container container, Level level) {
        if (container.getContainerSize() < 2) {
            return false;
        }
        if (this.getInputBottom().isEmpty() && container.getItem(1).isEmpty()) {
            return this.input_top.test(container.getItem(0));
        }
        return this.input_top.test(container.getItem(0)) && this.getInputBottom().test(container.getItem(1));
    }

    @Override
    public ItemStack assemble(Container container, HolderLookup.Provider provider) {
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
                        Ingredient.CODEC_NONEMPTY.fieldOf("input_top").forGetter(recipe -> recipe.input_top),
                        Ingredient.CODEC.optionalFieldOf("input_bottom").forGetter(recipe -> recipe.input_bottom),
                        ItemStack.CODEC.fieldOf("result").forGetter(recipe -> recipe.result)
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
