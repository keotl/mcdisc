package com.lambdanum.mcdisc.playback.playlist;

import com.lambdanum.mcdisc.McDiscMod;
import java.util.ArrayList;
import java.util.List;

public class PlaylistManager {

  public static PlaylistManager INSTANCE = new PlaylistManager();

  private List<Playlist> playlists;

  public PlaylistManager() {
    playlists = new ArrayList<>();
  }

  public void add(Playlist playlist) {
    playlists.add(playlist);
    playlist.advance(McDiscMod.NETWORK_WRAPPER);
  }
}
