package com.lambdanum.mcdisc;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = McDiscMod.MODID)
public class McdiscConfig {

    public static String DISC_LIST_LOCATION = "https://github.com/KEOTL/mcdisc/raw/master/sample-disc-config.json";

    public static boolean SPAWN_DISCS_IN_CHESTS = true;

    public static boolean CREEPERS_DROP_CUSTOM_DISCS = true;

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
