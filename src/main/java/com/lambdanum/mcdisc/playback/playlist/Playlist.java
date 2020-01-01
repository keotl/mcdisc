package com.lambdanum.mcdisc.playback.playlist;

import com.lambdanum.mcdisc.playback.network.PortableJukeboxPlayPacket;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class Playlist {

  private int dimension;
  private EntityPlayer followingPlayer;
  private List<String> sounds;

  public Playlist(int dimension, EntityPlayer followingPlayer, List<String> sounds) {
    this.dimension = dimension;
    this.followingPlayer = followingPlayer;
    this.sounds = sounds;
  }

  public void advance(SimpleNetworkWrapper networkWrapper) {
    if (sounds.size() == 0) {
      // TODO handle playback done.
      return;
    }

    String nextSound = sounds.remove(0);
    networkWrapper.sendToDimension(new PortableJukeboxPlayPacket(followingPlayer.getName(), nextSound), dimension);
  }
}
