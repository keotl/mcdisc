package com.lambdanum.mcdisc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.IOException;

@Mod(modid = "mcdisc", version = "0.0.1")
@Mod.EventBusSubscriber
public class McDiscMod {

    //private static SoundEvent soundEvent = new SoundEvent(new ResourceLocation("mcdisc:test"));

    private static ObjectMapper mapper = new ObjectMapper();
    private static Disc[] discs;

    public McDiscMod() {
        //MinecraftForge.EVENT_BUS.register(this);
        try {
            HttpResponse<String> jsonResponse = Unirest.get("https://boiling-forest-57763.herokuapp.com/release").asString();
            discs = mapper.readValue(jsonResponse.getBody(),Disc[].class);
        } catch (UnirestException | IOException e) {
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
