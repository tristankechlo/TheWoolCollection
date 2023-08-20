package com.tristankechlo.wool_collection.jei;


import com.tristankechlo.wool_collection.TheWoolCollection;
import com.tristankechlo.wool_collection.client.WoolProcessorScreen;
import com.tristankechlo.wool_collection.container.WoolProcessorContainer;
import com.tristankechlo.wool_collection.init.ModRegistry;
import com.tristankechlo.wool_collection.recipe.WoolProcessorRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.registration.*;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;
import java.util.Objects;

@JeiPlugin
public class JustEnoughItemsPlugin implements IModPlugin {

    private static final ResourceLocation UID = new ResourceLocation(TheWoolCollection.MOD_ID, "jei_plugin");
    public static final RecipeType<WoolProcessorRecipe> RECIPE_TYPE = RecipeType.create(TheWoolCollection.MOD_ID, "wool_processor", WoolProcessorRecipe.class);
    private IRecipeCategory<WoolProcessorRecipe> recipeCategory;

    @Override
    public ResourceLocation getPluginUid() {
        return UID;
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
        TheWoolCollection.LOGGER.info("JEI is available");
        TheWoolCollection.JEI_LOADED = true;
    }

    @Override
    public void onRuntimeUnavailable() {
        TheWoolCollection.LOGGER.info("JEI is no longer available");
        TheWoolCollection.JEI_LOADED = false;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        //custom recipe category for the wool_processor
        IGuiHelper guiHelper = registration.getJeiHelpers().getGuiHelper();
        registration.addRecipeCategories(recipeCategory = new WoolProcessorRecipeCategory(guiHelper));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        //register recipes for the wool_processor category
        Objects.requireNonNull(recipeCategory, "woolProcessorCategory");
        List<WoolProcessorRecipe> recipes = getRecipes();
        registration.addRecipes(RECIPE_TYPE, recipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        //while in wool_processor GUI, add clickable area to open the JEI GUI for the custom category
        registration.addRecipeClickArea(WoolProcessorScreen.class, 173, 0, 18, 22, RECIPE_TYPE);
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        //move ingredients from the inventory into crafting GUIs
        registration.addRecipeTransferHandler(WoolProcessorContainer.class, ModRegistry.WOOL_PROCESSOR_CONTAINER.get(), RECIPE_TYPE, 0, 2, 3, 36);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        //how to craft the thing where the recipe is for (e.g. the wool_processor)
        registration.addRecipeCatalyst(new ItemStack(ModRegistry.WOOL_PROCESSOR_BLOCK.get()), RECIPE_TYPE);
    }

    public static List<WoolProcessorRecipe> getRecipes() {
        Minecraft minecraft = Minecraft.getInstance();
        Objects.requireNonNull(minecraft, "minecraft");
        ClientLevel world = minecraft.level;
        Objects.requireNonNull(world, "minecraft world");
        RecipeManager recipeManager = world.getRecipeManager();
        return recipeManager.getAllRecipesFor(ModRegistry.WOOL_PROCESSOR_RECIPE_TYPE.get());
    }

}
