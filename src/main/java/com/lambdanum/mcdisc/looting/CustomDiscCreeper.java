package com.lambdanum.mcdisc.looting;

import com.lambdanum.mcdisc.DiscRepository;
import com.lambdanum.mcdisc.McdiscConfig;
import com.lambdanum.mcdisc.model.Disc;
import com.lambdanum.mcdisc.repository.DiscRepositoryFactory;

import java.util.Iterator;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class CustomDiscCreeper extends EntityCreeper {

    private DiscRepository discRepository;

    public CustomDiscCreeper(World worldIn) {
        super(worldIn);
        discRepository = new DiscRepositoryFactory().getDiscRepository(McdiscConfig.DISC_LIST_LOCATION);
    }

    @Override
    public void onDeath(DamageSource cause) {
        onDeathBaseEntityLiving(cause);
        if (world.isRemote) {
            return;
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

    // This is a really hacky way of bypassing the parent class onDeath method.
    // This way, the vanilla Creeper class is never called, thereby removing duplicated record drops.
    // This method mimics the call to BaseEntityLiving, which should normally be done through super().
    private void onDeathBaseEntityLiving(DamageSource p_onDeath_1_) {
        if (!ForgeHooks.onLivingDeath(this, p_onDeath_1_)) {
            if (!this.dead) {
                Entity entity = p_onDeath_1_.getTrueSource();
                EntityLivingBase entitylivingbase = this.getAttackingEntity();
                if (this.scoreValue >= 0 && entitylivingbase != null) {
                    entitylivingbase.awardKillScore(this, this.scoreValue, p_onDeath_1_);
                }

                if (entity != null) {
                    entity.onKillEntity(this);
                }

                this.dead = true;
                this.getCombatTracker().reset();
                if (!this.world.isRemote) {
                    int i = ForgeHooks.getLootingLevel(this, entity, p_onDeath_1_);
                    this.captureDrops = true;
                    this.capturedDrops.clear();
                    if (this.canDropLoot() && this.world.getGameRules().getBoolean("doMobLoot")) {
                        boolean flag = this.recentlyHit > 0;
                        this.dropLoot(flag, i, p_onDeath_1_);
                    }

                    this.captureDrops = false;
                    if (!ForgeHooks.onLivingDrops(this, p_onDeath_1_, this.capturedDrops, i, this.recentlyHit > 0)) {
                        Iterator var7 = this.capturedDrops.iterator();

                        while(var7.hasNext()) {
                            EntityItem item = (EntityItem)var7.next();
                            this.world.spawnEntity(item);
                        }
                    }
                }

                this.world.setEntityState(this, (byte)3);
            }

        }
    }
}
