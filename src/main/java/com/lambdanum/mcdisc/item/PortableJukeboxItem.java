//package com.lambdanum.mcdisc.item;
//
//import com.lambdanum.mcdisc.McDiscGuiHandler;
//import com.lambdanum.mcdisc.McDiscMod;
//import com.lambdanum.mcdisc.McdiscConfig;
//import net.minecraft.client.renderer.block.model.ModelResourceLocation;
//import net.minecraft.creativetab.CreativeTabs;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.entity.player.InventoryPlayer;
//import net.minecraft.inventory.Container;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemStack;
//import net.minecraft.util.ActionResult;
//import net.minecraft.util.EnumActionResult;
//import net.minecraft.util.EnumHand;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//import net.minecraftforge.client.model.ModelLoader;
//import net.minecraftforge.fml.common.FMLCommonHandler;
//
//public class PortableJukeboxItem extends Item {
//  public static final String NAME = "portable_jukebox";
//
//  public PortableJukeboxItem() {
//    setTranslationKey(NAME);
//    setRegistryName(NAME);
//    setCreativeTab(CreativeTabs.MISC);
//  }
//
//  @Override
//  public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
//    if (!worldIn.isRemote) {
//      BlockPos position = playerIn.getPosition();
//      playerIn.openGui(McDiscMod.INSTANCE, McDiscGuiHandler.PORTABLE_JUKEBOX, worldIn, position.getX(), position.getY(), position.getZ());
//      return ActionResult.newResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
//    }
//    return super.onItemRightClick(worldIn, playerIn, handIn);
//  }
//
//  public PortableJukeboxContainer createContainer(InventoryPlayer playerInventory) {
//    ItemStack currentlyHeld = playerInventory.getCurrentItem();
//    if (currentlyHeld.getItem() instanceof PortableJukeboxItem) {
//
//      return new PortableJukeboxContainer(playerInventory);
//    }
//    throw new UnsupportedOperationException("not implemented");
//  }
//}
