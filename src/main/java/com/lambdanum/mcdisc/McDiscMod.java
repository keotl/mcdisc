package com.lambdanum.mcdisc;

import com.google.gson.Gson;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = "mcdisc", version = "@VERSION@")
@Mod.EventBusSubscriber
public class McDiscMod {

    private static final String RELEASE_URL = "https://boiling-forest-57763.herokuapp.com/release";
    private static Gson mapper = new Gson();
    private static Disc[] discs;

    public McDiscMod() {
        try {
            String jsonResponse = HttpUtil.get(RELEASE_URL);
            discs = mapper.fromJson(jsonResponse,Disc[].class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerItems(RegistryEvent.Register<Item> event) {
        for (Disc disc : discs) {
            event.getRegistry().register(new CustomRecord(disc));
        }
    }

    @SubscribeEvent
    public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
        for (Disc disc : discs) {
            SoundEvent soundEvent = new SoundEvent(new ResourceLocation("mcdisc",disc.soundId));
            soundEvent.setRegistryName(disc.soundId);
            event.getRegistry().register(soundEvent);
        }
    }
}
