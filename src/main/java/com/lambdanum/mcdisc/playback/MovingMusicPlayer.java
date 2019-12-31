package com.lambdanum.mcdisc.playback;

import net.minecraft.client.audio.MovingSound;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MovingMusicPlayer extends MovingSound {

  private EntityPlayer entity;

  public MovingMusicPlayer(EntityPlayer entity, SoundEvent sound) {
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
    }
  }
}
