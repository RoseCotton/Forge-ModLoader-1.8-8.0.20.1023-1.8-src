package com.rosecotton.shelvesmod;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * User: brandon3055
 * Date: 06/01/2015
 *
 * GuiInventoryShelf is a simple gui that does nothing but draw a background image and a line of text on the screen
 * everything else is handled by the vanilla container code
 */
@SideOnly(Side.CLIENT)
public class GuiInventoryShelf extends GuiContainer {

	// This is the resource location for the background image for the GUI
	private static final ResourceLocation texture = new ResourceLocation("shelvesmod", "textures/gui/shelf_inventory_bg.png");
	private ShelfTitleEntity shelfTitleEntity;

	public GuiInventoryShelf(InventoryPlayer invPlayer, ShelfTitleEntity tile) {
		super(new ContainerShelf(invPlayer, tile));
		shelfTitleEntity = tile;
		// Set the width and height of the gui.  Should match the size of the texture!
		xSize = 176;
		ySize = 133;
	}

	// draw the background for the GUI - rendered first
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int x, int y) {
		// Bind the image texture of our custom container
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		// Draw the image
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}

	// draw the foreground for the GUI - rendered after the slots, but before the dragged items and tooltips
	// renders relative to the top left corner of the background
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		final int LABEL_XPOS = 5;
		final int LABEL_YPOS = 5;
		fontRendererObj.drawString(shelfTitleEntity.getDisplayName().getUnformattedText(), LABEL_XPOS, LABEL_YPOS, Color.darkGray.getRGB());
	}
}
