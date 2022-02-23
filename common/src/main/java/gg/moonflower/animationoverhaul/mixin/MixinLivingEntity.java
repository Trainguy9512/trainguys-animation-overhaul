package gg.moonflower.animationoverhaul.mixin;

import gg.moonflower.animationoverhaul.access.LivingEntityAccess;
import gg.moonflower.animationoverhaul.util.animation.LocatorRig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Unique
@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity implements LivingEntityAccess {

    @Shadow public abstract void lerpHeadTo(float f, int i);

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
