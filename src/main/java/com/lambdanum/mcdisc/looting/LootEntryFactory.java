package com.lambdanum.mcdisc.looting;

import com.lambdanum.mcdisc.DiscRepository;
import com.lambdanum.mcdisc.McdiscConfig;
import com.lambdanum.mcdisc.repository.DiscRepositoryFactory;

import net.minecraft.item.Item;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.conditions.RandomChance;
import net.minecraft.world.storage.loot.functions.LootFunction;

public class LootEntryFactory {

    private DiscRepository discRepository;

    public LootEntryFactory() {
        discRepository = new DiscRepositoryFactory().getDiscRepository(McdiscConfig.DISC_LIST_LOCATION);
    }

    public LootEntry[] createCustomDiscLootEntries() {
        return discRepository.getDiscs().stream().map(disc ->
            new LootEntryItem(
                Item.getByNameOrId("mcdisc:" + disc.minecraftId),
                1,
                1,
                new LootFunction[] {},
                new LootCondition[] {new RandomChance(1f)},
                disc.minecraftId)
        ).toArray(LootEntry[]::new);
    }

}
