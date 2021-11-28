package com.trainguy.animationoverhaul.mixin;

import com.trainguy.animationoverhaul.access.EntityAccess;
import com.trainguy.animationoverhaul.access.LivingEntityAccess;
import com.trainguy.animationoverhaul.util.animation.LivingEntityAnimParams;
import com.trainguy.animationoverhaul.util.animation.Locator;
import com.trainguy.animationoverhaul.util.animation.LocatorRig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.HashMap;

@Unique
@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity implements LivingEntityAccess {

    @Shadow public abstract void lerpHeadTo(float f, int i);

    private LivingEntityAnimParams animationParameters;


    private String songName;
    private boolean songPlaying;
    private BlockPos songOrigin = new BlockPos(0, 0, 0);

    private String equippedArmor = "";

    public boolean useInventoryRenderer = false;

    private LocatorRig locatorRig;

    /*
    public float getAnimationVariable(String variableType){
        return 4F;
    }
    public void setAnimationVariable(String variableType, float newValue){
    }

     */

    public void setRecordPlayerNearbyValues(String songName, boolean songPlaying, BlockPos songOrigin){
        if(songPlaying){
            this.songName = songName;
            float frequency = switch(songName){
                case "music_disc_13" -> 1F;
                case "music_disc_cat" -> 1F;
                case "music_disc_blocks" -> 1F;
                case "music_disc_chirp" -> 1F;
                case "music_disc_far" -> 1F;
                case "music_disc_mall" -> 1F;
                case "music_disc_mellohi" -> 1F;
                case "music_disc_stal" -> 1F;
                case "music_disc_strad" -> 1F;
                case "music_disc_ward" -> 1F;
                case "music_disc_11" -> 1F;
                case "music_disc_wait" -> 1F;
                case "music_disc_pigstep" -> 1F;
                default -> throw new IllegalStateException("Unexpected song name value: " + songName);
            };
            ((EntityAccess)this).setAnimationTimer("dance_frequency", frequency);
        }
        this.songPlaying = songPlaying;
        this.songOrigin = songOrigin;
    }

    public boolean getIsSongPlaying(){
        return this.songPlaying;
    }
    public BlockPos getSongOrigin(){
        return this.songOrigin;
    }
    public String getSongName(){
        return this.songName;
    }
    public void setEquippedArmor(String currentArmor){
        this.equippedArmor = currentArmor;
    }
    public String getPreviousEquippedArmor(){
        return this.equippedArmor;
    }

    // Animation parameters
    public void setAnimationParamaters(float animationPosition, float animationSpeed, float tickAtFrame, float tickDifference, float delta, float headYRot, float headXRot, int lightInt){
        this.animationParameters = new LivingEntityAnimParams(animationPosition, animationSpeed, tickAtFrame, tickDifference, delta, headYRot, headXRot, lightInt);
    }
    public LivingEntityAnimParams getAnimationParameters(){
        return this.animationParameters;
    }

    public boolean getUseInventoryRenderer(){
        return useInventoryRenderer;
    }

    public void setUseInventoryRenderer(boolean bool){
        useInventoryRenderer = bool;
    }

    public void storeLocatorRig(LocatorRig locatorRig){
        this.locatorRig = locatorRig;
    }
    public LocatorRig getLocatorRig(){
        return this.locatorRig;
    }
}
