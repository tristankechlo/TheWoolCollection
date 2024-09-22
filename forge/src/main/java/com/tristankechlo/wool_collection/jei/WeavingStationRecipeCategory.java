package com.tristankechlo.wool_collection.jei;

import com.tristankechlo.wool_collection.TheWoolCollection;
import com.tristankechlo.wool_collection.blocks.WeavingStationBlock;
import com.tristankechlo.wool_collection.init.ModRegistry;
import com.tristankechlo.wool_collection.recipe.WeavingStationRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.Arrays;

public class WeavingStationRecipeCategory implements IRecipeCategory<WeavingStationRecipe> {

    public static final ResourceLocation UID = new ResourceLocation(TheWoolCollection.MOD_ID, "weaving_station");
    private static final ResourceLocation LOCATION = new ResourceLocation(TheWoolCollection.MOD_ID, "textures/gui/weaving_station.png");
    private final IDrawable background;
    private final IDrawable icon;
    private final Component localizedName;

    public WeavingStationRecipeCategory(IGuiHelper guiHelper) {
        background = guiHelper.createDrawable(LOCATION, 0, 213, 90, 43);
        localizedName = WeavingStationBlock.getContainerName();
        icon = guiHelper.createDrawableIngredient(ModRegistry.WEAVING_STATION_ITEM.get().getDefaultInstance());
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends WeavingStationRecipe> getRecipeClass() {
        return WeavingStationRecipe.class;
    }

    @Override
    public Component getTitle() {
        return this.localizedName;
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setIngredients(WeavingStationRecipe recipe, IIngredients ingredients) {
        ingredients.setInputIngredients(Arrays.asList(recipe.getInputTop(), recipe.getInputBottom()));
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
    }

    @Override
    public void setRecipe(IRecipeLayout builder, WeavingStationRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = builder.getItemStacks();
        guiItemStacks.init(0, true, 2, 2);
        guiItemStacks.init(1, true, 2, 23);
        guiItemStacks.init(2, false, 66, 13);
        guiItemStacks.set(ingredients);
    }

    @Override
    public boolean isHandled(WeavingStationRecipe recipe) {
        return !recipe.isSpecial();
    }

}