package com.tristankechlo.wool_collection.jei;

import com.tristankechlo.wool_collection.TheWoolCollection;
import com.tristankechlo.wool_collection.blocks.WeavingStationBlock;
import com.tristankechlo.wool_collection.init.ModRegistry;
import com.tristankechlo.wool_collection.recipe.WeavingStationRecipe;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class WeavingStationRecipeCategory implements IRecipeCategory<WeavingStationRecipe> {

    private static final ResourceLocation LOCATION = ResourceLocation.fromNamespaceAndPath(TheWoolCollection.MOD_ID, "textures/gui/weaving_station.png");
    private final IDrawable background;
    private final IDrawable icon;
    private final Component localizedName;

    public WeavingStationRecipeCategory(IGuiHelper guiHelper) {
        background = guiHelper.createDrawable(LOCATION, 0, 213, 90, 43);
        localizedName = WeavingStationBlock.getContainerName();
        icon = guiHelper.createDrawableItemStack(ModRegistry.WEAVING_STATION_ITEM.get().getDefaultInstance());
    }

    @Override
    public RecipeType<WeavingStationRecipe> getRecipeType() {
        return JustEnoughItemsPlugin.RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return this.localizedName;
    }

    @Override
    public int getWidth() {
        return this.background.getWidth();
    }

    @Override
    public int getHeight() {
        return this.background.getHeight();
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void draw(WeavingStationRecipe recipe, IRecipeSlotsView view, GuiGraphics graphics, double mouseX, double mouseY) {
        this.background.draw(graphics);
        IRecipeCategory.super.draw(recipe, view, graphics, mouseX, mouseY);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, WeavingStationRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 3, 3).addIngredients(recipe.getInputTop());
        builder.addSlot(RecipeIngredientRole.INPUT, 3, 24).addIngredients(recipe.getInputBottom());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 67, 14).addItemStack(getResultItem(recipe));
    }

    @Override
    public boolean isHandled(WeavingStationRecipe recipe) {
        return !recipe.isSpecial();
    }

    private static ItemStack getResultItem(WeavingStationRecipe recipe) {
        Minecraft minecraft = Minecraft.getInstance();
        ClientLevel level = minecraft.level;
        if (level == null) {
            throw new NullPointerException("level must not be null.");
        }
        RegistryAccess registryAccess = level.registryAccess();
        return recipe.getResultItem(registryAccess);
    }

}
