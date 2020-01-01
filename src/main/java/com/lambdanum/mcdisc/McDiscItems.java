package com.lambdanum.mcdisc;

import com.lambdanum.mcdisc.item.CustomRecord;
import com.lambdanum.mcdisc.item.PortableJukeboxItem;
import com.lambdanum.mcdisc.model.Disc;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class McDiscItems {

  public static McDiscItems INSTANCE;

  public List<CustomRecord> customRecords;
  public PortableJukeboxItem portableJukebox;

  public McDiscItems(List<Disc> discs) {
    customRecords = discs.stream().map(CustomRecord::new).collect(Collectors.toList());
    this.portableJukebox = new PortableJukeboxItem();
    INSTANCE = this;
  }

  public void registerItems(RegistryEvent.Register<Item> event) {

    customRecords.forEach(event.getRegistry()::register);
    event.getRegistry().register(portableJukebox);
  }

  public void registerModels() {
    if (FMLCommonHandler.instance().getSide().isClient()) {
      customRecords.forEach(CustomRecord::registerModel);
      ModelLoader.setCustomModelResourceLocation(portableJukebox, 0, new ModelResourceLocation("mcdisc:portable_jukebox", "inventory"));
    }
  }
}
