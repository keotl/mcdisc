package com.lambdanum.mcdisc.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
// The inventory
public class PortableJukeboxContainer extends Container {

  @Override
  public boolean canInteractWith(EntityPlayer playerIn) {
    return true;
  }
}
