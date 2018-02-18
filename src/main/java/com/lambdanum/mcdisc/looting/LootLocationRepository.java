package com.lambdanum.mcdisc.looting;

import com.lambdanum.mcdisc.McdiscConfig;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class LootLocationRepository {

    private static final ResourceLocation[] WORLD_LOOT_CHESTS =
        {LootTableList.CHESTS_ABANDONED_MINESHAFT, LootTableList.CHESTS_DESERT_PYRAMID, LootTableList.CHESTS_END_CITY_TREASURE, LootTableList.CHESTS_IGLOO_CHEST,
            LootTableList.CHESTS_JUNGLE_TEMPLE, LootTableList.CHESTS_SIMPLE_DUNGEON, LootTableList.CHESTS_NETHER_BRIDGE, LootTableList.CHESTS_SPAWN_BONUS_CHEST,
            LootTableList.CHESTS_STRONGHOLD_LIBRARY, LootTableList.CHESTS_WOODLAND_MANSION, LootTableList.CHESTS_VILLAGE_BLACKSMITH};

    public List<ResourceLocation> getAllowedLootLocations() {
        return McdiscConfig.SPAWN_DISCS_IN_CHESTS ? Arrays.asList(WORLD_LOOT_CHESTS) : Collections.emptyList();
    }
}
