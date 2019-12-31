package com.lambdanum.mcdisc.playback;

import net.minecraft.client.audio.MovingSound;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;

public class JoggingModeMusicPlayer extends MovingSound {
  private EntityPlayer entity;

  public JoggingModeMusicPlayer(EntityPlayer entity, SoundEvent sound) {
    super(sound, SoundCategory.RECORDS);
    this.entity = entity;
    this.repeat = false;
  }

  public void stop() {
    this.donePlaying = true;
  }

  @Override
  public void update() {
    if (entity.isDead) {
      stop();
    } else {
      this.xPosF = (float) this.entity.posX;
      this.yPosF = (float) this.entity.posY;
      this.zPosF = (float) this.entity.posZ;
      float f = MathHelper.sqrt(this.entity.motionX * this.entity.motionX + this.entity.motionZ * this.entity.motionZ);

      if ((double) f >= 0.01D) {
        this.volume = 0.0F + MathHelper.clamp(f, 0.0F, 0.5F) * 0.7F;
      } else {
        this.volume = 0.0F;
      }
    }
  }
}
