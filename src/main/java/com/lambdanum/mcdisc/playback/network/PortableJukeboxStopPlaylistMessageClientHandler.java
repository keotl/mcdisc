//package com.lambdanum.mcdisc.playback.network;
//
//import com.lambdanum.mcdisc.playback.network.client.PlayingSoundRepository;
//import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
//import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
//import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
//
//public class PortableJukeboxStopPlaylistMessageClientHandler implements IMessageHandler<PortableJukeboxStopPlaylistMessage, IMessage> {
//
//  @Override
//  public IMessage onMessage(PortableJukeboxStopPlaylistMessage message, MessageContext ctx) {
//    PlayingSoundRepository.INSTANCE.stop(message.player);
//    return null;
//  }
//}
