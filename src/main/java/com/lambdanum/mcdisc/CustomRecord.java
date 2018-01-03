package com.lambdanum.mcdisc;

import com.lambdanum.mcdisc.model.Disc;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemRecord;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class CustomRecord extends ItemRecord {

    public CustomRecord(Disc disc) {
        super(disc.minecraftId,new SoundEvent(new ResourceLocation("mcdisc",disc.soundId)));
        this.setRegistryName(disc.minecraftId);
        this.setUnlocalizedName("record");
        if (FMLCommonHandler.instance().getSide().isClient()) {
            registerModel(disc);
        }

    }

    private void registerModel(Disc disc) {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getDiscModelLocation(disc), "inventory"));
    }

    private String getDiscModelLocation(Disc disc) {
        switch (disc.id % 12) {
            case 1:
                return "minecraft:record_blocks";
            case 2:
                return "minecraft:record_13";
            case 3:
                return "minecraft:record_11";
            case 4:
                return "minecraft:record_cat";
            case 5:
                return "minecraft:record_chirp";
            case 6:
                return "minecraft:record_far";
            case 7:
                return "minecraft:record_mall";
            case 8:
                return "minecraft:record_mellohi";
            case 9:
                return "minecraft:record_stal";
            case 10:
                return "minecraft:record_strad";
            case 11:
                return "minecraft:record_wait";
            default:
                return "minecraft:record_ward";
        }

    }
}
