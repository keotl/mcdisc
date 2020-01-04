package com.lambdanum.mcdisc.playback.network;

import io.netty.buffer.ByteBuf;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class PortableJukeboxStartPlaylistMessage implements IMessage {

  public String player;
  public List<String> sounds;

  // Used for deserialization
  public PortableJukeboxStartPlaylistMessage() {
  }

  public PortableJukeboxStartPlaylistMessage(String player, List<String> sounds) {
    this.player = player;
    this.sounds = sounds;
  }

  @Override
  public void fromBytes(ByteBuf buf) {
    int playerLength = buf.readInt();
    int soundsLength = buf.readInt();
    player = buf.readCharSequence(playerLength, Charset.forName("UTF-8")).toString();
    String serializedSounds = buf.readCharSequence(soundsLength, Charset.forName("UTF-8")).toString();
    sounds = Arrays.stream(serializedSounds.split(",")).collect(Collectors.toList());
  }

  @Override
  public void toBytes(ByteBuf buf) {
    buf.writeInt(player.length());
    String serializedSounds = String.join(",", sounds);
    buf.writeInt(serializedSounds.length());
    buf.writeCharSequence(player, Charset.forName("UTF-8"));
    buf.writeCharSequence(serializedSounds, Charset.forName("UTF-8"));
  }
}
