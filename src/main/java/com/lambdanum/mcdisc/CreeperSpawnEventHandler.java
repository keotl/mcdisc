//package com.lambdanum.mcdisc;
//
//import com.lambdanum.mcdisc.looting.CustomDiscCreeper;
//
//import net.minecraft.entity.monster.CreeperEntity;
//import net.minecraft.entity.monster.EntityCreeper;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.vector.Vector3d;
//import net.minecraftforge.event.entity.living.LivingSpawnEvent;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//
//public class CreeperSpawnEventHandler {
//
//    @SubscribeEvent
//    public void onEntitySpawn(LivingSpawnEvent.SpecialSpawn event) {
//        if (event.getWorld().isRemote()|| !McdiscConfig.CREEPERS_DROP_CUSTOM_DISCS) {
//            return;
//        }
//        if (event.getEntityLiving() instanceof CreeperEntity&& !(event.getEntityLiving() instanceof CustomDiscCreeper)) {
//            CreeperEntity vanillaCreeper = (CreeperEntity) event.getEntityLiving();
//            CustomDiscCreeper customCreeper = new CustomDiscCreeper(event.getWorld());
//            Vector3d position = vanillaCreeper.getPositionVec();
//            customCreeper.setLocationAndAngles(position.getX(), position.getY(), position.getZ(), vanillaCreeper.rotationYaw, vanillaCreeper.rotationPitch);
//            event.getWorld().spawnEntity(customCreeper);
//            vanillaCreeper.setDead();
//        }
//    }
//}
