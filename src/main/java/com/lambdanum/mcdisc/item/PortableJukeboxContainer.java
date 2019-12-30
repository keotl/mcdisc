package com.lambdanum.mcdisc.item;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemRecord;

// The inventory
public class PortableJukeboxContainer extends Container {

  private InventoryPlayer playerInventory;
  private PortableJukeboxInventory content;

  public PortableJukeboxContainer(InventoryPlayer playerInventory, List<ItemRecord> content) {
    this.playerInventory = playerInventory;
    this.content = new PortableJukeboxInventory();
    initializeSlots();
  }

  @Override
  public boolean canInteractWith(EntityPlayer playerIn) {
    return true;
  }

  private void initializeSlots() {
    // jukebox content
    for (int i = 0; i < 4; i++) {
      for (int j=0; j < 2; j++) {
        addSlotToContainer(new Slot(content, j*4+i, 8+j*18, 6+i*18));
      }
    }

    // inventory
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 9; j++) {
        addSlotToContainer(new Slot(this.playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
      }
    }

    // inventory hotbar
    for (int k = 0; k < 9; k++) {
      addSlotToContainer(new Slot(this.playerInventory, k, 8 + k * 18, 142));
    }
  }
}
