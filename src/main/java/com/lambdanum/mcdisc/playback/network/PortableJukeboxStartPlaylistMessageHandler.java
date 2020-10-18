//package com.lambdanum.mcdisc.playback.network;
//
//import com.lambdanum.mcdisc.playback.playlist.Playlist;
//import com.lambdanum.mcdisc.playback.playlist.PlaylistManager;
//import net.minecraft.entity.player.EntityPlayerMP;
//import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
//import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
//import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
//public class PortableJukeboxStartPlaylistMessageHandler implements IMessageHandler<PortableJukeboxStartPlaylistMessage, IMessage> {
//
//  @Override
//  public IMessage onMessage(PortableJukeboxStartPlaylistMessage message, MessageContext ctx) {
//    PlaylistManager.INSTANCE.remove(message.player);
//    PlaylistManager.INSTANCE.add(new Playlist(message.player, message.sounds));
//
//    return null;
//  }
//}
