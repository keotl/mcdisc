package com.lambdanum.mcdisc.playback.network;

import com.lambdanum.mcdisc.playback.MovingMusicPlayer;
import com.lambdanum.mcdisc.playback.network.client.PlayingSoundRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PortableJukeboxPlayPacketHandler implements IMessageHandler<PortableJukeboxPlayPacket, IMessage> {


  @Override
  public IMessage onMessage(PortableJukeboxPlayPacket message, MessageContext ctx) {
    WorldClient world = Minecraft.getMinecraft().world;
    EntityPlayer sourcePlayer = world.getPlayerEntityByName(message.sourcePlayer);
    if (sourcePlayer == null) {
      return null;
    }

    ResourceLocation soundLocation = new ResourceLocation(message.soundId);

    MovingMusicPlayer sound = new MovingMusicPlayer(sourcePlayer, new SoundEvent(soundLocation));

    PlayingSoundRepository.INSTANCE.stop(message.sourcePlayer);

    Minecraft.getMinecraft()
        .getSoundHandler()
        .playSound(sound);

    PlayingSoundRepository.INSTANCE.add(message.sourcePlayer, sound);

    return null;
  }
}
