package com.lambdanum.mcdisc.item;

import com.lambdanum.mcdisc.McDiscMod;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class PortableJukeboxGuiContainer extends GuiContainer {
  private static final ResourceLocation BG_TEXTURE = new ResourceLocation(McDiscMod.MODID, "textures/gui/portable_jukebox.png");

  public PortableJukeboxGuiContainer(Container inventorySlotsIn) {
    super(inventorySlotsIn);
  }

  @Override
  protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
    GlStateManager.color(1, 1, 1, 1);
    mc.getTextureManager().bindTexture(BG_TEXTURE);
    int x = (width - xSize) / 2;
    int y = (height - ySize) / 2;
    drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
  }

  @Override
  protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
    super.drawGuiContainerForegroundLayer(mouseX, mouseY);
  }
}
