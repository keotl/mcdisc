package com.lambdanum.mcdisc.item;

import com.lambdanum.mcdisc.item.serialization.InventorySerializer;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundEvent;

public class PortableJukeboxContainer extends Container {

  private InventorySerializer inventorySerializer = new InventorySerializer();

  private InventoryPlayer playerInventory;
  private PortableJukeboxInventory content;

  public PortableJukeboxContainer(InventoryPlayer playerInventory) {
    this.playerInventory = playerInventory;
    this.content = new PortableJukeboxInventory();

    if (!this.playerInventory.getCurrentItem().hasTagCompound()) {
      this.playerInventory.getCurrentItem().setTagCompound(new NBTTagCompound());
    }

    NBTTagCompound itemTag = this.playerInventory.getCurrentItem().getTagCompound();
    if (!itemTag.hasKey("content")) {
      itemTag.setTag("content", new NBTTagCompound());
    }

    inventorySerializer.deserializeToInventory(this.content, (NBTTagCompound) itemTag.getTag("content"));

    this.content.addInventoryChangeListener(newContent -> {
      ItemStack currentItem = this.playerInventory.getCurrentItem();
      if (!currentItem.hasTagCompound()) {
        currentItem.setTagCompound(new NBTTagCompound());
      }
      NBTTagCompound tagCompound = currentItem.getTagCompound();
      tagCompound.setTag("content", inventorySerializer.serializeToNbt(newContent));
    });
    initializeSlots();
  }

  public List<String> getPlaylist() {
    return IntStream.range(0, 8)
        .mapToObj(content::getStackInSlot)
        .map(ItemStack::getItem)
        .filter(item -> item instanceof ItemRecord)
        .map(item -> ((ItemRecord) item).getSound().getSoundName().toString())
        .collect(Collectors.toList());
  }

  @Override
  public boolean canInteractWith(EntityPlayer playerIn) {
    return true;
  }

  private void initializeSlots() {
    // jukebox content
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 2; j++) {
        addSlotToContainer(new Slot(content, j * 4 + i, 8 + j * 18, 6 + i * 18));
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
