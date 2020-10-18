package com.lambdanum.mcdisc.playback;

import net.minecraft.client.audio.TickableSound;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;

public class JoggingModeMusicPlayer extends TickableSound {
  private PlayerEntity entity;

  public JoggingModeMusicPlayer(PlayerEntity entity, SoundEvent sound) {
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
      float f = MathHelper.sqrt(this.entity.getMotion().x * this.entity.getMotion().x + this.entity.getMotion().z * this.entity.getMotion().z);

      if ((double) f >= 0.01D) {
        this.volume = 0.0F + MathHelper.clamp(f, 0.0F, 0.5F) * 0.7F;
      } else {
        this.volume = 0.0F;
      }
    }
  }
}
