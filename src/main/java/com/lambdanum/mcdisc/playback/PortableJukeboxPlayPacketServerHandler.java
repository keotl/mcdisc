package com.lambdanum.mcdisc.playback;

import com.lambdanum.mcdisc.McDiscMod;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PortableJukeboxPlayPacketServerHandler implements IMessageHandler<PortableJukeboxPlayPacket, IMessage>{
  @Override
  public IMessage onMessage(PortableJukeboxPlayPacket message, MessageContext ctx) {
    McDiscMod.NETWORK_WRAPPER.sendToDimension(message, message.dimension);

    return null;
  }
}
