package com.tristankechlo.wool_collection.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.tristankechlo.wool_collection.TheWoolCollection;
import com.tristankechlo.wool_collection.container.WeavingStationContainer;
import com.tristankechlo.wool_collection.recipe.WeavingStationRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;

import java.util.List;

public class WeavingStationScreen extends AbstractContainerScreen<WeavingStationContainer> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(TheWoolCollection.MOD_ID, "textures/gui/weaving_station.png");
    private static final int SCROLLER_WIDTH = 12;
    private static final int SCROLLER_HEIGHT = 15;
    private static final int RECIPES_IMAGE_SIZE_WIDTH = 18;
    private static final int RECIPES_IMAGE_SIZE_HEIGHT = 18;
    private static final int SCROLLER_FULL_HEIGHT = 54;
    private static final int RECIPES_X_START = 40;
    private static final int RECIPES_Y_START = 16;
    private float scrollOffs;
    private boolean scrolling;
    private int startIndex;
    private boolean displayRecipes;

    public WeavingStationScreen(WeavingStationContainer container, Inventory inv, Component title) {
        super(container, inv, title);
        this.imageWidth = 176;
        this.imageHeight = 166;
        this.titleLabelY -= 1;
        this.inventoryLabelY += 1;
        menu.setInventoryChangeListener(this::containerChanged);
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
        super.render(poseStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(poseStack, mouseX, mouseY);
    }

    @Override
    protected void renderBg(PoseStack poseStack, float partialTicks, int mouseX, int mouseY) {
        this.renderBackground(poseStack); // render transparent background
        RenderSystem.setShaderTexture(0, TEXTURE);
        blit(poseStack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
        //render JEI if loaded
        if (TheWoolCollection.JEI_LOADED) {
            blit(poseStack, this.leftPos + this.imageWidth - 3, this.topPos, 176, 0, 18, 22);
        }

        //render scrollbar
        int offset = (int) (39.0F * this.scrollOffs);
        blit(poseStack, this.leftPos + 116, this.topPos + RECIPES_Y_START + offset, 214 + (this.isScrollBarActive() ? 0 : SCROLLER_WIDTH), 0, SCROLLER_WIDTH, SCROLLER_HEIGHT);

        //render recipes and buttons
        int startX = this.leftPos + RECIPES_X_START;
        int startY = this.topPos + RECIPES_Y_START;
        int $$9 = this.startIndex + SCROLLER_WIDTH;
        this.renderButtons(poseStack, mouseX, mouseY, startX, startY, $$9);
        this.renderRecipes(poseStack, startX, startY, $$9);
    }

    @Override
    protected void renderTooltip(PoseStack poseStack, int x, int y) {
        super.renderTooltip(poseStack, x, y);
        if (this.displayRecipes) {
            int $$3 = this.leftPos + RECIPES_X_START;
            int $$4 = this.topPos + RECIPES_Y_START;
            int $$5 = this.startIndex + SCROLLER_WIDTH;
            List<WeavingStationRecipe> recipes = this.menu.getRecipes();

            for (int $$7 = this.startIndex; $$7 < $$5 && $$7 < (this.menu).getNumRecipes(); ++$$7) {
                int $$8 = $$7 - this.startIndex;
                int $$9 = $$3 + $$8 % 4 * RECIPES_IMAGE_SIZE_WIDTH;
                int $$10 = $$4 + $$8 / 4 * RECIPES_IMAGE_SIZE_HEIGHT + 2;
                if (x >= $$9 && x < $$9 + RECIPES_IMAGE_SIZE_WIDTH && y >= $$10 && y < $$10 + RECIPES_IMAGE_SIZE_HEIGHT) {
                    renderTooltip(poseStack, recipes.get($$7).getResultItem(), x, y);
                }
            }
        }
    }

    private void renderButtons(PoseStack poseStack, int mouseX, int mouseY, int startX, int startY, int $$5) {
        for (int index = this.startIndex; index < $$5 && index < (this.menu).getNumRecipes(); ++index) {
            int $$7 = index - this.startIndex;
            int $$8 = startX + $$7 % 4 * RECIPES_IMAGE_SIZE_WIDTH;
            int $$9 = $$7 / 4;
            int $$10 = startY + $$9 * RECIPES_IMAGE_SIZE_HEIGHT + 1;
            int yPosButtonTexture = 0;
            if (index == (this.menu).getSelectedRecipe()) {
                yPosButtonTexture += 18;
            } else if (mouseX >= $$8 && mouseY >= $$10 && mouseX < $$8 + RECIPES_IMAGE_SIZE_WIDTH && mouseY < $$10 + RECIPES_IMAGE_SIZE_HEIGHT) {
                yPosButtonTexture += 36;
            }

            blit(poseStack, $$8, $$10 - 1, 238, yPosButtonTexture, RECIPES_IMAGE_SIZE_WIDTH, RECIPES_IMAGE_SIZE_HEIGHT);
        }
    }

    private void renderRecipes(PoseStack poseStack, int startX, int startY, int $$3) {
        List<WeavingStationRecipe> recipes = (this.menu).getRecipes();
        for (int index = this.startIndex; index < $$3 && index < (this.menu).getNumRecipes(); ++index) {
            int $$6 = index - this.startIndex;
            int $$7 = startX + $$6 % 4 * RECIPES_IMAGE_SIZE_WIDTH + 1;
            int $$8 = $$6 / 4;
            int $$9 = startY + $$8 * RECIPES_IMAGE_SIZE_HEIGHT + 1;
            this.minecraft.getItemRenderer().renderAndDecorateItem(recipes.get(index).getResultItem(), $$7, $$9);
        }

    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int key) {
        this.scrolling = false;
        if (this.displayRecipes) {
            int startX = this.leftPos + RECIPES_X_START;
            int startY = this.topPos + RECIPES_Y_START;
            int $$5 = this.startIndex + SCROLLER_WIDTH;

            for (int $$6 = this.startIndex; $$6 < $$5; ++$$6) {
                int $$7 = $$6 - this.startIndex;
                double $$8 = mouseX - (double) (startX + $$7 % 4 * RECIPES_IMAGE_SIZE_WIDTH);
                double $$9 = mouseY - (double) (startY + $$7 / 4 * RECIPES_IMAGE_SIZE_HEIGHT);
                if ($$8 >= 0.0 && $$9 >= 0.0 && $$8 < RECIPES_IMAGE_SIZE_WIDTH && $$9 < RECIPES_IMAGE_SIZE_HEIGHT && (this.menu).clickMenuButton(this.minecraft.player, $$6)) {
                    Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_STONECUTTER_SELECT_RECIPE, 1.0F));
                    this.minecraft.gameMode.handleInventoryButtonClick((this.menu).containerId, $$6);
                    return true;
                }
            }

            startX = this.leftPos + 116;
            startY = this.topPos + 16;
            if (mouseX >= (double) startX && mouseX < (double) (startX + SCROLLER_WIDTH) && mouseY >= (double) startY && mouseY < (double) (startY + SCROLLER_FULL_HEIGHT)) {
                this.scrolling = true;
            }
        }

        return super.mouseClicked(mouseX, mouseY, key);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int key, double dragX, double dragY) {
        if (this.scrolling && this.isScrollBarActive()) {
            int startScrollBarY = this.topPos + RECIPES_Y_START;
            int endScrollBarY = startScrollBarY + SCROLLER_FULL_HEIGHT;
            this.scrollOffs = ((float) mouseY - (float) startScrollBarY - 7.5F) / ((float) (endScrollBarY - startScrollBarY) - 15.0F);
            this.scrollOffs = Mth.clamp(this.scrollOffs, 0.0F, 1.0F);
            this.startIndex = (int) ((double) (this.scrollOffs * (float) this.getOffscreenRows()) + 0.5) * 4;
            return true;
        } else {
            return super.mouseDragged(mouseX, mouseY, key, dragX, dragY);
        }
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        if (this.isScrollBarActive()) {
            int offScreenRows = this.getOffscreenRows();
            float $$4 = (float) delta / (float) offScreenRows;
            this.scrollOffs = Mth.clamp(this.scrollOffs - $$4, 0.0F, 1.0F);
            this.startIndex = (int) ((double) (this.scrollOffs * (float) offScreenRows) + 0.5) * 4;
        }
        return true;
    }

    private boolean isScrollBarActive() {
        return this.displayRecipes && (this.menu).getNumRecipes() > SCROLLER_WIDTH;
    }

    protected int getOffscreenRows() {
        return ((this.menu).getNumRecipes() + 4 - 1) / 4 - 3;
    }

    private void containerChanged() {
        this.displayRecipes = (this.menu).hasInputItem();
        if (!this.displayRecipes) {
            this.scrollOffs = 0.0F;
            this.startIndex = 0;
        }
    }

    public int getStartX() {
        return this.leftPos + this.imageWidth - 3;
    }

    public int getStartY() {
        return this.topPos;
    }

}