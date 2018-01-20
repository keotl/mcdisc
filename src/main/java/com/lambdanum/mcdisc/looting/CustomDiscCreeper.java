package com.lambdanum.mcdisc.looting;

import com.lambdanum.mcdisc.DiscRepository;
import com.lambdanum.mcdisc.McdiscConfig;
import com.lambdanum.mcdisc.model.Disc;
import com.lambdanum.mcdisc.repository.DiscRepositoryFactory;

import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class CustomDiscCreeper extends EntityCreeper {

    private DiscRepository discRepository;

    public CustomDiscCreeper(World worldIn) {
        super(worldIn);
        discRepository = new DiscRepositoryFactory().getDiscRepository(McdiscConfig.DISC_LIST_LOCATION);
    }
    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);
        if (!world.isRemote) {
            this.dropItem(getRandomMusicDiscItem(), 1);
        }
        if (this.world.getGameRules().getBoolean("doMobLoot")) {
            if (cause.getTrueSource() instanceof EntitySkeleton) {
                this.dropItem(getRandomMusicDiscItem(), 1);
            } else if (cause.getTrueSource() instanceof EntityCreeper && cause.getTrueSource() != this && ((EntityCreeper) cause.getTrueSource()).getPowered() && ((EntityCreeper) cause.getTrueSource()).isAIEnabled()) {
                ((EntityCreeper) cause.getTrueSource()).incrementDroppedSkulls();
                this.entityDropItem(new ItemStack(Items.SKULL, 1, 4), 0.0F);
            }
        }
    }

    private Item getRandomMusicDiscItem() {
        Disc randomCustomDisc = discRepository.getRandomCustomDisc();
        return Item.getByNameOrId("mcdisc:" + randomCustomDisc.minecraftId);
    }
}
