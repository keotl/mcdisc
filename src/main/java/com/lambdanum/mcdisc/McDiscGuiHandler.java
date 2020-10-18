//package com.lambdanum.mcdisc;
//
//import com.lambdanum.mcdisc.item.PortableJukeboxGuiContainer;
//import com.lambdanum.mcdisc.item.PortableJukeboxItem;
//import javax.annotation.Nullable;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.item.ItemStack;
//import net.minecraft.world.World;
//import net.minecraftforge.fml.common.network.IGuiHandler;
//
//public class McDiscGuiHandler implements IGuiHandler {
//
//  public static final int PORTABLE_JUKEBOX = 0;
//
//  @Nullable
//  @Override
//  public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
//    switch (ID) {
//      case PORTABLE_JUKEBOX:
//        ItemStack currentlyHeld = player.inventory.getCurrentItem();
//        if (currentlyHeld.getItem() instanceof PortableJukeboxItem) {
//          return ((PortableJukeboxItem) currentlyHeld.getItem()).createContainer(player.inventory);
//        }
//      default:
//        return null;
//    }
//  }
//
//  @Nullable
//  @Override
//  public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
//    switch (ID) {
//      case PORTABLE_JUKEBOX:
//        ItemStack currentlyHeld = player.inventory.getCurrentItem();
//        if (currentlyHeld.getItem() instanceof PortableJukeboxItem) {
//          return new PortableJukeboxGuiContainer(player,((PortableJukeboxItem) currentlyHeld.getItem()).createContainer(player.inventory));
//        }
//
//      default:
//        return null;
//    }
//  }
//}
