package com.lambdanum.mcdisc.playback;

import io.netty.buffer.ByteBuf;
import java.nio.charset.Charset;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class PortableJukeboxPlayPacket implements IMessage {

  public int dimension;
  public  String sourcePlayer;
  public String soundId;

  // Used for deserialization
  public PortableJukeboxPlayPacket() {
  }

  public PortableJukeboxPlayPacket(int dimension, String sourcePlayer, String soundId) {
    this.dimension = dimension;
    this.sourcePlayer = sourcePlayer;
    this.soundId = soundId;
  }

  @Override
  public void fromBytes(ByteBuf buf) {
    dimension = buf.readInt();
    int sourcePlayerLength = buf.readInt();
    int soundIdLength = buf.readInt();
    sourcePlayer = buf.readCharSequence(sourcePlayerLength, Charset.defaultCharset()).toString();
    soundId = buf.readCharSequence(soundIdLength, Charset.defaultCharset()).toString();
  }

  @Override
  public void toBytes(ByteBuf buf) {
    buf.writeInt(dimension);
    buf.writeInt(sourcePlayer.length());
    buf.writeInt(soundId.length());
    buf.writeCharSequence(sourcePlayer, Charset.defaultCharset());
    buf.writeCharSequence(soundId, Charset.defaultCharset());
  }
}
