package com.lambdanum.mcdisc.looting;

import com.lambdanum.mcdisc.DiscRepository;
import com.lambdanum.mcdisc.McDiscItems;
import com.lambdanum.mcdisc.McdiscConfig;
import com.lambdanum.mcdisc.item.CustomRecord;
import com.lambdanum.mcdisc.model.Disc;
import com.lambdanum.mcdisc.repository.DiscRepositoryFactory;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraftforge.fml.loading.FMLEnvironment;

public class CustomDiscCreeper extends CreeperEntity {

  private DiscRepository discRepository;

  public CustomDiscCreeper(World worldIn) {
    super(EntityType.CREEPER, worldIn);
    discRepository = new DiscRepositoryFactory().getDiscRepository(McdiscConfig.DISC_LIST_LOCATION);
  }

  @Override
  public void onDeath(DamageSource cause) {
//    onDeathBaseEntityLiving(cause);
    if (world.isRemote) {
      return;
    }
    if (this.world.getGameRules().getBoolean(GameRules.DO_MOB_LOOT)) {
      if (cause.getTrueSource() instanceof SkeletonEntity) {
        this.entityDropItem(getRandomMusicDiscItem(), 1);
//      } else if (cause.getTrueSource() instanceof CreeperEntity && cause.getTrueSource() != this &&
//          ((CreeperEntity) cause.getTrueSource()).getPowered() &&
//          !((CreeperEntity) cause.getTrueSource()).isAIDisabled()) {
//        ((CreeperEntity) cause.getTrueSource()).incrementDroppedSkulls();
////        this.entityDropItem(new ItemStack(Items.CREEPER_HEAD, 1, 4), 0.0F);
      }
    }
  }

  private Item getRandomMusicDiscItem() {
    List<CustomRecord> discs = McDiscItems.INSTANCE.customRecords;
    int index = ThreadLocalRandom.current().nextInt(discs.size());
    return discs.get(index);
  }

  // This is a really hacky way of bypassing the parent class onDeath method.
  // This way, the vanilla Creeper class is never called, thereby removing duplicated record drops.
  // This method mimics the call to BaseEntityLiving, which should normally be done through super().
//  private void onDeathBaseEntityLiving(DamageSource p_onDeath_1_) {
//    if (!ForgeHooks.onLivingDeath(this, p_onDeath_1_)) {
//      if (!this.dead) {
//        Entity entity = p_onDeath_1_.getTrueSource();
//        EntityLivingBase entitylivingbase = this.getAttackingEntity();
//        if (this.scoreValue >= 0 && entitylivingbase != null) {
//          entitylivingbase.awardKillScore(this, this.scoreValue, p_onDeath_1_);
//        }
//
//        if (entity != null) {
//          entity.onKillEntity(this);
//        }
//
//        this.dead = true;
//        this.getCombatTracker().reset();
//        if (!this.world.isRemote) {
//          int i = ForgeHooks.getLootingLevel(this, entity, p_onDeath_1_);
//          this.captureDrops = true;
//          this.capturedDrops.clear();
//          if (this.canDropLoot() && this.world.getGameRules().getBoolean("doMobLoot")) {
//            boolean flag = this.recentlyHit > 0;
//            this.dropLoot(flag, i, p_onDeath_1_);
//          }
//
//          this.captureDrops = false;
//          if (!ForgeHooks.onLivingDrops(this, p_onDeath_1_, this.capturedDrops, i, this.recentlyHit > 0)) {
//            Iterator var7 = this.capturedDrops.iterator();
//
//            while (var7.hasNext()) {
//              EntityItem item = (EntityItem) var7.next();
//              this.world.spawnEntity(item);
//            }
//          }
//        }
//
//        this.world.setEntityState(this, (byte) 3);
//      }
//    }
//  }
}
