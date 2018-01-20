package com.lambdanum.mcdisc;

import com.lambdanum.mcdisc.looting.CustomDiscCreeper;
import com.lambdanum.mcdisc.looting.LootEntryFactory;
import com.lambdanum.mcdisc.looting.LootLocationRepository;
import com.lambdanum.mcdisc.model.Disc;
import com.lambdanum.mcdisc.repository.DiscRepositoryFactory;

import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.conditions.RandomChance;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;

@Mod(modid = McDiscMod.MODID, version = "@VERSION@")
@Mod.EventBusSubscriber
public class McDiscMod {

    public static final String MODID = "mcdisc";

    private static List<Disc> discs;
    private static LootLocationRepository lootLocationRepository = new LootLocationRepository();

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

    @SubscribeEvent
    public static void registerLoot(LootTableLoadEvent event) {
        if (lootLocationRepository.getAllowedLootLocations().contains(event.getName())) {
            event.getTable().addPool(createDiscPool());
        }
    }

    private static LootPool createDiscPool() {
        LootEntryFactory lootEntryFactory = new LootEntryFactory();

        LootEntry[] lootEntries = lootEntryFactory.createCustomDiscLootEntries();
        return new LootPool(lootEntries,
            new LootCondition[]{new RandomChance(1f)},
            new RandomValueRange(1f), new RandomValueRange(0f), "custom discs");
    }

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
        if (McdiscConfig.CREEPERS_DROP_CUSTOM_DISCS) {
            EntityEntry creeperEntry = new EntityEntry(CustomDiscCreeper.class, "creeper");
            creeperEntry.setRegistryName("minecraft:creeper");
            event.getRegistry().register(creeperEntry);
        }
    }
}
