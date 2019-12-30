package com.lambdanum.mcdisc.item;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemRecord;

// The inventory
public class PortableJukeboxContainer extends Container {

  private InventoryPlayer playerInventory;
  private List<ItemRecord> content;

  public PortableJukeboxContainer(InventoryPlayer playerInventory, List<ItemRecord> content) {
    this.playerInventory = playerInventory;
    this.content = content;
  }

  @Override
  public boolean canInteractWith(EntityPlayer playerIn) {
    return true;
  }
}
