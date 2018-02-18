package com.lambdanum.mcdisc;

import com.lambdanum.mcdisc.looting.CustomDiscCreeper;

import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CreeperSpawnEventHandler {

    @SubscribeEvent
    public void onEntitySpawn(LivingSpawnEvent.SpecialSpawn event) {
        if (event.getWorld().isRemote || !McdiscConfig.CREEPERS_DROP_CUSTOM_DISCS) {
            return;
        }
        if (event.getEntityLiving() instanceof EntityCreeper && !(event.getEntityLiving() instanceof CustomDiscCreeper)) {
            EntityCreeper vanillaCreeper = (EntityCreeper) event.getEntityLiving();
            CustomDiscCreeper customCreeper = new CustomDiscCreeper(event.getWorld());
            BlockPos position = vanillaCreeper.getPosition();
            customCreeper.setLocationAndAngles(position.getX(), position.getY(), position.getZ(), vanillaCreeper.rotationYaw, vanillaCreeper.rotationPitch);
            event.getWorld().spawnEntity(customCreeper);
            vanillaCreeper.setDead();
        }
    }
}
