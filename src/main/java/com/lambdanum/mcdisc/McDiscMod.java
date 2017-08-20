package com.lambdanum.mcdisc;

import com.google.gson.Gson;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = "mcdisc", version = "0.0.1")
@Mod.EventBusSubscriber
public class McDiscMod {

    //private static SoundEvent soundEvent = new SoundEvent(new ResourceLocation("mcdisc:test"));

    //private static ObjectMapper mapper = new ObjectMapper();
    private static final String RELEASE_URL = "https://boiling-forest-57763.herokuapp.com/release";
    private static Gson mapper = new Gson();
    private static Disc[] discs;

    public McDiscMod() {
        //MinecraftForge.EVENT_BUS.register(this);
        try {
            String jsonResponse = HttpGet.getHTML(RELEASE_URL);
            discs = mapper.fromJson(jsonResponse,Disc[].class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerItems(RegistryEvent.Register<Item> event) {
        //event.getRegistry().register(new CustomRecord("test", soundEvent));
        for (Disc disc : discs) {
            event.getRegistry().register(new CustomRecord(disc));
        }
    }

    @SubscribeEvent
    public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
        System.out.println("test");
        for (Disc disc : discs) {
            SoundEvent soundEvent = new SoundEvent(new ResourceLocation("mcdisc",disc.getSoundId()));
            soundEvent.setRegistryName(disc.getSoundId());
            event.getRegistry().register(soundEvent);
        }
    }


}
