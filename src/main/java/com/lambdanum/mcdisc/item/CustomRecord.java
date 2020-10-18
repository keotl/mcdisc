package com.lambdanum.mcdisc.item;

import com.lambdanum.mcdisc.McDiscMod;
import com.lambdanum.mcdisc.model.Disc;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.model.ModelLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomRecord extends MusicDiscItem {
  private static final Logger LOGGER = LogManager.getLogger(CustomRecord.class);
  private Disc disc;

  public CustomRecord(Disc disc) {
    super(1, () -> new SoundEvent(new ResourceLocation("mcdisc", disc.soundId)), new Properties());
    this.disc = disc;
    this.setRegistryName(new ResourceLocation(McDiscMod.MODID, disc.minecraftId));
  }

  public void registerModel() {
    ModelLoader.addSpecialModel(new ResourceLocation(getDiscModelLocation(disc), "inventory"));
//    ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getDiscModelLocation(disc), "inventory"));
  }

  private String getDiscModelLocation(Disc disc) {
    if (disc.texture != null && !"".equals(disc.texture)) {
      if (!disc.texture.startsWith("minecraft:")) {
        LOGGER.error("Custom Disc " + disc.minecraftId + " is missing 'minecraft:' in its custom texture path. It will probably not work!");
      }
      return disc.texture;
    } else {
      switch (disc.id % 12) {
        case 1:
          return "minecraft:record_blocks";
        case 2:
          return "minecraft:record_13";
        case 3:
          return "minecraft:record_11";
        case 4:
          return "minecraft:record_cat";
        case 5:
          return "minecraft:record_chirp";
        case 6:
          return "minecraft:record_far";
        case 7:
          return "minecraft:record_mall";
        case 8:
          return "minecraft:record_mellohi";
        case 9:
          return "minecraft:record_stal";
        case 10:
          return "minecraft:record_strad";
        case 11:
          return "minecraft:record_wait";
        default:
          return "minecraft:record_ward";
      }
    }
  }
}
