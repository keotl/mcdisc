package com.lambdanum.mcdisc.item;

import com.lambdanum.mcdisc.McDiscMod;
import com.lambdanum.mcdisc.playback.network.PortableJukeboxPlayPacket;
import com.lambdanum.mcdisc.playback.network.PortableJukeboxStartPlaylistMessage;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class PortableJukeboxGuiContainer extends GuiContainer {
  private static final ResourceLocation BG_TEXTURE = new ResourceLocation(McDiscMod.MODID, "textures/gui/portable_jukebox.png");
  private EntityPlayer player;
  private PortableJukeboxContainer container;

  public PortableJukeboxGuiContainer(EntityPlayer player, PortableJukeboxContainer inventorySlotsIn) {
    super(inventorySlotsIn);
    this.player = player;
    this.container = inventorySlotsIn;
  }

  @Override
  public void initGui() {
    super.initGui();

    buttonList.add(new GuiButton(0, 400, 100, 14, 8, "Play") {
      @Override
      public void mouseReleased(int mouseX, int mouseY) {
        if (container.getPlaylist().size() > 0) {
          McDiscMod.NETWORK_WRAPPER.sendToServer(new PortableJukeboxStartPlaylistMessage(
              player.dimension,
              player.getName(),
              container.getPlaylist()));
        }
      }
    });
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
