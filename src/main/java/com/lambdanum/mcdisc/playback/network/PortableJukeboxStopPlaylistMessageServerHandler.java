//package com.lambdanum.mcdisc.playback.network;
//
//import com.lambdanum.mcdisc.McDiscMod;
//import com.lambdanum.mcdisc.playback.playlist.PlaylistManager;
//import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
//import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
//import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
//
//public class PortableJukeboxStopPlaylistMessageServerHandler implements IMessageHandler<PortableJukeboxStopPlaylistMessage, IMessage> {
//
//  @Override
//  public IMessage onMessage(PortableJukeboxStopPlaylistMessage message, MessageContext ctx) {
//    PlaylistManager.INSTANCE.remove(message.player);
//    McDiscMod.NETWORK_WRAPPER.sendToAll(message);
//    return null;
//  }
//}
