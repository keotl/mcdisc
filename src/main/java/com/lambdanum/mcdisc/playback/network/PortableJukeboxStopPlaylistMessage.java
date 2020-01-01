package com.lambdanum.mcdisc.playback.network;

import io.netty.buffer.ByteBuf;
import java.nio.charset.Charset;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class PortableJukeboxStopPlaylistMessage implements IMessage {

  public String player;

  // For deserialization
  public PortableJukeboxStopPlaylistMessage() {
  }

  public PortableJukeboxStopPlaylistMessage(String player) {
    this.player = player;
  }

  @Override
  public void fromBytes(ByteBuf buf) {
    int playerLength = buf.readInt();
    player = buf.readCharSequence(playerLength, Charset.forName("UTF-8")).toString();
  }

  @Override
  public void toBytes(ByteBuf buf) {
    buf.writeInt(player.length());
    buf.writeCharSequence(player, Charset.forName("UTF-8"));
  }
}
