package com.lambdanum.mcdisc.playback;

import net.minecraft.client.audio.TickableSound;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MovingMusicPlayer extends TickableSound {

  private PlayerEntity entity;

  public MovingMusicPlayer(PlayerEntity entity, SoundEvent sound) {
    super(sound, SoundCategory.RECORDS);
    this.entity = entity;
    this.repeat = false;
  }

  @Override
  public void tick() {
    if (!entity.isLiving()) {
      this.func_239509_o_(); // this.stop()
    } else {
      this.x = (float) this.entity.getPosX();
      this.y = (float) this.entity.getPosY();
      this.z = (float) this.entity.getPosZ();
    }
  }
}
