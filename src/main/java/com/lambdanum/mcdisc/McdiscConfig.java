package com.lambdanum.mcdisc;

//@Config(modid = McDiscMod.MODID)
public class McdiscConfig {

    public static String DISC_LIST_LOCATION = "https://github.com/KEOTL/mcdisc/raw/master/sample-disc-config.json";

    public static boolean SPAWN_DISCS_IN_CHESTS = true;

    public static boolean CREEPERS_DROP_CUSTOM_DISCS = true;

    public static boolean SHOULD_CACHE_DISC_LIST = true;

//    @Mod.EventBusSubscriber
//    private static class EventHandler {
//        @SubscribeEvent
//        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
//            if (event.getModID().equals(McDiscMod.MODID)) {
//                ConfigManager.sync(McDiscMod.MODID, Config.Type.INSTANCE);
//            }
//        }
//    }
}
