//package com.lambdanum.mcdisc.playback.network.client;
//
//import com.lambdanum.mcdisc.playback.MovingMusicPlayer;
//import java.util.HashMap;
//import java.util.Map;
//
//public class PlayingSoundRepository {
//  public static PlayingSoundRepository INSTANCE = new PlayingSoundRepository();
//  private Map<String, MovingMusicPlayer> playingSounds = new HashMap<>();
//
//  public void stop(String player) {
//    if (playingSounds.containsKey(player)) {
//      playingSounds.get(player).stop();
//      playingSounds.remove(player);
//    }
//  }
//
//  public void add(String player, MovingMusicPlayer sound) {
//    playingSounds.put(player, sound);
//  }
//}
