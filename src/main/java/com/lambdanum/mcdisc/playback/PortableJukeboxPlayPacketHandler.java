package com.lambdanum.mcdisc.playback;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import scala.collection.parallel.ParIterableLike;

public class PortableJukeboxPlayPacketHandler implements IMessageHandler<PortableJukeboxPlayPacket, IMessage> {
  @Override
  public IMessage onMessage(PortableJukeboxPlayPacket message, MessageContext ctx) {
    WorldClient world = Minecraft.getMinecraft().world;
    EntityPlayer sourcePlayer = world.getPlayerEntityByName(message.sourcePlayer);
    Minecraft.getMinecraft().getSoundHandler().playSound(new MovingMusicPlayer(sourcePlayer, new SoundEvent(new ResourceLocation(message.soundId))));
    return null;
  }
}
