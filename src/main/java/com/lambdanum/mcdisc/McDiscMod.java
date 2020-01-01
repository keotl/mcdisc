package com.lambdanum.mcdisc;

import com.lambdanum.mcdisc.looting.CustomDiscCreeper;
import com.lambdanum.mcdisc.looting.LootEntryFactory;
import com.lambdanum.mcdisc.looting.LootLocationRepository;
import com.lambdanum.mcdisc.model.Disc;
import com.lambdanum.mcdisc.playback.network.PortableJukeboxPlayPacket;
import com.lambdanum.mcdisc.playback.network.PortableJukeboxPlayPacketHandler;
import com.lambdanum.mcdisc.playback.network.PortableJukeboxStartPlaylistMessage;
import com.lambdanum.mcdisc.playback.network.PortableJukeboxStartPlaylistMessageHandler;
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
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = McDiscMod.MODID, version = "@VERSION@", updateJSON = "https://raw.githubusercontent.com/keotl/mcdisc/master/update.json")
@Mod.EventBusSubscriber
public class McDiscMod {

  public static McDiscMod INSTANCE;

  public static final String MODID = "mcdisc";

  private static List<Disc> discs;
  private static McDiscItems MOD_ITEMS;
  public static final SimpleNetworkWrapper NETWORK_WRAPPER = new SimpleNetworkWrapper("mcdisc");

  private static LootLocationRepository lootLocationRepository = new LootLocationRepository();

  public McDiscMod() {
    INSTANCE = this;
  }

  @Mod.EventHandler
  public void preInit(FMLPreInitializationEvent event) {
    DiscRepositoryFactory discRepositoryFactory = new DiscRepositoryFactory();
    DiscRepository discRepository = discRepositoryFactory.getDiscRepository(McdiscConfig.DISC_LIST_LOCATION);
    discs = discRepository.getDiscs();
    MOD_ITEMS = new McDiscItems(discs);

    NetworkRegistry.INSTANCE.registerGuiHandler(this, new McDiscGuiHandler());


    if (event.getSide().isClient()) {
      // Handler to play the sound for clients
      NETWORK_WRAPPER.registerMessage(PortableJukeboxPlayPacketHandler.class, PortableJukeboxPlayPacket.class, 0, Side.CLIENT);
    }

    // Handler to maintain the active playlists on the server
    NETWORK_WRAPPER.registerMessage(PortableJukeboxStartPlaylistMessageHandler.class, PortableJukeboxStartPlaylistMessage.class, 1, Side.SERVER);
  }

  @SubscribeEvent(priority = EventPriority.LOWEST)
  public static void registerItems(RegistryEvent.Register<Item> event) {
    System.out.println("Config value :" + McdiscConfig.DISC_LIST_LOCATION);
    MOD_ITEMS.registerItems(event);

    if (FMLCommonHandler.instance().getSide().isClient()) {
      MOD_ITEMS.registerModels();
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
        new LootCondition[] {new RandomChance(1f)},
        new RandomValueRange(1f), new RandomValueRange(0f), "custom discs");
  }

  @SubscribeEvent
  public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
    MinecraftForge.EVENT_BUS.register(new CreeperSpawnEventHandler());
    EntityEntry creeperEntry = new EntityEntry(CustomDiscCreeper.class, "creeper");
    creeperEntry.setRegistryName("mcdisc:creeper");
    event.getRegistry().register(creeperEntry);
  }
}
