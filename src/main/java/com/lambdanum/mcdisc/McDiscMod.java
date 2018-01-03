package com.lambdanum.mcdisc;

import com.lambdanum.mcdisc.model.Disc;
import com.lambdanum.mcdisc.repository.DiscRepositoryFactory;

import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = McDiscMod.MODID, version = "@VERSION@")
@Mod.EventBusSubscriber
public class McDiscMod {

    public static final String MODID = "mcdisc";

    private static List<Disc> discs;

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        DiscRepositoryFactory discRepositoryFactory = new DiscRepositoryFactory();
        DiscRepository discRepository = discRepositoryFactory.getDiscRepository(McdiscConfig.DISC_LIST_LOCATION);
        discs = discRepository.getDiscs();
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerItems(RegistryEvent.Register<Item> event) {
        System.out.println("Config value :" + McdiscConfig.DISC_LIST_LOCATION);
        for (Disc disc : discs) {
            event.getRegistry().register(new CustomRecord(disc));
        }
    }

    @SubscribeEvent
    public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
        for (Disc disc : discs) {
            SoundEvent soundEvent = new SoundEvent(new ResourceLocation("mcdisc", disc.soundId));
            soundEvent.setRegistryName(disc.soundId);
            event.getRegistry().register(soundEvent);
        }
    }
}
