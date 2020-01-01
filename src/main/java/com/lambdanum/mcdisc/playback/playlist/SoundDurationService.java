package com.lambdanum.mcdisc.playback.playlist;

import com.lambdanum.mcdisc.model.Disc;
import java.util.List;

public class SoundDurationService {

  private List<Disc> customDiscs;

  public SoundDurationService(List<Disc> customDiscs) {
    this.customDiscs = customDiscs;
  }

  public int getDuration(String soundId) {
    return customDiscs.stream()
        .filter(disc -> disc.soundId.equals(soundId))
        .map(disc -> disc.duration)
        .findFirst()
        .orElse(5);
  }
}
