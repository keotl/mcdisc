package com.lambdanum.mcdisc.playback.playlist;

import com.lambdanum.mcdisc.playback.network.PortableJukeboxPlayPacket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class Playlist {

  private int dimension;
  private EntityPlayer followingPlayer;
  private List<String> sounds;


  private LocalDateTime nextTime;

  public Playlist(int dimension, EntityPlayer followingPlayer, List<String> sounds) {
    this.dimension = dimension;
    this.followingPlayer = followingPlayer;
    this.sounds = sounds;
    nextTime = LocalDateTime.MIN;
  }

  public boolean shouldAdvance() {
    return LocalDateTime.now().isAfter(nextTime);
  }

  public boolean isEmpty() {
    return sounds.isEmpty();
  }

  public void advance(SimpleNetworkWrapper networkWrapper, SoundDurationService soundDurationService) {
    if (sounds.size() == 0) {
      return;
    }

    String nextSound = sounds.remove(0);

    nextTime = LocalDateTime.now().plusSeconds(soundDurationService.getDuration(nextSound));
    networkWrapper.sendToDimension(new PortableJukeboxPlayPacket(followingPlayer.getName(), nextSound), dimension);
  }
}
