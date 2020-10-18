//package com.lambdanum.mcdisc.item.serialization;
//
//import net.minecraft.inventory.IInventory;
//import net.minecraft.item.ItemStack;
//import net.minecraft.nbt.NBTTagCompound;
//
//public class InventorySerializer {
//
//  public NBTTagCompound serializeToNbt(IInventory inventory) {
//    NBTTagCompound nbt = new NBTTagCompound();
//
//    for (Integer i = 0; i < inventory.getSizeInventory(); i++) {
//      NBTTagCompound stackTag = new NBTTagCompound();
//      inventory.getStackInSlot(i).writeToNBT(stackTag);
//      nbt.setTag(i.toString(), stackTag);
//    }
//
//    return nbt;
//  }
//
//  public <T extends IInventory> T deserializeToInventory(T inventory, NBTTagCompound nbt) {
//    for (Integer slot = 0; slot < inventory.getSizeInventory(); slot++) {
//      if (nbt.hasKey(slot.toString())) {
//        inventory.setInventorySlotContents(slot, new ItemStack((NBTTagCompound) nbt.getTag(slot.toString())));
//      }
//    }
//    return inventory;
//  }
//}
