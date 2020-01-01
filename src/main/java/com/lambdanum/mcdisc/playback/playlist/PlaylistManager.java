package com.lambdanum.mcdisc.playback.playlist;

import com.lambdanum.mcdisc.McDiscMod;
import com.lambdanum.mcdisc.playback.network.PortableJukeboxStopPlaylistMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class PlaylistManager {

  public static PlaylistManager INSTANCE = new PlaylistManager();

  private List<Playlist> playlists;
  private Thread workerThread;
  private boolean shouldStopWorker;

  private final Object lock = new Object();

  private PlaylistManager() {
    playlists = new ArrayList<>();
    workerThread = new Thread(new PlaylistManagerWorker());
    shouldStopWorker = false;
  }

  public void add(Playlist playlist) {
    playlists.add(playlist);
    playlist.advance(McDiscMod.NETWORK_WRAPPER, McDiscMod.SOUND_DURATION_SERVICE);
  }

  public void remove(String player) {
    synchronized (lock) {
      playlists = playlists.stream().filter(p -> !p.matchesPlayer(player)).collect(Collectors.toList());
    }

  }

  public void startWorker() {
    workerThread.start();
  }

  public void stopWorker() {
    shouldStopWorker = true;
  }

  private class PlaylistManagerWorker implements Runnable {

    @Override
    public void run() {
      try {
        while (!shouldStopWorker) {
          synchronized (lock) {
            playlists = playlists.stream().filter(p -> !p.isEmpty()).collect(Collectors.toList());
            playlists.stream()
                .filter(Playlist::shouldAdvance)
                .forEach(p -> p.advance(McDiscMod.NETWORK_WRAPPER, McDiscMod.SOUND_DURATION_SERVICE));
          }
          try {
            Thread.sleep(5000L);
          } catch (InterruptedException e) {
            // Ignored
          }
        }
      } finally {
        System.out.println("Stopped the playlist worker thread.");
      }
    }
  }
}
