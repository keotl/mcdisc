package com.lambdanum.mcdisc;

import com.lambdanum.mcdisc.model.Disc;
import com.lambdanum.mcdisc.playback.playlist.SoundDurationService;
import com.lambdanum.mcdisc.repository.DiscRepositoryFactory;
import java.util.List;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mod(McDiscMod.MODID)
@Mod.EventBusSubscriber
public class McDiscMod {

  public static McDiscMod INSTANCE;

  public static final String MODID = "mcdisc";
  public static SoundDurationService SOUND_DURATION_SERVICE;

  private static List<Disc> discs;
  private static McDiscItems MOD_ITEMS;
//  public static final SimpleNetworkWrapper NETWORK_WRAPPER = new SimpleNetworkWrapper("mcdisc");

//  private static LootLocationRepository lootLocationRepository = new LootLocationRepository();

  public McDiscMod() {
    INSTANCE = this;
    MinecraftForge.EVENT_BUS.register(this);
  }

  @SubscribeEvent
  public void preInit(FMLCommonSetupEvent event) {
    DiscRepositoryFactory discRepositoryFactory = new DiscRepositoryFactory();
    DiscRepository discRepository = discRepositoryFactory.getDiscRepository(McdiscConfig.DISC_LIST_LOCATION);
    discs = discRepository.getDiscs();
    MOD_ITEMS = new McDiscItems(discs);

    SOUND_DURATION_SERVICE = new SoundDurationService(discs);
//    NetworkRegistry.INSTANCE.registerGuiHandler(this, new McDiscGuiHandler());


//    if (event.getSide().isClient()) {
//      // Handler to play the sound for clients
//      NETWORK_WRAPPER.registerMessage(PortableJukeboxPlayPacketHandler.class, PortableJukeboxPlayPacket.class, 0, Side.CLIENT);
//      NETWORK_WRAPPER.registerMessage(PortableJukeboxStopPlaylistMessageClientHandler.class, PortableJukeboxStopPlaylistMessage.class, 2, Side.CLIENT);
//    } else {
//      NETWORK_WRAPPER.registerMessage(DummyPlayPacketHandler.class, PortableJukeboxPlayPacket.class, 0, Side.SERVER);
//    }

    // Handler to maintain the active playlists on the server

//    NETWORK_WRAPPER.registerMessage(PortableJukeboxStartPlaylistMessageHandler.class, PortableJukeboxStartPlaylistMessage.class, 1, Side.SERVER);
//    NETWORK_WRAPPER.registerMessage(PortableJukeboxStopPlaylistMessageServerHandler.class, PortableJukeboxStopPlaylistMessage.class, 2, Side.SERVER);
  }

//  @Mod.EventHandler
//  public void afterStart(FMLServerStartedEvent event) {
//    PlaylistManager.INSTANCE.startWorker();
//  }

//  @Mod.EventHandler
//  public void beforeShutdown(FMLServerStoppingEvent event) {
//    PlaylistManager.INSTANCE.stopWorker();
//  }

  @SubscribeEvent(priority = EventPriority.LOWEST)
  public static void registerItems(RegistryEvent.Register<Item> event) {
    System.out.println("Config value :" + McdiscConfig.DISC_LIST_LOCATION);
    MOD_ITEMS.registerItems(event);

    if (FMLEnvironment.dist.isClient()) {
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

//  @SubscribeEvent
//  public static void registerLoot(LootTableLoadEvent event) {
//    if (lootLocationRepository.getAllowedLootLocations().contains(event.getName())) {
//      event.getTable().addPool(createDiscPool());
//    }
//  }

//  private static LootPool createDiscPool() {
//    LootEntryFactory lootEntryFactory = new LootEntryFactory();
//
//    LootEntry[] lootEntries = lootEntryFactory.createCustomDiscLootEntries();
//    LootPool.builder().name("custom discs").addEntry()
//
//    return new LootPool(lootEntries,
//        new ILootCondition[] {RandomChance.builder(1f).build()},
//        new RandomValueRange(1f), new RandomValueRange(0f), "custom discs");
//  }

//  @SubscribeEvent
//  public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
//    MinecraftForge.EVENT_BUS.register(new CreeperSpawnEventHandler());
//    EntityEntry creeperEntry = new EntityEntry(CustomDiscCreeper.class, "creeper");
//    creeperEntry.setRegistryName("mcdisc:creeper");
//    event.getRegistry().register(creeperEntry);
//  }
}
