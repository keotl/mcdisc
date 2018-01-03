package com.lambdanum.mcdisc;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = McDiscMod.MODID)
public class McdiscConfig {

    public static String DISC_LIST_LOCATION = "disc-list.json";

    @Mod.EventBusSubscriber
    private static class EventHandler {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(McDiscMod.MODID)) {
                ConfigManager.sync(McDiscMod.MODID, Config.Type.INSTANCE);
            }
        }
    }
}
