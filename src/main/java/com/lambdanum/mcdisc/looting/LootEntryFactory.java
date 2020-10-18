package com.lambdanum.mcdisc.looting;

import com.lambdanum.mcdisc.DiscRepository;
import com.lambdanum.mcdisc.McdiscConfig;
import com.lambdanum.mcdisc.repository.DiscRepositoryFactory;

public class LootEntryFactory {

    private DiscRepository discRepository;

    public LootEntryFactory() {
        discRepository = new DiscRepositoryFactory().getDiscRepository(McdiscConfig.DISC_LIST_LOCATION);
    }

//    public LootEntry[] createCustomDiscLootEntries() {
//        return discRepository.getDiscs().stream().map(disc ->
//            new ItemLootEntry(
//                Item.getByNameOrId("mcdisc:" + disc.minecraftId),
//                1,
//                1,
//                new LootFunction[] {},
//                new LootCondition[] {new RandomChance(1f)},
//                disc.minecraftId)
//        ).toArray(LootEntry[]::new);
//    }

}
